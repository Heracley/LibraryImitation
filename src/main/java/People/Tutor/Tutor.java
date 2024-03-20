package People.Tutor;

import People.Person;
import Chair.Chair;

public class Tutor extends Person {
    private Chair chair;

    public Tutor(String name, String patronymic, String lastname, Chair chair) {
        super(name, patronymic, lastname);
        this.chair = chair;
    }
}
