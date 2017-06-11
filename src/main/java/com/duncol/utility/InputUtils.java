package com.duncol.utility;

import java.util.Objects;
import java.util.Scanner;

public abstract class InputUtils {
	private static Scanner scanner = new Scanner(System.in);
	private final static String USER_CHOICE_GLOBAL_EXIT = "exit";

	public static String next() {
		String input = scanner.nextLine();
		if (Objects.equals(input, USER_CHOICE_GLOBAL_EXIT)) {
			scanner.close();
			System.exit(0);
		}
		return input;
	}
}
