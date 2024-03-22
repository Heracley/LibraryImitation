package Library.Literature.EduLiterature;

public class EduLiteratureFabric {
    public static EduLiterature createBook(String lang, String name, String author, String major, String gradeOrKind, String university) {
        return switch (lang.toLowerCase()) {
            case "ru" -> new EduLiteratureRU(name, author, major, gradeOrKind);
            case "en" -> new EduLiteratureEN(name, author, major, gradeOrKind, university);
            default -> throw new IllegalArgumentException("Unsupported language: " + lang);
        };
    }
}
