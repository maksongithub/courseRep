package main;

import java.util.Scanner;

import static main.Menu.mainMenuChoice;
import static main.Menu.mainMenuPrint;

public class Main {
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        mainMenuPrint();
        while (userInput.hasNext()){
            mainMenuChoice(userInput.nextInt());
        }
    }
}
