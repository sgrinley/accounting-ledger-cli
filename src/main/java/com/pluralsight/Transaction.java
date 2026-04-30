package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {


    //  Implement: Date+Time formatter for cleaner output
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");


    //  Step 1: Establish Data fields (instance variables) for each transaction (date|time|description|vendor|amount)
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;


    //  Step 2: Generate constructor (for transaction object)
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }


    //  Step 3: Generate Getters to access data safely
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public double getAmount() { return amount; }


    // Step 4: Convert Transaction object into a CSV line (saving data) SERIALIZATION
    public String toCSV() {
        return date.format(DATE_FORMAT) + "|" + time.format(TIME_FORMAT) + "|" + description + "|" + vendor + "|" + amount;
    }

    //  Step 5: Convert CSV line into a Transaction object (when reading file) DESERIALIZATION
//    public static Transaction fromCSV(String line) {
//        String[] parts = line.split("\\|");
//
//        LocalDate date = LocalDate.parse(parts[0], DATE_FORMAT);
//        LocalTime time = LocalTime.parse(parts[1], TIME_FORMAT);
//
//        return new Transaction(date,
//                time,
//                parts[2],
//                parts[3],
//                Double.parseDouble(parts[4])
//        );
//    }
    public static Transaction fromCSV(String line) {
        String[] parts = line.split("\\|");

        LocalDate date = LocalDate.parse(parts[0], DATE_FORMAT);
        LocalTime time = LocalTime.parse(parts[1]);
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);

        return new Transaction(date, time, description, vendor, amount);
    }

    // Display format (user view output)
    @Override
    public String toString() {
        return date + " | " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " | " + description + " | " + vendor + " | " + String.format("$%.2f", amount);
    }
}