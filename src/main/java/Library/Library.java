package Library;

import Library.Literature.Literature;
import People.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import College.College;
import static Library.BookGenerator.generateLiterature;


public class Library {
    private final List<Literature> bookList = new ArrayList<>();
    private final Map<Person, LibraryPass> passMap = new HashMap<>();
    private final Random random = new Random();

    public Library(College college) {
        generateBooks(30, 30, 30, 30);
        addRandomTaken(college.getUserList());
    }

    public ArrayList<Literature> getBook() { return new ArrayList<>(bookList); }

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

    private void generateBooks(int ruFicNum, int enFicNum, int ruEduNum, int enEduNum) {
        ArrayList<Literature> books = generateLiterature(ruFicNum, enFicNum, ruEduNum, enEduNum);
        Collections.shuffle(books);
        for (Literature book : books) {
            addBook(book);
        }
    }

    private void addRandomTaken(ArrayList<Person> userList) {
        for (Person person : userList) {
            LibraryPass pass = getPass(person);
            HashMap<Literature, Date> takenBooks = pass.getTakenBooks();
            int numBooksToTake = random.nextInt(8) + 3; // Генерируем число от 3 до 10
            for (int i = 0; i < numBooksToTake; i++) {
                // Получаем случайную книгу из списка, которая еще не была взята
                Literature randomBook;
                do {
                    randomBook = getBook().get(random.nextInt(getBook().size()));
                } while (takenBooks.containsKey(randomBook));
                // Взять книгу
                pass.takeBook(randomBook, getRandomDate());
            }
        }
    }

    private Date getRandomDate() {
        Random random = new Random();
        int daysInThePast = random.nextInt(1096); // 3 years in days
        LocalDate randomDate = LocalDate.now().minusDays(daysInThePast);
        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
