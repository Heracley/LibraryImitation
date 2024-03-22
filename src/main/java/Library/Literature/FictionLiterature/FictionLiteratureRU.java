package Library.Literature.FictionLiterature;

import Library.Literature.EduLiterature.EduLiterature;

public class FictionLiteratureRU extends FictionLiterature {
    private RUPeriod period;

    public FictionLiteratureRU(String name, String author, String genre, String period) {
        super(name, author, genre);
        this.period = RUPeriod.getByDescription(period);
    }

    public String getPeriod() {
        return period.getDescription();
    }

    @Override
    public String toString(){
        return super.toString() + ".\n" + period.getDescription();
    }
}
