package com.zsh.labouCapital.util.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.zsh.labouCapital.entity.StrategyAdviceInfo;

import jxl.biff.StringHelper;

public class EmailUtil {
	
	private static final String EMAIL_HOST="smtp.qq.com" ;
	private static final String EMAIL_AUTH_CODE="wlyibuhnviwzbdia" ;//授权码
	private static final String EMAIL_ACCOUNT_FROM="790947569@qq.com" ;
	private static final String EMAIL_ACCOUNT_TO="1078733694@qq.com" ;
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
    public static MimeMessage createTextMail(String subJect,String remind)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(SESSION);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(EMAIL_ACCOUNT_FROM));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_ACCOUNT_TO));
        //邮件的标题
        message.setSubject(subJect);
        message.setSentDate(new Date());
        //邮件的文本内容
        message.setContent(remind, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
	
    public static MimeMessage creatHtmlMail(String subJect,List<StrategyAdviceInfo> emailConList) throws AddressException, MessagingException{
        if(emailConList == null || emailConList.size() <= 0){
            System.out.println("list 为空，失败！");
            return null;
        }
        //创建邮件对象
        MimeMessage message = new MimeMessage(SESSION);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(EMAIL_ACCOUNT_FROM));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_ACCOUNT_TO));
        //邮件的标题
        message.setSubject(subJect);
        message.setSentDate(new Date());
        String htmlTbaleBegin = "<html><body><div><table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"2\" bordercolor=\"#009900\"><thead ><tr><th width=\"100\">编号</th><th width=\"100\">参考时间</th><th width=\"100\">基金代码</th><th width=\"100\">基金名称</th><th width=\"100\">实时净值</th><th width=\"100\">目前成本</th><th width=\"100\">差值</th><th width=\"100\">增长率</th><th width=\"100\">策略建议</th></tr></thead><tbody>";
            
        StringBuffer allTrs = new StringBuffer();
        StringBuffer trStrBuff = null;
        for(int i=0;i<emailConList.size();i++){
            StrategyAdviceInfo tAdviceInfo = emailConList.get(i);

            trStrBuff = new StringBuffer();
            trStrBuff.append("<tr align=\"center\">");
            //1.编号
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getId());
            trStrBuff.append("</td>");
            //2.参考时间
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getTime());
            trStrBuff.append("</td>");
            
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getFundCode());
            trStrBuff.append("</td>");
            
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getFundName());
            trStrBuff.append("</td>");
            
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getNetWorth());
            trStrBuff.append("</td>");
            
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getNowCost());
            trStrBuff.append("</td>");
            
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getDiffValue());
            trStrBuff.append("</td>");
            
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getGrowRate());
            trStrBuff.append("</td>");
            
            trStrBuff.append("<td>");
            trStrBuff.append(tAdviceInfo.getStageAdvice());
            trStrBuff.append("</td>");
            trStrBuff.append("</tr>");
            allTrs.append(trStrBuff.toString());
        }
        
        String htmlTbaleEnd = "</tbody></table></div></body></html>";
        String emailStr = htmlTbaleBegin + allTrs.toString() + htmlTbaleEnd;
        //邮件的文本内容
        message.setContent(emailStr, "text/html;charset=UTF-8");
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
	        ts.connect(EMAIL_HOST, EMAIL_ACCOUNT_FROM, EMAIL_AUTH_CODE);
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
	        List<StrategyAdviceInfo> eAdviceInfos = new ArrayList<>();
	        for(int i=1;i<10;i++){
	            StrategyAdviceInfo adviceInfo = new StrategyAdviceInfo();
	            adviceInfo.setId(i+"");
	            adviceInfo.setTime("2018-09-29 13:01:00");
	            adviceInfo.setFundCode("006132");
	            adviceInfo.setFundName("天弘十瓶食品饮料");
	            adviceInfo.setNetWorth(0.564);
	            adviceInfo.setNetWorth(0.589);
	            adviceInfo.setNowCost(0.543);
	            adviceInfo.setStageAdvice("涨了3%，建议买入1500元");
	            eAdviceInfos.add(adviceInfo);
	        }
	        
	        Message message = creatHtmlMail("策略建议",eAdviceInfos);
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
