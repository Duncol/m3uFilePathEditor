package com.duncol.testmain;

import static org.junit.Assert.*;
import org.junit.Test;
import com.duncol.client.Client;
import com.duncol.client.ClientFactory;

public class MainTest {
	
	@Test
	public void testInitializeClient() {
		ClientFactory factory = ClientFactory.getInstance();
		Client client = factory.getConsoleClient("m3u");
		assertNotNull(factory);
		assertNotNull(client);
	}
}
