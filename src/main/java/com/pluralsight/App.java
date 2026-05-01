package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class App {

    //  Establish Arraylist (store data)
    static ArrayList<Transaction> transactions = new ArrayList<>();

    //  Establish Scanner (read input data)
    static Scanner scanner = new Scanner(System.in);

    //  Establish File Name
    static final String FILE_NAME = "transactions.csv";

    //  Establish Main Method
    public static void main(String[] args) {
        loadTransactions();
        runHomeScreen();
    }

    //  Create: HOME SCREEN
    private static void runHomeScreen() {
        boolean isRunning = true;

        while (isRunning) {

            System.out.print("""
                    \n--- HOME SCREEN ---
                    D) Add Deposit (Credit +)
                    P) Add Payment (Debit -)
                    L) Ledger
                    X) Exit
                    Enter choice:""");
            String choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "d" -> addDeposit();
                case "p" -> addPayment();
                case "l" -> runLedgerScreen();
                case "x" -> isRunning = false;
                default -> System.out.println("Invalid input.");
            }
        }
    }

    //  Create: LEDGER SCREEN
    private static void runLedgerScreen() {
        boolean inLedger = true;

        while (inLedger) {

            System.out.print("""
                    \n--- LEDGER ---
                    A) All Entries
                    D) Deposits
                    P) Payments
                    R) Reports
                    H) Home
                    Enter choice:""");
            String choice = scanner.nextLine().toLowerCase().trim();

            switch (choice) {
                case "a" -> showAllTransactions();
                case "d" -> showDeposits();
                case "p" -> showPayments();
                case "r" -> runReportsScreen();
                case "h" -> inLedger = false;
                default -> System.out.println("Invalid input.");
            }
        }
    }

    //  Create: REPORT SCREEN
    private static void runReportsScreen() {
        boolean inReports = true;

        while (inReports) {

            System.out.print("""
                    \n--- REPORTS ---
                    1) Month To Date
                    2) Previous Month
                    3) Year To Date
                    4) Previous Year
                    5) Search Vendor
                    6) Custom Search
                    0) Back
                    Enter choice:""");
            String choice = scanner.nextLine().trim();

            LocalDate today = LocalDate.now();

            switch (choice) {
                case "1" -> showMonthToDate(today);
                case "2" -> showPreviousMonth(today);
                case "3" -> showYearToDate(today);
                case "4" -> showPreviousYear(today);
                case "5" -> searchByVendor();
                case "6" -> customSearch();
                case "0" -> inReports = false;
                default -> System.out.println("Invalid input.");
            }
        }
    }

    //  Filter by: MONTH TO DATE [METHOD]
    private static void showMonthToDate(LocalDate today) {
        boolean found = false;

        for (Transaction t : getSortedTransactions()) {
            if (t.getDate().getMonth() == today.getMonth() && t.getDate().getYear() == today.getYear()) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No transactions found. ");
    }

    //  Filter by: PREVIOUS MONTH [METHOD]
    private static void showPreviousMonth(LocalDate today) {
        boolean found = false;

        LocalDate prev = today.minusMonths(1);

        for (Transaction t : getSortedTransactions()) {
            if (t.getDate().getMonthValue() == prev.getMonthValue() && t.getDate().getYear() == prev.getYear()) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No transactions found. ");
    }

    //  Filter by: YEAR TO DATE [METHOD]
    private static void showYearToDate(LocalDate today) {
        boolean found = false;

        for (Transaction t : getSortedTransactions()) {
            if (t.getDate().getYear() == today.getYear()) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No transactions found. ");
    }

    //  Filter by: PREVIOUS YEAR [METHOD]
    private static void showPreviousYear(LocalDate today) {
        boolean found = false;

        int lastYear = today.getYear() - 1;

        for (Transaction t : getSortedTransactions()) {
            if (t.getDate().getYear() == lastYear) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No transactions found. ");
    }

    //  Filter by: VENDOR [METHOD]
    private static void searchByVendor() {
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        boolean found = false;

        for (Transaction t : getSortedTransactions()) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for vendor: " + vendor);
        }
    }

    //  Filter by: CUSTOM SEARCH [METHOD]
    private static void customSearch() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (Transaction t : getSortedTransactions()) {

            boolean matchesVendor = t.getVendor().toLowerCase().contains(keyword);
            boolean matchesDescription = t.getDescription().toLowerCase().contains(keyword);

            if (matchesVendor || matchesDescription) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions matched: " + keyword);
        }
    }


    //  ADD: DEPOSITS
    private static void addDeposit() {

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);

        transactions.add(t);
        saveTransaction(t);

        System.out.println("Deposit added successfully! ");
    }

    //  ADD: PAYMENTS
    private static void addPayment() {

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, -amount);

        transactions.add(t);
        saveTransaction(t);

        System.out.println("Payment added successfully! ");
    }

    //  Add: Load Transactions from CSV file
    private static void loadTransactions() {

        File file = new File(FILE_NAME);

        //  If file doesn't exist, refresh/reload instead of crash
        if (!file.exists()) {
            System.out.println("No saved transactions found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                //  Skip CSV header safely
                if (line.startsWith("date")) {
                    continue;
                }

                if (!line.isBlank()) {
                    transactions.add(Transaction.fromCSV(line));
                }
            }

            System.out.println("Transactions loaded successfully! ");

        } catch (IOException e) {
            System.out.println("Error loading transaction file. ");
        }
    }

    //  Add: Save transaction to CSV file
    private static void saveTransaction(Transaction t) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(t.toCSV());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error saving transaction. ");
        }
    }

    //  Ledger Helpers (organize ledger display functionality)
    //  Filter by: SHOWING ALL TRANSACTIONS [METHOD]
    private static void showAllTransactions() {
        for (Transaction t : getSortedTransactions()) {
            System.out.println(t);
        }
    }

    //  Filter by: SHOWING DEPOSITS ONLY [METHOD]
    private static void showDeposits() {
        boolean found = false;

        for (Transaction t : getSortedTransactions()) {
            if (t.getAmount() > 0) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) System.out.println("No deposits found.");
    }

    //  Filter by: SHOWING PAYMENTS ONLY [METHOD]
    private static void showPayments() {
        boolean found = false;

        for (Transaction t : getSortedTransactions()) {
            if (t.getAmount() < 0) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) System.out.println("No payments found.");
    }

    //    Sorting Helper Method
    private static ArrayList<Transaction> getSortedTransactions() {
        ArrayList<Transaction> sorted = new ArrayList<>(transactions);

        sorted.sort(
                Comparator.comparing(Transaction::getDate)
                        .thenComparing(Transaction::getTime)
                        .reversed()
        );

        return sorted;
    }
}