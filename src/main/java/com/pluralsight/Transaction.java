package com.pluralsight;

public class Transaction {

    //  Step 1: Establish Data fields (instance variables)for each transaction
    //  date|time|description|vendor|amount
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    //  Step 2: Generate constructor (for transaction object)
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }
}

