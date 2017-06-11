package com.duncol.client;

import java.util.Objects;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.duncol.utility.*;
import com.duncol.exceptions.*;

public class ConsoleClient implements Client {
	private PathChanger changer;
	public static Logger logger;

	private static final String USER_CHOICE_CHANGE_ENTIRE_PATH = "1";
	private static final String USER_CHOICE_CHANGE_DISC_LABEL = "2";
	
	private PathChangeFactory factory;

	static {
		BasicConfigurator.configure();
		logger = Logger.getRootLogger();
	}
	
	ConsoleClient(String fileExt) {
		this.factory = PathChangeFactory.getInstance(fileExt);
	}
	
	@Override
	public void run() {
		printWelcomeMessage();
		selectChanger();
		runChanger();
	}

	void printWelcomeMessage() {
		System.out.print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" + "Welcome to a simple program,\n"
				+ "that will assist you with renaming a whole bunch of records in a m3u file.\n"
				+ "You can exit program anytime by typing 'exit'\n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n");
	}
	
	public void selectChanger() {
		String choice;
		do {
			System.out.println("\nSelect option:\n" + "\t1 - Change entire path\n" + "\t2 - Change disc letter only\n");
			choice = InputUtils.next();
		} while (!isCorrectChoice(choice));
		try {
			changer = factory.getPathChanger(choice);
		}
		catch (InvalidChoiceException ex) {
			ex.getMessage();
		}
	}
	
	private boolean isCorrectChoice(String choice) {
		if (Objects.equals(choice, USER_CHOICE_CHANGE_ENTIRE_PATH) || Objects.equals(choice, USER_CHOICE_CHANGE_DISC_LABEL)) {
			return true;
		}
		return false;
	}
	
	private void runChanger() {
		if (changer != null) {
			changer.run();
		}
	}
}