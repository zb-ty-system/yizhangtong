package com.zillionfortune.cif.support.common;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.PaycoreException;
import com.zillionfortune.cif.support.domain.EmailDomain;
import com.zillionfortune.cif.support.domain.MyAuthenticator;

@Component
public class EmailUtil{
	private String hostName;
	private String port;
	private String userName;
	private String password;
	private String smtp;
	private String open;
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}


	/**
     * 此段代码用来发送普通电子邮件
     */
    public void send(EmailDomain emailDoamin) throws PaycoreException{
        try{
        	// 获取系统环境
            Properties props = new Properties(); 
            Authenticator auth = new MyAuthenticator(userName,password);
            props.put("mail.smtp.host", getHostName());
            props.put("mail.smtp.auth", "true");
            
            //设置session,和邮件服务器进行通讯。
            Session session = Session.getDefaultInstance(props, auth);
            
            MimeMessage message = new MimeMessage(session);
            // 设置邮件主题
            message.setSubject(emailDoamin.getMailSubject());
            // 设置邮件正文
            message.setText(emailDoamin.getEmailBody());
            
            // 设置邮件发送日期
            message.setSentDate(new Date()); 
            // 设置邮件发送者的地址
            Address address = new InternetAddress(emailDoamin.getFromEmail(), emailDoamin.getPersonalName());
            message.setFrom(address); 
            
            // 设置邮件接收方的地址
            String toList = getMailList(emailDoamin.getToEmail());  
            InternetAddress[] iaToList = new InternetAddress().parse(toList);  
            message.setRecipients(Message.RecipientType.TO,iaToList); //收件人     
            
            message.saveChanges();
            // 发送邮件
            Transport.send(message); 
        } catch (Exception ex){
            ex.printStackTrace();
            throw new PaycoreException("发送失败！");
        }
    }
    
    public void sendMailByAsynchronous(final EmailDomain emailDoamin) {  
        new Thread(new Runnable() {  
            public void run() {  
                try {  
                	send(emailDoamin);  
                } catch (Exception ex) {  
                }  
            }  
        }).start();  
    }  
    
    private String getMailList(String emailStr)throws PaycoreException{
    	StringBuffer emailBuffer = new StringBuffer();
    	String[] emailArr = null;
    	
        if(StringUtils.isNotBlank(emailStr)){
        	emailArr = emailStr.split(";");
        	if(emailArr!=null && emailArr.length>0){
        		for(String email:emailArr){
        			emailBuffer.append(email).append(",");
                }
        	}else{
        		throw new PaycoreException("邮箱不能为空");
        	}
        }else{
        	throw new PaycoreException("邮箱不能为空");
        }
        
        if(StringUtils.isNotBlank(emailBuffer.toString())){
        	emailBuffer.deleteCharAt(emailBuffer.length()-1);
        }
        
        return emailBuffer.toString();
    }
}
