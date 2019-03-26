package com.zb.sms.channel.sender;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 功能: 亿美短信渠道发送器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/19 0019 13:35
 * 版本: V1.0
 */
public class YiMeiSender {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(YiMeiSender.class);

    /**
     * 成功代码
     */
    private static final String SUCCESS_CODE = "0";

    /**
     * 发送下行短信
     *
     * @param url
     * @return
     */
    public boolean send(String url, String cdkey, String password, Integer seqId, String mobile, String message) {
        String ret = "";
        try {
            String param = "cdkey=" + cdkey + "&password=" + password + "&phone=" + mobile
                    + "&message=" + URLEncoder.encode(message, "UTF-8");
            if (seqId != null) {
                param += "&seqid=" + seqId;
            }
            url = url + "?" + param;
            System.out.println("【YiMeiSender】发送MT到SDK->" + url);
            String responseString = this.sendGet(url);
            responseString = responseString.trim();
            if (null != responseString && !"".equals(responseString)) {
                ret = xmlMt(responseString);
            }
        } catch (Exception e) {
            logger.error("YiMeiSender发送下行短信异常", e);
        }
        return SUCCESS_CODE.equals(ret);
    }

    /**
     * 统一解析响应格式
     *
     * @param response
     * @return
     */
    public String xmlResponse(String response) {
        String result = "失败";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.elementText("message");
        return result;
    }

    /**
     * 解析XML响应
     *
     * @param response
     * @return
     */
    public String xmlMt(String response) {
        String result = "0";
        Document document = null;
        try {
            document = DocumentHelper.parseText(response);
        } catch (DocumentException e) {
            e.printStackTrace();
            result = "-250";
        }
        Element root = document.getRootElement();
        result = root.elementText("error");
        if (null == result || "".equals(result)) {
            result = "-250";
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置连接超时和读取超时
            connection.setConnectTimeout(30 * 1000);
            connection.setReadTimeout(60 * 1000);
            // 建立实际的连接
            connection.connect();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("Exception is happened!-->" + e.getMessage());
            result = "-99";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error("Exception is happened!", e);
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置连接超时和读取超时
            connection.setConnectTimeout(30 * 1000);
            connection.setReadTimeout(60 * 1000);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            logger.error("Exception is happened!", e);
            result = "-99";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("Exception is happened!", e);
            }
        }
        return result;
    }
}
