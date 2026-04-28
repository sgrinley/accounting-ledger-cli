package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    //  Step 1: Establish Data fields (instance variables)for each transaction
    //  date|time|description|vendor|amount
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
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // Step 4: Convert Transaction object into a CSV line (saving data)
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;

    }

    //  Step 5: Convert CSV line into a Transaction object (when reading file)
    public static Transaction fromCSV(String line) {
        String[] parts = line.split("\\|");

        LocalDate date = LocalDate.parse(parts[0]);
        LocalTime time = LocalTime.parse(parts[1]);
        String description = parts[2];
        String vendor = parts[3];
        double amount = Double.parseDouble(parts[4]);

        return new Transaction(date, time, description, vendor, amount);
    }


}

