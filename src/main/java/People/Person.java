package People;

public abstract class Person {
    private String name;
    private String patronymic;
    private String lastname;
    private String role;

    public Person(String name, String patronymic, String lastname, String role) {
        this.name = name;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.role = role;
    }

    public String getFullName() { return lastname + " " + name + " " + patronymic; }

    public String getRole() { return role ;}

    @Override
    public String toString() { return getFullName(); }
}
