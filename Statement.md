Problem Statement
The problem

A student's week really runs on two clocks at once: deadlines and money. Assignments, lab records, quizzes and project milestones show up from three or four courses in the same week, while mess bills, travel, printouts and everyday spending pile up quietly in the background. Most of us end up tracking the first in a notes app or a rough diary, and the second nowhere at all.

Keeping the two apart causes a few very specific problems:

Things get missed. A deadline written down in a notebook only helps if you actually remember to open the notebook.
Spending stays invisible until it hurts. Without any record, "where did this month go?" only gets answered once the money is already gone.
There's no single picture of how things stand. How much of the week's work is actually done, and what the month has cost so far, are two things a student needs to see together — not as two separate, disconnected facts.

The apps already out there tend to solve exactly one half of this, usually need an internet connection, and want you to make an account before they'll do anything useful at all.

What I'm building

Student Life Manager is an offline, console-based Java program that keeps academic tasks and personal expenses in one menu-driven place, stores both as local text files, and can pull together a combined summary report whenever you ask for one.

It runs wherever a JDK is installed, needs no internet connection, no database server and no account — the data just stays on the student's own machine.

Scope of the project

What's in scope

Registering the user's name and email at the start of a session
Full create, read, update and delete on tasks, plus keyword search
Create, read and delete on expenses
A summary report covering task completion rate, spending totals and a category-wise breakdown
Validating every piece of user input before it's accepted
Saving automatically on exit and loading automatically on startup, using plain text files
Handling a missing, empty or corrupted data file without falling over
What's deliberately left out
Multiple user accounts, logins or passwords
Any graphical or web interface
A relational database, or any kind of network or cloud sync
Reminders, notifications or calendar integration
Editing an expense once it's recorded — for now it just gets deleted and re-entered
Who this is for

Mainly: college students who want one offline place to track coursework deadlines and daily spending, and who don't mind working from a terminal.

Also useful for: hostel residents splitting expenses who just need a simple spending record, and anyone learning Java who wants a readable, layered example of CRUD, collections, file I/O and validation working together in one project.

High-level features

#	Feature	Description

1	 Student registration	Captures a name and a validated email at startup

2	 Task management	Add, view, update status, delete and search tasks with due dates

3 	Expense management	Add, view and delete expenses with category, amount, date and a note

4 	Summary reporting	Completion rate, totals, average, largest expense, category breakdown

5 	Persistent storage	Saves to text files on exit and loads them on startup, with escaping and error recovery

6 	Input validation	Real calendar dates, positive amounts, required fields, a valid email and status

7	 Self-contained tests	A TestRunner class that checks the core rules without needing any test library

What success looks like

A student opens the program, sees what's still pending and what the month has cost so far, adds whatever's new, and closes it knowing the data will still be there next time — all from a single terminal window, with nothing to set up beyond a JDK.
