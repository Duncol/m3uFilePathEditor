package com.duncol.utility.input;

import com.duncol.enums.FilePathRegexEnum;

public class ConsoleInputFilePathReader implements FileInputReader{
	
	private final String WIN_FILE_PATH_REGEX;
	private final String FILE_EXTENSION;
	private String filePath;
	
	private ConsoleInputFilePathReader(ConsoleInputFilePathReader.Builder builder) {
		this.FILE_EXTENSION = builder.fileExt;
		this.WIN_FILE_PATH_REGEX = builder.regex;
	}

	@Override
	public String read() {
		boolean isCorrect = false;
		while (!isCorrect) {
			System.out.println("Please specify filepath: ");
			filePath = ConsoleGeneralInputReader.read();
			isCorrect = checkUserInputFilePath(filePath);
		}
		return filePath;
	}
	
	private boolean checkUserInputFilePath(String filePath) {
		if (filePath.matches(WIN_FILE_PATH_REGEX.concat(FILE_EXTENSION))) {
			return true;
		}
		else if (filePath.matches("[a-zA-Z]:(\\\\\\w+)+\\.\\w{2,5}")) {
			System.out.println("Incorrect filetype.");
			return false;
		}
		else {
			System.out.println("Incorrect filepath.");
			return false;
		}
	}
	
	// BUILDER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public static class Builder {
		private String fileExt;
		private final String regex;
		
		Builder() {
			this.regex = FilePathRegexEnum.WIN_FILE_PATH_REGEX.get();
		}
		
		Builder fileExtension(String fileExt) {
			this.fileExt = fileExt;
			return this;
		}
		
		
		ConsoleInputFilePathReader build() {
			return new ConsoleInputFilePathReader(this);
		}
	}
}
