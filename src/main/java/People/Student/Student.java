package People.Student;

import People.Person;

public class Student extends Person {
    private String group;
    private final String studentID;

    public Student(String name, String patronymic, String lastname, String group, String studentID) {
        super(name, patronymic, lastname);
        this.group = group;
        this.studentID = studentID;
    }

    public String getGroup() {
        return group;
    }
    public String getStudentID() {
        return studentID;
    }
}
