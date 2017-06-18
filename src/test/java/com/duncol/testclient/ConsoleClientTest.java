package com.duncol.testclient;

import org.junit.Test;

import com.duncol.client.Client;
import com.duncol.client.ClientFactory;

public class ConsoleClientTest {
	
	private Client client;
	
	protected void setUp() {
		this.client = ClientFactory.getInstance()
									.getConsoleClient("m3u");
	}
	
	@Test
	public void testChooseChangeOption() {
		client.run();
	}
}
