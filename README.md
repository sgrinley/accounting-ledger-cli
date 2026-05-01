# Transaction Ledger App

Welcome to the Accounting (Banking) Ledger Application, a Java-based console system designed to simulate a real-world financial tracking tool.

This application allows users to record deposits and payments, categorize transactions, and generate detailed financial reports. All data is persistently stored using a CSV file, ensuring that transactions remain available between sessions.

This project demonstrates practical software engineering fundamentals including object-oriented programming, data parsing, and user-driven menu navigation built with a focus on file-based-data handling, organized architecture, and app design.

---

## Core Features

### Home Screen
- Add Deposit
- Add Payment
- Navigate to Ledger
- Exit Application

### Ledger Screen
- View All Transactions (sorted newest → oldest)
- View Deposits only
- View Payments only
- Access Reports Menu

### Reports Screen
- Month-to-Date report
- Previous Month report
- Year-to-Date report
- Previous Year report
- Search by Vendor
- Custom keyword search (vendor or description)

---

## Core Functionality

### ✔ Transaction Object Model
Each transaction contains:
- Date (`LocalDate`)
- Time (`LocalTime`)
- Description
- Vendor
- Amount (positive = deposit, negative = payment)

### ✔ CSV Persistence
- Transactions are saved to `transactions.csv`
- Automatically loaded when application starts
- Uses `|` delimiter for structured storage

### ✔ Sorting System
Transactions are automatically sorted:
- Newest → Oldest
- Based on Date, then Time

---

## Project Structure

com.pluralsight
│
├── App.java           # Main application + menus
├── Transaction.java   # Transaction model
└── Search.java        # Search model

---

## Flow Chart

![Home Screen](https://github.com/sgrinley/accounting-ledger-cli/blob/main/Flowchart%20Diagram3.png?raw=true)
             

---

## File Format (transactions.csv)

date|time|description|vendor|amount

2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50 

2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00 


---

## How to Run

1. Clone the repository
2. Open in IntelliJ IDEA (or any Java IDE)
3. Ensure Java 17+ is installed
4. Run `App.java`

---

## Technologies Used

- Java 17+
- File I/O (BufferedReader / BufferedWriter)
- ArrayList Collections
- Java Time API (LocalDate, LocalTime)
- Comparator Sorting
- Console-based UI

---

## 📸 Screenshots

### Home Screen
![Home Screen](https://github.com/sgrinley/accounting-ledger-cli/blob/main/Home%20Screen.png?raw=true)

### Ledger Screen
![Ledger Screen](https://github.com/sgrinley/accounting-ledger-cli/blob/main/Ledger%20Screen.png?raw=true)

### Reports Screen
![Reports Screen](https://github.com/sgrinley/accounting-ledger-cli/blob/main/Reports%20Screen.png?raw=true)

---

## Key Highlights

- Clean separation of concerns (App vs Transaction class)
- Persistent data storage using CSV
- Fully interactive menu system
- Advanced filtering & reporting system
- Sorted financial history view

---

## Author: Shamar Grinley

Built as a Java capstone project to demonstrate:
- Object-Oriented Programming
- File handling
- Data parsing/serialization
- Menu-driven application design
