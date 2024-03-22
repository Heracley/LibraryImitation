package People;

import Chair.Chair;
import People.Student.Student;
import People.Tutor.Tutor;

import java.util.Locale;

public class PersonFabric {

    public static Person createPerson(String role, String name, String patronymic, String lastname, String group, String studentID, Chair chair) {
        return switch (role.toLowerCase()) {
            case "student" -> new Student(name, patronymic, lastname, group, studentID);
            case "tutor" -> new Tutor(name, patronymic, lastname, chair);
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
