import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteApp {
    private static final String FILE_NAME = "notes.txt";
    private static ArrayList<Note> notes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadNotesFromFile();

        int choice;
        do {
            showMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    listNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    searchNotes();
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    saveNotesToFile();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n--- Simple Note-taking App ---");
        System.out.println("1. Add Note");
        System.out.println("2. List Notes");
        System.out.println("3. Delete Note");
        System.out.println("4. Search Notes");
        System.out.println("5. Exit");
    }

    private static void addNote() {
        System.out.print("Enter note title: ");
        String title = scanner.nextLine();

        System.out.println("Enter note content (type 'END' on a new line to finish):");
        ArrayList<String> contentLines = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            contentLines.add(line);
        }

        Note newNote = new Note(title, contentLines);
        notes.add(newNote);
        System.out.println("Note added!");
        saveNotesToFile();
    }

    private static void listNotes() {
        if (notes.isEmpty()) {
            System.out.println("Note list is empty.");
        } else {
            System.out.println("\nYour Notes:");
            for (Note note : notes) {
                System.out.println(note);
            }
        }
    }

    private static void deleteNote() {
        System.out.print("Enter the title of the note to delete: ");
        String title = scanner.nextLine();

        boolean removed = notes.removeIf(note -> note.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Note deleted.");
            saveNotesToFile();
        } else {
            System.out.println("Note with the given title not found.");
        }
    }

    private static void searchNotes() {
        System.out.print("Enter the title keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Note note : notes) {
            if (note.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(note);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No notes matching the search criteria were found.");
        }
    }

    private static void saveNotesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Note note : notes) {
                String contentJoined = String.join("|", note.getContent());
                bw.write(note.getTitle() + ";" + contentJoined);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing notes to file: " + e.getMessage());
        }
    }

    private static void loadNotesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    String title = parts[0];
                    String contentJoined = parts[1];
                    String[] contentLines = contentJoined.split("\\|");
                    ArrayList<String> contentList = new ArrayList<>();
                    for (String c : contentLines) {
                        contentList.add(c);
                    }
                    notes.add(new Note(title, contentList));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading notes from file: " + e.getMessage());
        }
    }
}


