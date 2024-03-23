package Library.Literature.FictionLiterature;

import Library.Literature.RULiterature;

public class FictionLiteratureRU extends FictionLiterature implements RULiterature {
    private RUPeriod period;

    public FictionLiteratureRU(String name, String author, String genre, String period) {
        super(name, author, genre);
        this.period = RUPeriod.getByDescription(period);
    }

    public String getPeriod() {
        return period.getDescription();
    }

    @Override
    public String getLang() {
        return lang;
    }
}
