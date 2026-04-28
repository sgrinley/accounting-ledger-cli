package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    //  Establish Arraylist (store data)
    static ArrayList<Transaction> transactions = new ArrayList<>();

    //  Establish Scanner (read input data)
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        runHomeScreen();
    }

    //  Create: HOME SCREEN
    private static void runHomeScreen() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                    \n--- HOME SCREEN ---
                    D) Add Deposit (Credit +)
                    P) Make Payment (Debit -)
                    L) Ledger
                    X) Exit
                    Enter choice""");
        }
    }
}
