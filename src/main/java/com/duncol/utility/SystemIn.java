package com.duncol.utility;

public class SystemIn implements FilePathInput {
	private final String WIN_FILE_PATH_REGEXP = "[a-zA-Z]:(\\\\\\w+)+\\.";
	private final String FILE_EXTENSION;
	private String filePath;
	
	SystemIn(String fileExtension) {
		this.FILE_EXTENSION = fileExtension;
	}

	@Override
	public String read() {
		boolean isCorrect = false;
		while (!isCorrect) {
			System.out.println("Please specify filepath: ");
			filePath = InputUtils.next();
			isCorrect = checkUserInputFilePath(filePath);
		}
		return filePath;
	}
	
	private boolean checkUserInputFilePath(String filePath) {
		if (filePath.matches(WIN_FILE_PATH_REGEXP.concat(FILE_EXTENSION))) {
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
}
