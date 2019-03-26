package com.zillionfortune.cif.support.util;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class EmailUtils {
    private final static Logger LOGGER           = LoggerFactory.getLogger(EmailUtils.class);
    private static String       FROM;                                                        //发件人的email
    private static String       PWD;                                                         //发件人密码
    private static String       propertyFileName = "mail.properties";

    private static Properties   props            = null;

    static {
        Resource resource = new ClassPathResource(propertyFileName);
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
            FROM = props.getProperty("mail.from");
            PWD = props.getProperty("mail.password");
        } catch (IOException e) {
            LOGGER.error("读取只有文件异常", e);
        }
    }

    /**
     * 获取Session
     * @return
     */
    private Session getSession() {

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(true);

        return session;
    }

    public void send(String toEmail, Multipart content, String subject) {
        Session session = getSession();
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = { new InternetAddress(toEmail) };
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(content);

            //Send the message
            Transport.send(msg);
        } catch (MessagingException mex) {
            LOGGER.error("发送邮件异常", mex);
        }
    }
}