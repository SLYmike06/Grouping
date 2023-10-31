import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


public class grouping {
    int num;
    int groupSize;
    Preference prefer;
    ArrayList<Student> studentList;
    public grouping() throws IOException {
        readFile();
    }
    public void readFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("./Untitled spreadsheet - Sheet1 (4).csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        int num = 0;
        int groupSize = 0;
        studentList = new ArrayList<Student>();
        for (CSVRecord csvRecord : csvParser) {
          //  System.out.println(csvRecord.getRecordNumber());
            if (csvRecord.getRecordNumber() == 1) {
                num = Integer.parseInt(csvRecord.get(0));
            } else if (csvRecord.getRecordNumber() == 2) {
                groupSize = Integer.parseInt(csvRecord.get(0));
            } else {
                int size = csvRecord.size();
               // System.out.println(size);
                Student newStudent = new Student(csvRecord.get(0));
                ArrayList<Preference> newStudentPreference = new ArrayList<Preference>();
                for (int i = 1; i < size; i += 2) {
                    if (csvRecord.get(i).length() > 0 || csvRecord.get(i+1).length() > 0) {
                       // System.out.println(" desire: " + csvRecord.get(i));
                        newStudentPreference.add(new Preference(csvRecord.get(i).charAt(0),csvRecord.get(i+1)));
                    }
                }
                newStudent.setPrefer(newStudentPreference);
                studentList.add(newStudent);
            }
        }
    }

    public int scoring(Group group) {
        int groupScore = 0;
        for (int i = 0; i < group.studentList.size();i++) {
            Student curr = group.studentList.get(i);
            ArrayList<Preference> listOfPrefer = curr.getPrefer();
            for (int k = 0; k < listOfPrefer.size();k++) {
                Student potentialStudent = listOfPrefer.get(k).getStudent();
                if (group.studentList.contains(potentialStudent) && k !=i) {
                    groupScore += 10;
                    break;
                }
            }
        }
        return groupScore;
    }

    public Group randCluster() {
        Group nwGroup = new Group();
        ArrayList<Student> students = new ArrayList<Student>();
        for(int i = 0; i < groupSize;i++) {
            Random rand = new Random();
            students.add(studentList.get(rand.nextInt(groupSize)));
        }
        nwGroup.setStudentList(students);
        int score = scoring(nwGroup);
        nwGroup.setScore(score);
    }
    //if the group contains any of the prefers
//    public boolean contain(ArrayList<Preference> prefer, Group gp) {
//        for(int i = 0; i < prefer.size();i++) {
//            if(prefergp.studentList)
//        }
//        return false;
//    }
    public boolean verifyData() {
        for (Student curStu: studentList) {
            ArrayList<Preference> curPrefer = curStu.getPrefer();
            for (int i = 0; i < curPrefer.size();i++) {
                Student match = findMatch(curPrefer.get(i).name);
                if (match == null) {
                    System.out.println("the Student named: " + prefer.name +  " is not a valid name");
                    return false;
                } else {
                    curPrefer.get(i).setStudent(match);
                }
            }
        }
        return true;
    }

    /**
     * searches for student that matches the name
     * @param name
     * @return the matching student or null if not found
     */
    public Student findMatch(String name) {
        for (Student stu: studentList) {
            if (name.equals(stu.getName())) {
                return stu;
            }
        }
        return null;
    }
    public void printStudents(ArrayList<Group> groups) {
        for (Group grp: groups) {
            System.out.println(grp);
        }
    }
    public void printStudents() {
        for (Student curStu: studentList) {
            System.out.println(curStu);
        }
    }

    public ArrayList<Group> demoGroup() {
        ArrayList<Group> groups = new ArrayList<Group>();
        int count = 1;
        for (int i = 0; i < 9;i+=3) {
            Group newGroup = new Group();
            ArrayList<Student> student = new ArrayList<Student>();
            student.add(studentList.get(i));
            student.add(studentList.get(i+1));
            student.add(studentList.get(i+2));
            newGroup.setStudentList(student);
            newGroup.setGroupNum(count);
            groups.add(newGroup);
            count++;
        }
        return groups;
    }
    public static void main(String[] args) throws IOException {
        grouping test = new grouping();
        test.printStudents();
        System.out.println(test.verifyData());
        ArrayList<Group> example = test.demoGroup();
        test.printStudents(example);
        for(int i = 0; i < example.size();i++) {
            System.out.println( "Group " + (i+1) + " score: " + test.scoring(example.get(i)));
        }
    //      ArrayList<Preference> curPrefer = stu.getPrefer();
         //   for(Preference prefer: curPrefer) {
         //       System.out.println(prefer.getStudent());
          //  }
        //}
    }
}

