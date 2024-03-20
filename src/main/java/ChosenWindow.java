import People.Person;
import People.Student.Student;
import People.Tutor.Tutor;

import javax.swing.*;

public class ChosenWindow extends JFrame {
    private JPanel panel1;

    public ChosenWindow(Person person) {
        setContentPane(panel1);
        setTitle("Библиотечный учёт 1");
        setSize(300, 300);

        if (person instanceof Student student) {
            System.out.println("Это студент: " + student.getFullName());
        } else if (person instanceof Tutor tutor) {
            System.out.println("Это преподаватель: " + tutor.getFullName());
        }

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
