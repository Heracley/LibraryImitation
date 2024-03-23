package Library.Literature.EduLiterature;

import Library.Literature.Literature;

public abstract class EduLiterature extends Literature {
    private String major;
    private final String style = "Научный";

    public EduLiterature(String name, String author, String major) {
        super(name, author);
        this.major = major;
    }

    public String getMajor() { return major; }

    public String getStyle() { return style; }
}
