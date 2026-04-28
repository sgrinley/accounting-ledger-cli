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
            System.out.print("""
                    \n--- HOME SCREEN ---
                    D) Add Deposit (Credit +)
                    P) Make Payment (Debit -)
                    L) Ledger
                    X) Exit
                    Enter choice""");
            String choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "D" -> System.out.println("Add Deposit");
                case "P" -> System.out.println("Make Payment");
                case "L" -> runLedgerScreen();
                case "X" -> isRunning = false;
                default -> System.out.println("Invalid input. Try again!");
            }
        }
    }
    //  Create: LEDGER SCREEN
    private static void runLedgerScreen() {
        boolean inLedger = true;

        while (inLedger) {
            System.out.print("""
                    \n--- LEDGER SCREEN ---
                    A) All Entries
                    D) Deposits
                    P) Payments
                    R) Reports
                    R) Home
                    Enter choice""");
            String choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "D" -> System.out.println("Add Deposit");
                case "P" -> System.out.println("Make Payment");
                case "L" -> runLedgerScreen();
                case "X" -> inLedger = false;
                default -> System.out.println("Invalid input. Try again!");
            }
        }
    }
}
