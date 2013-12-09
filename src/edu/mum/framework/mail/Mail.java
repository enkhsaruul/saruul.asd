package edu.mum.framework.mail;

public class Mail {

	private String from;
	private String to;
	private String subject;
	private String text;
	
	public Mail(String from, String to, String subject, String text) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}
}
