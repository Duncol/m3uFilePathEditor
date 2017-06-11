package com.duncol.main;

import com.duncol.client.Client;
import com.duncol.client.ClientFactory;

public class Main {
	private final static String FILE_EXTENSION = "m3u";
	
	
	public static void main(String[] args) {
		ClientFactory factory = ClientFactory.getInstance();
		Client client = factory.getConsoleClient(FILE_EXTENSION);
		client.run();
	}
}