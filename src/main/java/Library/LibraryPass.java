package Library;

import Library.Literature.Literature;
import People.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static Library.BookGenerator.generateLiterature;

public class LibraryPass {
    private final HashMap<Literature, Date> taken = new HashMap<>();

    public void takeBook(Literature book, Date date) {
        taken.put(book, Objects.requireNonNullElseGet(date, Date::new));
    }

    public void returnBook(Literature book) {
        taken.remove(book);
    }

    public HashMap<Literature, Date> getTakenBooks() {
        return new HashMap<>(taken);
    }
}
