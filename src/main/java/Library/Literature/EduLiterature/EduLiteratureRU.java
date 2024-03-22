package Library.Literature.EduLiterature;

public class EduLiteratureRU extends EduLiterature {
    private RUKind kind;

    public EduLiteratureRU(String name, String author, String major, String kind) {
        super(name, author, major);
        this.kind = RUKind.valueOf(kind);
    }

    public String getKind() {
        return kind.toString();
    }

    @Override
    public String toString() {
        return kind + ". " + getName() + ". " + getAuthor() + ". " + getMajor();
    }
}
