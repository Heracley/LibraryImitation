package Library;

import Library.Literature.Literature;
import People.Person;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class LibraryPass {
    private final HashMap<Literature, Date> taken = new HashMap<>();

    public void takeBook(Literature book, Date date) {
        taken.put(book, Objects.requireNonNullElseGet(date, Date::new));
    }

    public HashMap<Literature, Date> getTakenBooks() {
        return new HashMap<>(taken);
    }
}
