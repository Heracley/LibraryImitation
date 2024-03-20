package People;

import Chair.Chair;
import People.Student.Student;
import People.Tutor.Tutor;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Locale;

public class PersonGenerator {
    public static final Faker faker = new Faker(Locale.forLanguageTag("ru-RU"));

    public static ArrayList<Student> generateStudent(int numStudent) {
        ArrayList<Student> studentList = new ArrayList<>();

        for (int i = 0; i < numStudent; i++) {
            String name = faker.name().firstName();
            String patronymic = faker.name().lastName();
            String lastname = faker.name().lastName();
            String group = faker.bothify("Группа ?-###");
            String studentID = faker.bothify("#####");
            Student student = (Student) PersonFabric.createPerson("student", name, patronymic, lastname, group, studentID, null);
            studentList.add(student);
        }

        studentList.sort((p1, p2) -> p1.getFullName().compareToIgnoreCase(p2.getFullName()));
        return studentList;
    }

    public static ArrayList<Tutor> generateTutor(int numTutor, int chairNumber, String chairName) {
        ArrayList<Tutor> tutorList = new ArrayList<>();

        Chair chair = new Chair(chairNumber, "chairName");

        for (int i = 0; i < numTutor; i++) {
            String name = faker.name().firstName();
            String patronymic = faker.name().lastName();
            String lastname = faker.name().lastName();
            Tutor tutor = (Tutor) PersonFabric.createPerson("tutor", name, patronymic, lastname, null, null, chair);
            tutorList.add(tutor);
        }

        tutorList.sort((p1, p2) -> p1.getFullName().compareToIgnoreCase(p2.getFullName()));
        return tutorList;
    }
}
