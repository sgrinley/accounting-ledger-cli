package com.pluralsight;

import java.time.LocalDate;
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
                    Enter choice:""");
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
                case "A" -> System.out.println("Show all transactions");
                case "D" -> System.out.println("Show deposits only");
                case "P" -> System.out.println("Show payments only");
                case "R" -> runReportsScreen();
                case "H" -> inLedger = false;
                default -> System.out.println("Invalid input. Try again!");
            }
        }
    }

    //  Create: REPORT SCREEN
    private static void runReportsScreen() {
        boolean inReports = true;

        while (inReports) {
            System.out.print("""
                    \n--- REPORTS SCREEN ---
                    1) Month To Date
                    2) Previous Month
                    3) Year To Date
                    4) Previous Year
                    5) Search by Vendor
                    0) Back
                    Enter choice: """);

            String choice = scanner.nextLine().trim();

            switch (choice) {

                // Filter by: MONTH TO DATE
                case "1" -> {
                    LocalDate today = LocalDate.now();

                    for (Transaction t : transactions) {
                        if (t.getDate().getMonthValue() == today.getMonthValue() && t.getDate().getYear() == today.getYear()) {
                            System.out.println(t);
                        }
                    }
                }

                // Filter by: PREVIOUS MONTH
                case "2" -> {
                    LocalDate today = LocalDate.now();
                    LocalDate previousMonth = today.minusMonths(1);

                    for (Transaction t : transactions) {
                        if (t.getDate().getMonth() == previousMonth.getMonth() && t.getDate().getYear() == previousMonth.getYear()) {
                            System.out.println(t);
                        }
                    }
                }

                // Filter by: YEAR TO DATE
                case "3" -> {
                    LocalDate today = LocalDate.now();

                    for (Transaction t : transactions) {
                        if (t.getDate().getYear() == today.getYear()) {
                            System.out.println(t);
                        }
                    }
                }

                // Filter by: PREVIOUS YEAR
                case "4" -> {
                    int lastYear = LocalDate.now().getYear() - 1;

                    for (Transaction t : transactions) {
                        if (t.getDate().getYear() == lastYear) {
                            System.out.println(t);
                        }
                    }
                }

                // SEARCH BY VENDOR
                case "5" -> {
                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine().trim();

                    boolean found = false;

                    for (Transaction t : transactions) {
                        if (t.getVendor().equalsIgnoreCase(vendor)) {
                            System.out.println(t);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No transactions found for vendor: " + vendor);
                    }
                }

                // RETURN BACK
                case "0" -> inReports = false;

                default -> System.out.println("Invalid input. Try again!");
            }
        }
    }
}