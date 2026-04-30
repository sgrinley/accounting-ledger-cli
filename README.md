# Transaction Ledger App

A Java console-based financial ledger application that allows users to track deposits, payments, and generate detailed financial reports. Transactions are stored in a CSV file and persist between sessions.

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
├── App.java # Main application logic (menus, UI, file handling)
├── Transaction.java # Transaction model (data + CSV conversion)
│
[CLASS: (Accounting Ledger App)]
   │
   └── main() 
       │
       ├── loadTransactions()
       │     └── reads transactions.csv into ArrayList
       │
       └── runHomeScreen()  ◄────────────────────────────────────────────┐ [LEVEL 1]
             │                                                           │
             ├── (D) Add Deposit ──► addDeposit()                        │
             │                         └── create Transaction (positive) │
             │                         └── saveTransaction()             │
             │
             ├── (P) Make Payment ──► addPayment()                       │
             │                         └── create Transaction (negative) │
             │                         └── saveTransaction()             │
             │
             ├── (L) Ledger ───────► runLedgerScreen()  ◄──────────────┤ [LEVEL 2]
             │                          │
             │                          ├── (A) All Entries ─► showAllTransactions()
             │                          │
             │                          ├── (D) Deposits ───► showDeposits()
             │                          │
             │                          ├── (P) Payments ───► showPayments()
             │                          │
             │                          ├── (R) Reports ────► runReportsScreen() ◄──┐ [LEVEL 3]
             │                          │                                           │
             │                          │     ├── (1) Month To Date                 │
             │                          │     ├── (2) Previous Month                │
             │                          │     ├── (3) Year To Date                  │
             │                          │     ├── (4) Previous Year                 │
             │                          │     ├── (5) Search by Vendor              │
             │                          │     ├── (6) Custom Search                 │
             │                          │     └── (0) Back ───────────────┘
             │                          │
             │                          └── (H) Home ───────► runHomeScreen()
             │
             └── (X) Exit ─────────────► [Terminate Application]
             

---

## 💾 File Format (transactions.csv)

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

## 👨‍💻 Author: Shamar Grinley

Built as a Java capstone project to demonstrate:
- Object-Oriented Programming
- File handling
- Data parsing/serialization
- Menu-driven application design
