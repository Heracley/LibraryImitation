import People.PersonGenerator;
import People.Student.Student;
import People.Tutor.Tutor;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private JPanel panel;
    private JTree mainTree;

    public MainWindow() {
        setContentPane(panel);
        setTitle("Библиотечный учёт");
        setSize(400, 500);

        fillTree();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainTree.addComponentListener(new ComponentAdapter() {
        });
    }

    private void fillTree() {
        // TODO: place custom component creation code here
        DefaultTreeModel treeModel = (DefaultTreeModel) mainTree.getModel();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Пользователи");
        DefaultMutableTreeNode studentNode = new DefaultMutableTreeNode("Студенты");
        DefaultMutableTreeNode tutorNode = new DefaultMutableTreeNode("Преподаватели");

        ArrayList<Student> students = PersonGenerator.generateStudent(30);
        for (Student student : students) {
            DefaultMutableTreeNode studentTreeNode = new DefaultMutableTreeNode(student);
            studentNode.add(studentTreeNode);
        }

        ArrayList<Tutor> tutors = PersonGenerator.generateTutor(10, 1, "Computer Science");
        for (Tutor tutor : tutors) {
            DefaultMutableTreeNode tutorTreeNode = new DefaultMutableTreeNode(tutor);
            tutorNode.add(tutorTreeNode);
        }

        root.add(studentNode);
        root.add(tutorNode);
        treeModel.setRoot(root);
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }
}
