package Library.Literature.EduLiterature;

import Library.Literature.RULiterature;

public class EduLiteratureRU extends EduLiterature implements RULiterature {
    private RUKind kind;
    private final String lang = "RU";

    public EduLiteratureRU(String name, String author, String major, String kind) {
        super(name, author, major);
        this.kind = RUKind.valueOf(kind);
    }

    public String getKind() {
        return kind.toString();
    }

    @Override
    public String getLang() {
        return lang;
    }
}
