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
import java.util.ArrayList;


public class MainWindow extends JFrame {
    private JPanel panel;
    private JTree mainTree;
    final ChosenWindow[] chosenWindow = {null}; // Используем массив для обхода требования final


    public MainWindow() {
        setContentPane(panel);
        setTitle("Библиотечный учёт");
        setSize(400, 500);

        fillTree();

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TreePath selectionPath = mainTree.getSelectionPath();
                if (selectionPath != null) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
                    String nodeText = selectedNode.getUserObject().toString();

                    if (!nodeText.equals("Студенты") && !nodeText.equals("Пользователи") && !nodeText.equals("Преподаватели")) {
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
                        chosenWindow[0] = new ChosenWindow((Person) selectedNode.getUserObject());
                        chosenWindow[0].setLocation(currentX + getWidth(), currentY);
                        chosenWindow[0].setVisible(true);
                        chosenWindow[0].addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                chosenWindow[0] = null; // Очищаем ссылку при закрытии окна
                            }
                        });
                    }
                }
            }
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
