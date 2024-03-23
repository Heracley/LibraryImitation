package Library.Literature;

public abstract class Literature {
    private String name;
    private String author;

    public Literature(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return name;
    }
}
