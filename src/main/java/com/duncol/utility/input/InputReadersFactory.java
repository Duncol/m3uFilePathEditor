package com.duncol.utility.input;

public class InputReadersFactory {
	private static InputReadersFactory instance;

	public static InputReadersFactory getInstance() {
		if (instance == null) {
			instance = new InputReadersFactory();
		}
		return instance;
	}
	
	public ConsoleInputFilePathReader consoleInputFilePathReader(String fileExtension) {
		return new ConsoleInputFilePathReader.Builder()
								.fileExtension(fileExtension)
								.build();
	}
}
