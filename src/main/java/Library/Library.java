package Library;

import People.Person;

public class Library {
    public LibraryPass createPass(Person person) {
        return new LibraryPass(person);
    }
}
