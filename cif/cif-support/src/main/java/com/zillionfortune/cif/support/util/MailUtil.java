package com.zillionfortune.cif.support.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public class MailUtil {
    protected static Logger     log     = LoggerFactory.getLogger(MailUtil.class);
    @Autowired
    private MailSender          mailSender;

    /** 日切成功邮件主题 */
    private final static String SUBJECT = "生产系统";

    /**
     * 日切成功发送邮件
     */
    public void sendDayCutSuccessMail(String from, String[] to, String cc, String accDate) {
        sendMail(SUBJECT, "", from, to, cc, accDate);
    }

    private void sendMail(String subject, String text, String from, String[] to, String cc, String accDate) {
        log.info("from:" + from + " to:" + to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setText(text);
        message.setCc(cc);
        message.setTo(to);
        mailSender.send(message);
    }

}
