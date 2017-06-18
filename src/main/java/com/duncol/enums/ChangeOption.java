package com.duncol.enums;

public enum ChangeOption {
	
	USER_CHOICE_CHANGE_ENTIRE_PATH (1),
	USER_CHOICE_CHANGE_DISC_LABEL (2);
	
	private final int option;
	
	private ChangeOption(int option) {
		this.option = option;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.option);
	}
	
	public int option() {
		return this.option;
	}
}
