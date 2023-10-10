public class Preference {
    char sentiment;
    String name;

    Student student;

    public Preference(char sentiment, String name) {
        this.sentiment = sentiment;
        this.name = name;
       // this.student = student;
    }
    public char getSentiment() {
        return sentiment;
    }
    public String getName() {
        return name;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public String toString() {
        return "sentiment: " + sentiment + " name: " + name;
    }
}