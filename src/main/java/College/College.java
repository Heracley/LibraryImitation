package College;

import People.Person;
import People.PersonGenerator;
import java.util.ArrayList;

public class College {
    private final ArrayList<Person> userList = new ArrayList<>();

    public College(boolean isDummy) {
        if (isDummy) {
            generatePeople(15, 15);
        }
    }

    public ArrayList<Person> getUserList() {
        return new ArrayList<>(userList);
    }

    private void generatePeople(int stNum, int ttNum) {
        userList.addAll(PersonGenerator.generateStudent(stNum));
        userList.addAll(PersonGenerator.generateTutor(ttNum, 9,
                "Кафедра физических проблем материаловедения"));
    }
}
