package com.duncol.utility;

import java.util.ArrayList;
import com.duncol.client.Client;


public class DiscLetterChange extends EditOption {
    public DiscLetterChange(){
        setAlphabet();
    }
    public void change() {
        String letter = getReplaceLetter();
        ArrayList<String> newSongPaths = new ArrayList<String>();
        for (String songPath : songPathsCopy){
            newSongPaths.add(songPath.replace(songPath.substring(0,1), letter));
        }
        Client.songPaths = newSongPaths;

    }
    public String getReplaceLetter(){
        System.out.print("Please specify the disc letter you would like to implement as new: ");
        String letter;
        do {
            letter = Client.input.nextLine().toUpperCase();
            if (letter.length() > 1) {
                System.out.println("You've typed more than one character. Please type again.");
                continue;
            }
            else {
                for (int i = 0; i < alphabet.length; i++) {
                    if (!letter.isEmpty() && alphabet[i] == letter.toLowerCase().charAt(0)) {
                        return letter;
                    }
                }
                System.out.println("The character that you type is not correct. Please type again");
            }
        } while (true);
    }
    void setAlphabet(){
        int k = 0;
        for (int i = 0; i<26; i++){
            this.alphabet[i] = (char) (97+(k++));
        }
    }
}
