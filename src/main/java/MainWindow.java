import College.College;
import Library.Library;
import People.Person;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;


public class MainWindow extends JFrame {
    private JPanel panel;
    private JTree mainTree;
    private final ChosenWindow[] chosenWindow = {null};
    private final College college = new College(true);
    private final Library library = new Library(college, true);

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

        // Добавляем пользователей
        for (Person person : college.getUserList()) {
            switch (person.getRole()) {
                case "st" -> {
                    DefaultMutableTreeNode studentTreeNode = new DefaultMutableTreeNode(person);
                    studentNode.add(studentTreeNode);
                }
                case "tt" -> {
                    DefaultMutableTreeNode tutorTreeNode = new DefaultMutableTreeNode(person);
                    tutorNode.add(tutorTreeNode);
                }
            }
        }
        root.add(studentNode);
        root.add(tutorNode);
        treeModel.setRoot(root);
    }

    private void addListeners() {
        // Добавим обработчик нажатия на JTree
        mainTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getClickCount() != 2) { return; }

                TreePath selectionPath = mainTree.getSelectionPath();
                if (selectionPath == null) { return; }

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
