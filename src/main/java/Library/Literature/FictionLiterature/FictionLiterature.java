package Library.Literature.FictionLiterature;

import Library.Literature.Literature;

public abstract class FictionLiterature implements Literature {
    private String name;
    private String author;
    private String genre;

    public FictionLiterature(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public String getName() { return name; }

    public String getAuthor() { return author; }

    public String getGenre() { return genre; }

    @Override
    public String toString() {
        return name + ".\n" + author + ".\n" + genre;
    }
}
