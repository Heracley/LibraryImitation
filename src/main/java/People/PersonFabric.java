package People;

import Chair.Chair;
import People.Student.Student;
import People.Tutor.Tutor;

public class PersonFabric {

    public static Person createPerson(String role, String name, String patronymic, String lastname, String group, String studentID, Chair chair) {
        if (role.equalsIgnoreCase("student")) {
            return new Student(name, patronymic, lastname, group, studentID);
        } else if (role.equalsIgnoreCase("tutor")) {
            return new Tutor(name, patronymic, lastname, chair);
        } else {
            return null;
        }
    }
}
