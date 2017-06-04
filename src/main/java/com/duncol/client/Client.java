package com.duncol.client;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.duncol.utility.*;
import com.duncol.exceptions.*;

public class Client {
    private static Change change;
    public static ArrayList<String> songPaths;
    public static void main(String[] args){
    	try {
    		run();
    	}
    	catch (InvalidChoiceException ex) {
    		ex.printStackTrace();
    	}
    }
    static void run() throws InvalidChoiceException{
        Scanner input = new Scanner(System.in);
        System.out.print("%%%%%%%%%%%%%%%%%%%%\nWelcome to a simple program, \n" +
                "that will assist you with renaming a whole bunch of records in a m3u file. \n%%%%%%%%%%%%%%%%%%%%\n\n" +
                "Please specify filename (make sure it is in program folder!): ");
        String answer = input.nextLine();
        if (!answer.contains(".m3u")){answer = answer + ".m3u";}
        parsem3u(answer);
        answer = "";
        while(!(answer.equals("1") || answer.equals("2") || answer.equals("3"))) {
            System.out.println(
                    "\nPlease select something:\n" +
                            "\t1 - Change entire path\n" +
                            "\t2 - Change disc letter only\n" +
                            "\t3 - Quit");
            answer = input.nextLine();
        }
        input.close();
        switch (answer){
            case "1":
                change = new WholePathChange();
                break;
            case "2":
                change = new DiscLetterChange();
                break;
            case "3":
                System.exit(0);
            default: throw new InvalidChoiceException("Invalid choice");
        }
        change.change();
        saveNewm3u();
    }
    static void parsem3u(String filePath) throws InvalidChoiceException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            ArrayList<String> paths = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                paths.add(line);
            }
            songPaths = paths;
        }
        catch (IOException ex) {
            Scanner scan = new Scanner(System.in);
            char answer = 'a';
            while (answer != 'y' || answer != 'n') {
                System.out.println("The file does not exist. Do you want to change m3u filepath? (y/n)");
                answer = scan.next().charAt(0);
                if (answer == 'y') {
                    System.out.print("Please type filepath: (to quit program type 'q')");
                    Scanner scan2 = new Scanner(System.in);
                    String path = scan2.nextLine();
                    if (path.equals("q")){ System.exit(0);}
                    parsem3u(path);
                    scan2.close();
                } else if (answer == 'n') {
                    System.exit(0);
                } else {;
                    throw new InvalidChoiceException("Error: invalid answer");
                }
            }
        }
    }
    static void saveNewm3u(){
        try {
            PrintWriter out = new PrintWriter(new FileWriter("newPlaylist.m3u"));
            for (String path : songPaths){
                out.println(path);
            }
            out.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
