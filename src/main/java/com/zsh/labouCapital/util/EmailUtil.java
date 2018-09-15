package com.zsh.labouCapital.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	private static final String EMAIL_HOST="smtp.qq.com" ;
	private static final String EMAIL_AUTH_CODE="wlyibuhnviwzbdia" ;//授权码
	private static final String EMAIL_ACCOUNT="790947569@qq.com" ;
	private static final String EMAIL_SMTP_AUTH ="true" ;
	private static final String MAIL_TRANSPORT_PROTOCOL="smtp";
	private static Session SESSION = null;
	
	static{
		try {
			if(SESSION == null){
				Properties prop = new Properties();
		        prop.setProperty("mail.host", EMAIL_HOST);
		        prop.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
		        prop.setProperty("mail.smtp.auth", EMAIL_SMTP_AUTH);
		        //使用JavaMail发送邮件的5个步骤
		        //1、创建session
		        SESSION = Session.getInstance(prop);
		        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		        SESSION.setDebug(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
    * @Method: createSimpleMail
    * @Description: 创建一封只包含文本的邮件
    * @param session
    * @return
    * @throws Exception
    */ 
    public static MimeMessage createSimpleMail(String subJect,String remind)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(SESSION);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("790947569@qq.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("1078733694@qq.com"));
        //邮件的标题
        message.setSubject(subJect);
        //邮件的文本内容
        message.setContent("根据最新的基金数据，基金编号:000008，最新净值:0.58，持仓价格：0.60,持有份额：1000,建议：买入，降低成本。！", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
	
    /**
     * @Method: sendEmail
     * @Description: 发送邮件
     * @param session
     * @return
     * @throws Exception
     */ 
    public static boolean sendEmail(Message message){
    	boolean isSuccess = false;
        //2、通过session得到transport对象
        Transport ts;
		try {
			ts = SESSION.getTransport();
			//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
	        ts.connect(EMAIL_HOST, EMAIL_ACCOUNT, EMAIL_AUTH_CODE);
	        //5、发送邮件
	        ts.sendMessage(message, message.getAllRecipients());
	        ts.close();
	        isSuccess = true;
		} catch (NoSuchProviderException e) {
			isSuccess = false;
			isSuccess = false;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
    }
    
	public static void main(String[] args) {
		Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts;
		try {
			ts = session.getTransport();
			//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
	        ts.connect("smtp.qq.com", "790947569@qq.com", EMAIL_AUTH_CODE);
	        //4、创建邮件
	        Message message = createSimpleMail("哈哈哈","aaaaa");
	        //5、发送邮件
	        ts.sendMessage(message, message.getAllRecipients());
	        ts.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
