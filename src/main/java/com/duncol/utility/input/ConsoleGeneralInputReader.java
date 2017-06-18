package com.duncol.utility.input;

import java.util.Objects;
import java.util.Scanner;

public class ConsoleGeneralInputReader {
	private static Scanner scanner = new Scanner(System.in);
	private final static String USER_CHOICE_GLOBAL_EXIT = "exit";

	public static String read() {
		String input = scanner.nextLine();
		if (Objects.equals(input, USER_CHOICE_GLOBAL_EXIT)) {
			scanner.close();
			System.exit(0);
		}
		return input;
	}
}
