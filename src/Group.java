import java.util.ArrayList;

public class Group {
    ArrayList<Student> stuList;
    int score;
    int noPrefer;
    int groupNum;
    static int groupCount = 0;
    public Group() {
        stuList = new ArrayList<Student>();
        score = 0;
        noPrefer = 0;
        groupCount++;
        groupNum = groupCount;
    }

    public Group(ArrayList<Student> list) {
        stuList = list;
        score = 0;
        noPrefer = 0;
        groupCount++;
        groupNum = groupCount;
    }

    public void score() {
        for (int i = 0; i < stuList.size(); i++) {
            Student curr = stuList.get(i);
            ArrayList<Preference> listOfPrefer = curr.getPrefer();
            double studentpoints = 0;
            int preferenceCount = 0;
            boolean hasPrefer = false;
            for (int k = 0; k < listOfPrefer.size(); k++) {
                Student potentialStudent = listOfPrefer.get(k).getStudent();
                if (stuList.contains(potentialStudent) && !potentialStudent.equStu(curr) ) {
                    preferenceCount++;
                    hasPrefer = true;
                }
            }
            if(!hasPrefer) noPrefer++;
            if(preferenceCount > 0) {
                studentpoints = Math.pow(preferenceCount,4)/-6 + 13 * Math.pow(preferenceCount,3)/6 +
                        31 * Math.pow(preferenceCount,2)/-3 + 70 * preferenceCount / 3.0 - 5;
            } else {
                studentpoints = 0;
            }
            score += (int) Math.round(studentpoints);
        }
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
