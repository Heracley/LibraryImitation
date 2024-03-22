package Library.Literature.EduLiterature;

import Library.Literature.Literature;

public abstract class EduLiterature implements Literature {
    private String name;
    private String author;
    private String major;

    public EduLiterature(String name, String author, String major) {
        this.name = name;
        this.author = author;
        this.major = major;
    }

    public String getName() { return name; }

    public String getAuthor() { return author; }

    public String getMajor() { return major; }

    @Override
    public String toString() {
        return getName() + ". " + getAuthor() + ". " + getMajor();
    }
}
