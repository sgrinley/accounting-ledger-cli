package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {
                case "D" -> addDeposit();
                case "P" -> addPayment();
                case "L" -> runLedgerScreen();
                case "X" -> isRunning = false;
                default -> System.out.println("Invalid input. Try again! ");
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
                    H) Home
                    Enter choice:""");
            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {
                case "A" -> System.out.println("Show all transactions ");
                case "D" -> System.out.println("Show deposits only ");
                case "P" -> System.out.println("Show payments only ");
                case "R" -> runReportsScreen();
                case "H" -> inLedger = false;
                default -> System.out.println("Invalid input. Try again! ");
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
                    Enter choice:""");

            String choice = scanner.nextLine().trim();

            // Filter by: MONTH TO DATE
            switch (choice) {

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

                default -> System.out.println("Invalid input. Try again! ");
            }
        }
    }

    //  Add Deposit
    private static void addDeposit() {
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        transactions.add(deposit);
        System.out.println("Deposit added successfully! ");

    }

    //  Add Payment
    public static void addPayment() {
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transaction payment = new Transaction(date, time, description, vendor, -amount);
        transactions.add(payment);
        System.out.println("Payment added successfully! ");
    }

//  Add: Load Transactions from CSV file
    static final String FileName = "transsction.csv";

    private static void loadTransactions() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FileName));
            String line;

            while ((line = reader.readLine()) != null) {
                Transaction t = Transaction.fromCSV(line);
                transactions.add(t);
            }
            reader.close();
            System.out.println("Transactions loaded successfully! ");

        } catch (IOException e) {
            System.out.println("File not found. Please make sure the file is available and not locked and then try again. ");
        }
    }
}