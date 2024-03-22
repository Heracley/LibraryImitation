import Library.Library;
import Library.LibraryPass;

import Library.Literature.Literature;
import People.Person;
import People.PersonGenerator;
import People.Student.Student;
import People.Tutor.Tutor;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static Library.BookGenerator.generateLiterature;


public class MainWindow extends JFrame {
    private JPanel panel;
    private JTree mainTree;
    final ChosenWindow[] chosenWindow = {null}; // Используем массив для обхода требования final
    private final Library library = new Library();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Tutor> tutors = new ArrayList<>();
    private final Random random = new Random();

    public MainWindow() {
        setContentPane(panel);
        setTitle("Библиотечный учёт");
        setSize(400, 500);

        addListeners();
        fillTree();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void fillTree() {
        // TODO: place custom component creation code here
        DefaultTreeModel treeModel = (DefaultTreeModel) mainTree.getModel();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Пользователи");
        DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode("Студенты");
        DefaultMutableTreeNode tutorNode = new DefaultMutableTreeNode("Преподаватели");

        // Генерируем пользователей и добавляем в дерево
        generatePeople(30, 10);
        for (Student student : students) {
            DefaultMutableTreeNode studentTreeNode = new DefaultMutableTreeNode(student);
            studentNode.add(studentTreeNode);
        }
        for (Tutor tutor : tutors) {
            DefaultMutableTreeNode tutorTreeNode = new DefaultMutableTreeNode(tutor);
            tutorNode.add(tutorTreeNode);
        }

        // Генерируем книжки к каждому пользователю
        ArrayList<Person> userList = new ArrayList<>();
        userList.addAll(students);
        userList.addAll(tutors);
        generateBooks(userList);

        root.add(studentNode);
        root.add(tutorNode);
        treeModel.setRoot(root);
    }

    private void generatePeople(int stNum, int ttNum) {
        students = PersonGenerator.generateStudent(stNum);
        tutors = PersonGenerator.generateTutor(ttNum, 9,
                "Кафедра физических проблем материаловедения");
    }

    private Date getRandomDate() {
        Random random = new Random();
        int daysInThePast = random.nextInt(1096); // 3 years in days
        LocalDate randomDate = LocalDate.now().minusDays(daysInThePast);
        Date dateInThePast = Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dateInThePast;
    }

    private void generateBooks(ArrayList<Person> users) {
        ArrayList<Literature> books = generateLiterature(30, 30, 30, 30);
        Collections.shuffle(books);
        for (Literature book : books) {
            library.addBook(book);
        }

        for (Person person : users) {
            LibraryPass pass = library.getPass(person);
            HashMap<Literature, Date> takenBooks = pass.getTakenBooks();

            int numBooksToTake = random.nextInt(8) + 3; // Генерируем число от 3 до 10
            for (int i = 0; i < numBooksToTake; i++) {
                // Получаем случайную книгу из списка, которая еще не была взята
                Literature randomBook;
                do {
                    randomBook = books.get(random.nextInt(books.size()));
                } while (takenBooks.containsKey(randomBook));

                // Взять книгу
                pass.takeBook(randomBook, getRandomDate());
            }
        }
    }

    private void addListeners() {
        // Добавим обработчик нажатия на JTree
        mainTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getClickCount() != 2) {
                    return;
                }

                TreePath selectionPath = mainTree.getSelectionPath();
                if (selectionPath == null) {
                    return;
                }

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
                String nodeText = selectedNode.getUserObject().toString();
                if (nodeText.equals("Пользователи")||nodeText.equals("Студенты")||nodeText.equals("Преподаватели")) {
                    return;
                }

                // Если уже открыто дополнительное окно, скрываем его
                if (chosenWindow[0] != null) {
                    chosenWindow[0].setVisible(false);
                    chosenWindow[0].dispose();
                }

                // Получаем координаты текущего окна
                Point currentLocation = getLocation();
                int currentX = (int) currentLocation.getX();
                int currentY = (int) currentLocation.getY();

                // Создаем новое окно и позиционируем его справа от текущего окна
                chosenWindow[0] = new ChosenWindow((Person) selectedNode.getUserObject(), library);
                chosenWindow[0].setLocation(currentX + getWidth(), currentY);
                chosenWindow[0].setVisible(true);
                chosenWindow[0].addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        chosenWindow[0] = null; // Очищаем ссылку при закрытии окна
                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }
}
