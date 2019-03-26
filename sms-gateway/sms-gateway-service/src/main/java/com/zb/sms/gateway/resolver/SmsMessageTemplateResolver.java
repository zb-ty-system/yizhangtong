package com.zb.sms.gateway.resolver;

import com.zb.sms.common.constants.SendStatus;
import com.zb.sms.common.constants.TrueFalse;
import com.zb.sms.common.model.SmsChannelMessageDo;
import com.zb.sms.common.model.SmsMessageConfigDo;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能: 短信模板处理器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/15 0015 13:28
 * 版本: V1.0
 */
@Component
public class SmsMessageTemplateResolver {

    /**
     * 日志器
     */
    private final Logger logger = LoggerFactory.getLogger(SmsMessageTemplateResolver.class);

    /**
     * 组装报文
     *
     * @param context 上下文
     * @return
     */
    public SmsMessageContext resolve(SmsMessageContext context) {
        try {
            SmsMessageConfigDo messageConfigDo = context.getMessageConfigDo();
            //创建渠道报文
            SmsChannelMessageDo channelMessageDo = new SmsChannelMessageDo();
            PropertyUtils.copyProperties(channelMessageDo, context.getSystemMessageDo());
            PropertyUtils.copyProperties(channelMessageDo, messageConfigDo);
            channelMessageDo.setSysId(context.getSystemInfoDo().getId());
            channelMessageDo.setSmsConfigId(messageConfigDo.getId());
            channelMessageDo.setStatus(SendStatus.SEND_ING.getValue());
            //只有使用本地模板且模板已加载才需要组装
            if (TrueFalse.TRUE.getValue() == messageConfigDo.getIsLocalTemplate()
                    && null != context.getMessageTemplateDo()) {
                //将内容JSON串转换成HashMap
                HashMap<String, String> paramMap = this.toHashMap(channelMessageDo.getContent());
                //开始适配模板
                String template = context.getMessageTemplateDo().getTemplateContent(); //${xx}
                //用参数替换模板中的${}变量
                Matcher m = Pattern.compile("\\$\\{\\w+\\}").matcher(template);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    String param = m.group(); //${xx}
                    String value = paramMap.get(param.substring(2, param.length() - 1));
                    m.appendReplacement(sb, value == null ? "" : value);
                }
                m.appendTail(sb);
                channelMessageDo.setSendContent(sb.toString());
            } else {
                //远程模板
                if (TrueFalse.TRUE.getValue() == messageConfigDo.getIsRemoteTemplate()) {
                    JSONObject object = JSONObject.fromObject(channelMessageDo.getContent());
                    channelMessageDo.setContent(object.toString());
                } else {
                    //纯内容或远程模板不需要发送验证码直接发送
                    channelMessageDo.setSendContent(channelMessageDo.getContent());
                }
            }
            context.setChannelMessageDo(channelMessageDo);
        } catch (Exception e) {
            logger.error("组装模板报文异常", e);
            context.setResultCode(ResultCode.RESOLVE_TEMPLATE_ERROR);
        }
        return context;
    }

    /**
     * 将json格式的字符串解析成Map对象
     *
     * @param jsonContent
     * @return
     */
    private HashMap<String, String> toHashMap(String jsonContent) {
        HashMap<String, String> data = new HashMap<String, String>();
        if (StringUtils.isNotBlank(jsonContent)) {
            // 将json字符串转换成jsonObject
            JSONObject jsonObject = JSONObject.fromObject(jsonContent);
            if (null != jsonObject && jsonObject.size() > 0) {
                Iterator it = jsonObject.keys();
                // 遍历jsonObject数据，添加到Map对象
                while (it.hasNext()) {
                    String key = String.valueOf(it.next());
                    String value = (String) jsonObject.get(key);
                    data.put(key, value);
                }
            }
        }
        return data;
    }
}
