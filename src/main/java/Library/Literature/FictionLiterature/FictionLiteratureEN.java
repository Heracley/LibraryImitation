package Library.Literature.FictionLiterature;

import Library.Literature.ENLiterature;

public class FictionLiteratureEN extends FictionLiterature implements ENLiterature {
    private String country;

    public FictionLiteratureEN(String name, String author, String genre, String country) {
        super(name, author, genre);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String getLang() {
        return lang;
    }
}
