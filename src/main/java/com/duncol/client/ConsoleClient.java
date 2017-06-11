package com.duncol.client;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.duncol.utility.*;
import com.duncol.exceptions.*;

public class Client {
	private static EditOption option;
	private static String filePath;
	public static ArrayList<String> songPaths;
	public static Scanner input;
	public static Logger logger;

	private static final String M3U_FILE_EXTENSION = "m3u";
	private static final String USER_CHOICE_YES = "y";
	private static final String USER_CHOICE_NO = "n";
	private static final String USER_CHOICE_CHANGE_ENTIRE_PATH = "1";
	private static final String USER_CHOICE_CHANGE_DISC_LABEL = "2";
	private static final String USER_CHOICE_EXIT = "3";

	static {
		Layout lay1 = new PatternLayout("[%p] %c - %m - %d %n");
		Appender app1 = new ConsoleAppender(lay1);
		BasicConfigurator.configure(app1);

		BasicConfigurator.configure();
		logger = Logger.getRootLogger();
		input = new Scanner(System.in);
	}

	public static void main(String[] args) {
		run();
	}

	static void run() {
		printWelcomeMessage();
		filePath = readUserInputFilePath();
		try {
			parsem3u(filePath);
			option = chooseOption();
		} catch (InvalidChoiceException ex) {
			ex.printStackTrace();
		}
		option.change();
		saveNewm3u();
	}

	static EditOption chooseOption() throws InvalidChoiceException {
		String choice;
		do {
			System.out.println("\nPlease select something:\n" + "\t1 - Change entire path\n"
					+ "\t2 - Change disc letter only\n" + "\t3 - Quit");
			choice = input.nextLine();
		} while (!(USER_CHOICE_CHANGE_ENTIRE_PATH.equals(choice) || USER_CHOICE_CHANGE_DISC_LABEL.equals(choice)
				|| USER_CHOICE_EXIT.equals(choice)));
		switch (choice) {
		case "1":
			return new WholePathChange();
		case "2":
			return new DiscLetterChange();
		case "3":
			System.exit(0);
		default:
			throw new InvalidChoiceException("Invalid choice");
		}
	}

	static void printWelcomeMessage() {
		System.out.print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" + "Welcome to a simple program,\n"
				+ "that will assist you with renaming a whole bunch of records in a m3u file. \n"
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\n");
	}

	static String readUserInputFilePath() {
		System.out.println("Please specify filepath: ");
		String filePath = input.nextLine();
		System.out.println(filePath.matches("/\\.+\\..{2,5}/g"));

		if (filePath.contains(".")) {
			if (!filePath.contains(M3U_FILE_EXTENSION)) {
				if (containsOtherExtension(filePath)) {
					System.out.println("It is highly likely, that you've chosen wrong file type.");
					if (tryAgainQuestion()) {
						readUserInputFilePath();
					}
					input.close();
					System.exit(0);
				}
			}
			return filePath = filePath.concat(M3U_FILE_EXTENSION);
		}
		return filePath = filePath.concat("." + M3U_FILE_EXTENSION);
	}

	static boolean tryAgainQuestion() {
		String answer;
		do {
			System.out.println("Do you want to try again?");
			answer = input.nextLine();
			if (Objects.equals(answer.toLowerCase(), USER_CHOICE_YES)) {
				return true;
			} else if (Objects.equals(answer.toLowerCase(), USER_CHOICE_NO)) {
				return false;
			} else {
				System.out.println("Incorrect answer, try again");
			}
		} while (!Objects.equals(answer.toLowerCase(), USER_CHOICE_YES)
				|| !Objects.equals(answer.toLowerCase(), USER_CHOICE_NO));
		return false;
	}

	static boolean containsOtherExtension(String input) {
		if (input.matches("\\.+\\..{2,5}")) {
			return true;
		}
		return false;
	}

	static void parsem3u(String filePath) throws InvalidChoiceException {

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			ArrayList<String> paths = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {
				paths.add(line);
			}
			songPaths = paths;
		} catch (IOException ex) {
			System.out.println("The file does not exist.");
			boolean isYes = tryAgainQuestion();
			if (isYes) {
				String newPath = readUserInputFilePath();
				parsem3u(newPath);
			} else if (!isYes) {
				input.close();
				System.exit(0);
			} else {
				throw new InvalidChoiceException("Error: invalid answer");
			}
		}
	}

	static void saveNewm3u() {
		String newFilePath = filePath.substring(0, filePath.lastIndexOf("\\"));
		try {
			PrintWriter out = new PrintWriter(new FileWriter(newFilePath + "\\newPlaylist.m3u"));
			for (String path : songPaths) {
				out.println(path);
			}
			System.out.println("Operation complete, converted playlist was saved in the same directory, as previous one. Enjoy!");
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
