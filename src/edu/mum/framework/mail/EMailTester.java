package edu.mum.framework.mail;

public class EMailTester {
	public static void main(String[] args) {
		EMail mail = EMail.getInstance();
		mail.sendMail(new Mail("hasanaa.88@gmail.com", "khongor.enkhbold@gmail.com", "Test", "XaCaHaa"));
	}
}
