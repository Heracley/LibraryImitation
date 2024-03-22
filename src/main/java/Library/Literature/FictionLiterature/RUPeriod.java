package Library.Literature.FictionLiterature;

public enum RUPeriod {
    ДРЕВНЕРУССКАЯ_ЛИТЕРАТУРА("Древнерусская литература (XI—XVII века)"),
    XVIII_ВЕК("Русская литература XVIII века"),
    ЗОЛОТОЙ_ВЕК("Золотой век русской литературы (XIX век)"),
    СЕРЕБРЯНЫЙ_ВЕК("Русская литература Серебряного века (рубеж XIX и XX веков)"),
    XX_ВЕК("Русская литература XX века (советская и русского зарубежья)"),
    СОВРЕМЕННАЯ("Современная русская литература (конец XX и начало XXI века)");

    private final String description;

    RUPeriod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Статический метод для получения enum по описанию
    public static RUPeriod getByDescription(String description) {
        for (RUPeriod period : RUPeriod.values()) {
            if (period.description.equals(description)) {
                return period;
            }
        }
        throw new IllegalArgumentException("Неподдерживаемое описание периода: " + description);
    }
}