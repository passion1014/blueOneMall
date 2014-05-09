package com.blueone.common.util;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class BlueOneAuthentication extends Authenticator {
	
	private final String userName = Configuration.getInstance().getProperty("mail.smtp.authenticator.userName");
	private final String password = Configuration.getInstance().getProperty("mail.smtp.authenticator.password");
	
	PasswordAuthentication pa;

	public BlueOneAuthentication() {
		// smtp server의 아이디와 패스워드를 입력한다.
		pa = new PasswordAuthentication(userName, password);
	}

	// 아래의 메소드는 시스템측에서 사용하는 메소드이다.
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}

}
