package com.sx_report.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class reportConfig {

	@Value("${wordField}")
	private String wordField;
	
	public String getWordField() {
		return wordField;
	}

	public void setWordField(String wordField) {
		this.wordField = wordField;
	}
}
