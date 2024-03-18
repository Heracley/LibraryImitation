package Library;

import Library.Literature.Literature;
import People.Person;

import java.util.Date;
import java.util.HashMap;

public class LibraryPass {
    private Person person;
    private final HashMap<Literature, Date> taken = new HashMap<>();

    public LibraryPass(Person person) {
        this.person = person;
    }
}
