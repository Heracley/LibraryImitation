package People;

public abstract class Person {
    private String name;
    private String patronymic;
    private String lastname;
    public String getFullName() {
        return name + " " + patronymic + " " + lastname;
    }
}
