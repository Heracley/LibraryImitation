import Library.Library;
import Library.LibraryPass;
import Library.Literature.Literature;
import People.Person;
import People.Student.Student;
import People.Tutor.Tutor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChosenWindow extends JFrame {
    private JPanel panel1;
    private JLabel roleField;
    private JLabel nameField;
    private JList list1;
    private JButton deleteButton;
    private JLabel groupOrChairLabel;
    private Person person;
    private Library library;
    private final Map<String, String> getMethods = new LinkedHashMap<>() {{
        put("getAuthor", "Автор: ");
        put("getStyle", "Стиль: ");
        put("getGenre", "Жанр: ");
        put("getMajor", "Дисциплина: ");
        put("getKind", "Тип литературы: ");
        put("getCountry", "Страна: ");
        put("getLang", "Язык: ");
        put("getUniversity", "Университет: ");
        put("getGrade", "Уровень: ");
    }};

    public ChosenWindow(Person person, Library library) {
        setContentPane(panel1);
        setTitle("Читательский билет");
        setSize(500, 300);

        this.person = person;
        this.library = library;

        fillLabels();
        fillBookList();
        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void fillLabels() {
        switch (person.getRole()) {
            case "st" -> {
                Student student = (Student) person;
                roleField.setText("Студент: ");
                groupOrChairLabel.setText("Группа: " + student.getGroup());
            }
            case "tt" -> {
                Tutor tutor = (Tutor) person;
                roleField.setText("Преподаватель: ");
                groupOrChairLabel.setText("Кафедра: " + tutor.getChair().getName());
            }
        }
        nameField.setText(person.getFullName());
    }

    private void fillBookList() {
        LibraryPass pass = library.getPass(person);
        HashMap<Literature, Date> takenBooks = pass.getTakenBooks();

        DefaultListModel<Literature> model = new DefaultListModel<>();

        int i = 0;
        for (Map.Entry<Literature, Date> entry : takenBooks.entrySet()) {
            i += 1;
            model.addElement(entry.getKey());
        }
        list1.setModel(model);
    }

    public String getMessageForBook(Literature book) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(book.getName()).append("\n");

        Set<String> methodNames = new HashSet<>();
        for (Method method : book.getClass().getMethods()) {
            methodNames.add(method.getName());
        }

        for (Map.Entry<String, String> entry : getMethods.entrySet()) {
            String methodName = entry.getKey();
            String methodDescription = entry.getValue();
            if (methodNames.contains(methodName)) {
                messageBuilder.append(methodDescription);
                try {
                    Method method = book.getClass().getMethod(methodName);
                    Object result = method.invoke(book);
                    messageBuilder.append(result).append("\n");
                } catch (NoSuchMethodException | IllegalAccessException |
                         InvocationTargetException ignored) {
                }
            }
        }

        // Добавим дату, когда книга была взята.
        LibraryPass pass = library.getPass(person);
        HashMap<Literature, Date> takenBooks = pass.getTakenBooks();
        Date date = takenBooks.get(book);
        // Форматирование даты в строку
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = formatter.format(date);
        messageBuilder.append("\nВзята: ").append(formattedDate);

        return messageBuilder.toString();
    }

    private void addListeners() {
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = list1.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String message = getMessageForBook((Literature) list1.getModel().getElementAt(selectedIndex));
                        JOptionPane.showMessageDialog(ChosenWindow.this, message);
                    }
                }
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    deleteButton.setEnabled(list1.getSelectedIndex() != -1);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibraryPass pass = library.getPass(person);
                pass.returnBook((Literature) list1.getModel().getElementAt(list1.getSelectedIndex()));
                fillBookList();
            }
        });
    }
}
