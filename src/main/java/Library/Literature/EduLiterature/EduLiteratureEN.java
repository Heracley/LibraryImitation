package Library.Literature.EduLiterature;


public class EduLiteratureEN extends EduLiterature {
    private ENGrade grade;
    private String university;

    public EduLiteratureEN(String name, String author, String major, String grade, String university) {
        super(name, author, major);
        this.grade = ENGrade.valueOf(grade);
        this.university = university;
    }

    public String getGrade() {
        return grade.toString();
    }
    public String getUniversity() {
        return university;
    }

    @Override
    public String toString() {
        return grade + ".\n" + super.toString();
    }
}

