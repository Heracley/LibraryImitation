package Library.Literature.FictionLiterature;

public class FictionLiteratureFabric {
    public static FictionLiterature createBook(String lang, String name, String author, String genre, String periodOrCounty) {
        return switch (lang.toLowerCase()) {
            case "ru" -> new FictionLiteratureRU(name, author, genre, periodOrCounty);
            case "en" -> new FictionLiteratureEN(name, author, genre, periodOrCounty);
            default -> throw new IllegalArgumentException("Unsupported language: " + lang);
        };
    }
}
