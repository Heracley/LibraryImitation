package Library;

import Library.Literature.Literature;
import People.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private final List<Literature> bookList = new ArrayList<>();
    private final Map<Person, LibraryPass> passMap = new HashMap<>();

    public void addBook(Literature book) {
        bookList.add(book);
    }

    public LibraryPass getPass(Person person) {
        if (passMap.containsKey(person)) {
            return passMap.get(person);
        }

        LibraryPass pass = new LibraryPass();
        passMap.put(person, pass);
        return pass;
    }
}
