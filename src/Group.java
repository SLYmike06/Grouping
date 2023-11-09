import java.util.ArrayList;

public class Group {
    ArrayList<Student> stuList;
    int score;
    int groupNum;
    static int groupCount = 0;
    public Group() {
        stuList = new ArrayList<Student>();
        score = 0;
        groupCount++;
        groupNum = groupCount;
    }

    public static int getGroupCount() {
        return groupCount;
    }

    public int getScore() {
        return score;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public ArrayList<Student> getStudentList() {
        return stuList;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.stuList = studentList;
    }

    public String toString() {
        String output = "Group Number: " + groupNum;
        for (Student student: stuList ) {
            output += " Student: " + student.getName();
        }
        return output;
    }
}
