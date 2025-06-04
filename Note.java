import java.util.ArrayList;

public class Note {
    private String title;
    private ArrayList<String> content;

    public Note(String title, ArrayList<String> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Title: " + title + "\nContent:\n");
        for (String line : content) {
            sb.append("- ").append(line).append("\n");
        }
        return sb.toString();
    }
}

