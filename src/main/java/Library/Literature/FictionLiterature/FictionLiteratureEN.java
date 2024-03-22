package Library.Literature.FictionLiterature;

import Library.Literature.FictionLiterature.FictionLiterature;

public class FictionLiteratureEN extends FictionLiterature {
    private String country;

    public FictionLiteratureEN(String name, String author, String genre, String country) {
        super(name, author, genre);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return super.toString() + ".\n" + country;
    }
}
