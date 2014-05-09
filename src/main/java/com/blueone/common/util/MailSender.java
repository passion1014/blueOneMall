package com.blueone.common.util;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.blueone.common.domain.MailInfo;

public class MailSender {
	
	private final String host = Configuration.getInstance().getProperty("mail.smtp.host");
	private final String starttlsEnable = Configuration.getInstance().getProperty("mail.smtp.starttls.enable");
	private final String auth = Configuration.getInstance().getProperty("mail.smtp.auth");
	private final String port = Configuration.getInstance().getProperty("mail.smtp.port");
	
	public void sent(MailInfo mailInfo) throws UnsupportedEncodingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", starttlsEnable); // Gmail의 경우 반드시 true로 세팅해야 한다.
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.port", port);

		Authenticator auth = new BlueOneAuthentication();
		Session session = Session.getDefaultInstance(props, auth);

		try {
			
			// 네이버는 한번에 100명에게 보낼수 있다.
			String[] toAddrs = splitMailAddr(new String[] {mailInfo.getToAddr()});
			InternetAddress[] address = new InternetAddress[toAddrs.length];
			for (int i = 0; i < toAddrs.length; i++) {
				address[i] = new InternetAddress(toAddrs[i]);
			}
			
			InternetAddress fromAddr = new InternetAddress(mailInfo.getFromAddr());
			Message msg = new MimeMessage(session);
			
			// 닉네임(보내는사람) 세팅
			fromAddr.setPersonal(mailInfo.getFromNm(), "EUC-KR");
			msg.setFrom(fromAddr);
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSentDate(new Date());
			
			// 제목
			msg.setSubject(mailInfo.getSubject());

			// 내용
			msg.setContent(mailInfo.getCont(), "text/html; charset=EUC-KR");

			Transport.send(msg);

		} catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = mex;

			do {
				if (ex instanceof SendFailedException) {
					SendFailedException sfex = (SendFailedException) ex;
					Address[] invalid = sfex.getInvalidAddresses();
					if (invalid != null) {
						System.out.println("    ** Invalid Addresses");
						if (invalid != null) {
							for (int i = 0; i < invalid.length; i++)
								System.out.println("         " + invalid[i]);
						}
					}
					Address[] validUnsent = sfex.getValidUnsentAddresses();

					if (validUnsent != null) {
						System.out.println("    ** ValidUnsent Addresses");
						if (validUnsent != null) {
							for (int i = 0; i < validUnsent.length; i++)
								System.out
										.println("         " + validUnsent[i]);
						}
					}
					Address[] validSent = sfex.getValidSentAddresses();

					if (validSent != null) {
						System.out.println("    ** ValidSent Addresses");
						if (validSent != null) {
							for (int i = 0; i < validSent.length; i++)
								System.out.println("         " + validSent[i]);
						}
					}
				}

				if (ex instanceof MessagingException) {
					ex = ((MessagingException) ex).getNextException();
				} else {
					ex = null;
				}
			} while (ex != null);
		}
	}
	
	/**
	 * , ; 로 구분되어 한꺼번에 넘어오는 전화번호를 배열화
	 * 이미 배열로 넘어오면 받은 인자를 그대로 돌려줌
	 * @param mailAddrs 받는사람
	 * @return
	 */
	private static String[] splitMailAddr(String[] mailAddrs) {

		String[] addrs = null;

		if (mailAddrs.length == 1 && (mailAddrs[0].indexOf(",") != -1)
				|| (mailAddrs[0].indexOf(";") != -1)) {
			addrs = mailAddrs[0].replaceAll(",", ";").split(";");
		} else {
			addrs = mailAddrs;
		}

		return addrs;
	}
}
