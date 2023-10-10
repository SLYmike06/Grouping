import java.util.ArrayList;

public class Group {
    ArrayList<Student> studentList;
    int score;
    int groupNum;
    static int groupCount = 0;
    public Group() {
        studentList = new ArrayList<Student>();
        score = 0;
        groupNum = 0;
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

    public void setScore(int score) {
        this.score = score;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }
}
