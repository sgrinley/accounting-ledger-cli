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
}

