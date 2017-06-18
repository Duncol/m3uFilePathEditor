package com.duncol.client;

public class ClientFactory {
	private static ClientFactory instance;
	
	private ClientFactory() {
		
	}
	
	public static ClientFactory getInstance() {
		if (instance == null) {
			instance = new ClientFactory();
		}
		return instance;
	}

	public ConsoleClient getConsoleClient(String fileExt) {
		return new ConsoleClient(fileExt);
	}
}
