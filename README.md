# NoteApp – Java Console Note Manager

A simple Java console application that allows users to create and view categorized notes. Notes are saved to a local text file (`notes.txt`) and grouped under a single title.

## Features

- Add multiple notes under a single topic/title
- Save all notes to a plain `.txt` file
- Append new notes without overwriting previous ones
- Exclude `notes.txt` from version control using `.gitignore`

## Project Structure

java/
├── NoteApp.java # Main application logic
├── Note.java # Note data model (title + list of contents)
├── notes.txt # Stores the saved notes (excluded from GitHub)
└── .gitignore # Ignores compiled and personal files


## How to Run

> Make sure you have Java installed on your system.

```bash
# Compile the code
javac Note.java NoteApp.java

# Run the application
java NoteApp

📌 Enter note title:
> Java Tips

✏️ Enter content (type 'exit' to finish):
> Use meaningful variable names.
> Keep methods short and focused.
> exit

✅ Notes saved to notes.txt!

