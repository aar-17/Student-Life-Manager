# Student-Life-Manager

A console application in Java that lets a student keep their assignments and their spending in one place, and saves both to disk so nothing is lost when the program closes.

Built for the VITyarthi Build Your Own Project evaluation.

Overview

Most students I know track deadlines in one app and money in another, or more often in a notebook that gets lost. This project puts the two together in a single menu-driven program. It covers the object-oriented ideas from the course — classes and objects, encapsulation, composition, collections, exception handling and file I/O — applied to a problem I actually have.

There are three functional modules:

Module	What it does
Task management	Add, list, update, delete and search assignments with due dates and status
Expense management	Record spending by category, list it, delete entries, see a running total
Reporting	Completion rate, total and average spending, and a category-wise breakdown

Data is written to plain text files under data/ and read back on the next launch.

Features

Tasks

Add a task with a title, description and due date
List every task with its ID, due date and status
Mark a task Pending or Completed
Delete a task by ID
Search by keyword across titles and descriptions

Expenses

Record an expense with category, amount, date and note
List all expenses with a running total
Delete an expense by ID

Report

Task counts, split into pending and completed, with a completion percentage
Total, average and largest expense
Spending grouped by category

Throughout

Every input is validated before it is accepted — dates must be real calendar dates, amounts must be positive, required fields cannot be blank
A bad entry re-prompts instead of crashing or returning you to the main menu
IDs are generated from the highest existing ID, so deleting a record never produces a duplicate ID
Text containing the | separator is escaped when saved, so a note like books | stationery survives a save-and-reload
Technologies used
Java 8 or later (developed on Java 17; uses java.time.LocalDate, java.nio.file, and List.removeIf)
Standard library only — no external dependencies to download
Git / GitHub for version control
Plain text files for persistence

Steps to install and run

You need a JDK (version 8 or newer). Check with javac -version.

1. Clone the repository

git clone https://github.com/<your-username>/student-life-manager.git
cd student-life-manager

2. Compile

Linux or macOS:

javac -encoding UTF-8 -d out $(find src -name "*.java")

Windows (PowerShell):

powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src | % FullName)

3. Run

java -cp out app.Main

On Windows, run chcp 65001 first so the rupee symbol displays correctly.

Instructions for testing

The project ships with its own test class, so no testing library is needed:

java -cp out app.TestRunner

It exercises the validation rules, ID generation after a delete, status updates, search, category grouping and the divide-by-zero case in the report, printing a PASS or FAIL line for each and a final count.

Manual checks worth doing
Try this	Expected
Enter abc at the menu	Re-prompts for a whole number
Enter 2026-02-30 as a due date	Rejected as an impossible date
Enter -100 as an amount	Rejected, must be greater than zero
Leave a title blank	Rejected, field is required
Add 3 tasks, delete #2, add another	New task gets ID 4, not a repeated 3
Add a note containing `	`, exit, restart
Delete data/tasks.txt and restart	Starts empty without an error
Screenshots

<img width="900" height="505" alt="a67f2ab9-5f38-42bd-9ef7-a1aa452f06e4" src="https://github.com/user-attachments/assets/d80c4504-5dc7-4405-8885-ac1c8c2d49a7" />


Future enhancements
Priority levels and overdue highlighting for tasks
A monthly budget limit with a warning when spending crosses it
CSV export so the data opens in a spreadsheet
Swap the text files for SQLite without changing anything outside FileManager
A JavaFX front end over the same service classes

Author

Aarush Sudheer, 25BAI10711, CSE(AIML)
