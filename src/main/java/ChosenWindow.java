import Library.Library;
import Library.LibraryPass;
import Library.Literature.Literature;
import People.Person;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChosenWindow extends JFrame {
    private JPanel panel1;
    private JLabel roleField;
    private JLabel nameField;
    private JList list1;

    public ChosenWindow(Person person, Library library) {
        setContentPane(panel1);
        setTitle("Читательский билет");
        setSize(500, 300);

        fillLabels(person);
        fillBookList(person, library);

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void fillLabels(Person person) {
        switch (person.getRole()) {
            case "st" -> roleField.setText("Студент: ");
            case "tt" -> roleField.setText("Преподаватель: ");
            default -> roleField.setText("Неизвестная роль: ");
        }
        nameField.setText(person.getFullName());
    }

    private void fillBookList(Person person, Library library) {
        LibraryPass pass = library.getPass(person);
        HashMap<Literature, Date> takenBooks = pass.getTakenBooks();

        DefaultListModel<String> model = new DefaultListModel<>();

        int i = 0;
        for (Map.Entry<Literature, Date> entry : takenBooks.entrySet()) {
            i += 1;
            model.addElement(i + ". " + entry.getKey().toString());
        }
        list1.setModel(model);
    }
}
