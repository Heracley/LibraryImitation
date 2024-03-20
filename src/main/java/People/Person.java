package People;

public abstract class Person {
    private String name;
    private String patronymic;
    private String lastname;

    public Person(String name, String patronymic, String lastname) {
        this.name = name;
        this.patronymic = patronymic;
        this.lastname = lastname;
    }

    public String getFullName() {
        return lastname + " " + name + " " + patronymic;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
