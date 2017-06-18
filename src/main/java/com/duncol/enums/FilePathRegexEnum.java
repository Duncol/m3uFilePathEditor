package com.duncol.enums;

public enum FilePathRegexEnum {
	WIN_FILE_PATH_REGEX("[a-zA-Z]:(\\\\\\w+)+\\.");
	
	private String regex;
	
	FilePathRegexEnum(String regex) {
		this.regex = regex;
	}
	
	public String get() {
		return this.regex;
	}
}
