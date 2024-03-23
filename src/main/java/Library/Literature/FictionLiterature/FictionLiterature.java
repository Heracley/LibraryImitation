package Library.Literature.FictionLiterature;

import Library.Literature.Literature;

public abstract class FictionLiterature extends Literature {
    private String genre;
    private final String style = "Художественный";

    public FictionLiterature(String name, String author, String genre) {
        super(name, author);
        this.genre = genre;
    }

    public String getGenre() { return genre; }

    public String getStyle() { return style; }
}
