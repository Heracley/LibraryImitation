package People.Student;

import People.Person;

public class Student extends Person {
    private String group;
    private final String studentID;

    public Student(String name, String patronymic, String lastname, String group, String studentID, String role) {
        super(name, patronymic, lastname, role);
        this.group = group;
        this.studentID = studentID;
    }

    public String getGroup() { return group; }
    public String getStudentID() { return studentID; }
}
