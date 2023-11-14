import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


public class Grouping {
    public static void main(String[] args) throws IOException {
        Grouping test = new Grouping();
        //test.printStudents();
        test.verifyData();
        //ArrayList<Group> example = test.demoGroup();
        // test.printStudents(example);
        //for(int i = 0; i < example.size();i++) {
        // System.out.println( "Group " + (i+1) + " score: " + test.scoring(example.get(i)));
        //}
        for(int i = 0; i < 100000000;i++ ) {
            ArrayList<Group> testGroupsList = test.randCluster();
            Configuration config = new Configuration();
            config.setConfigScore(test.configurationScore(testGroupsList));
            ArrayList<Integer>scores = new ArrayList<Integer>();
            for(int j = 0; j < testGroupsList.size();j++) {
                scores.add(testGroupsList.get(j).getScore());
            }
            config.setScores(scores);
            StandardDeviation sd = new StandardDeviation(scores);
            config.setAllGP(testGroupsList);
            config.setSD(sd.SD());
           // test.printStudents(testGroupsList);
           // System.out.println("Configuration Score: " + config.getConfigScore());
           // System.out.println("SD value: " + config.getSD());
           // System.out.println("----------------------------------------------------------------------------------------------------------------");

        }
    // ArrayList<Group> testGroupsList = test.randCluster();
    // test.printStudents(testGroupsList);
     //   Group np = new Group();
     //   ArrayList<Student> testStu = new ArrayList<Student>();
      //  testStu.add(test.studentList.get(0));
      //  testStu.add(test.studentList.get(1));
      //  testStu.add(test.studentList.get(2));
      //  np.setStudentList(testStu);
      //  System.out.println(test.scoring(np));


        //test.printStudents();
    //      ArrayList<Preference> curPrefer = stu.getPrefer();
    //   for(Preference prefer: curPrefer) {
    //       System.out.println(prefer.getStudent());
    //  }
    //}
}
    int num;
    int groupSize;
    Preference prefer;
    ArrayList<Student> studentList;

    public Grouping() throws IOException {
        readFile();
    }

    public void readFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("./Untitled spreadsheet - Sheet1 (4).csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
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
                    if (csvRecord.get(i).length() > 0 || csvRecord.get(i + 1).length() > 0) {
                        // System.out.println(" desire: " + csvRecord.get(i));
                        newStudentPreference.add(new Preference(csvRecord.get(i).charAt(0), csvRecord.get(i + 1)));
                    }
                }
                newStudent.setPrefer(newStudentPreference);
                studentList.add(newStudent);
            }
        }
    }

    public int scoring(Group group) {
        int groupScore = 0;
        for (int i = 0; i < group.stuList.size(); i++) {
            Student curr = group.stuList.get(i);
            ArrayList<Preference> listOfPrefer = curr.getPrefer();
            double studentpoints = 0;
            int preferenceCount = 0;
            for (int k = 0; k < listOfPrefer.size(); k++) {
                Student potentialStudent = listOfPrefer.get(k).getStudent();
                if (group.stuList.contains(potentialStudent) && !potentialStudent.equStu(curr) ) {
                    preferenceCount++;
                }
            }
            if(preferenceCount > 0) {
                studentpoints = Math.pow(preferenceCount,4)/-6 + 13 * Math.pow(preferenceCount,3)/6 +
                        31 * Math.pow(preferenceCount,2)/-3 + 70 * preferenceCount / 3.0 - 5;
            } else {
                studentpoints = 0;
            }
            groupScore += (int) Math.round(studentpoints);
        }
        return groupScore;
    }

    public int configurationScore(ArrayList<Group>groups) {
        int totalScore = 0;
        for(int i = 0; i < groups.size();i++) {
            totalScore += groups.get(i).getScore();
        }
        return totalScore;
    }

    public ArrayList<Group> randCluster() {
        ArrayList<Group> randGroup = new ArrayList<Group>();
        ArrayList<Student> copyList = new ArrayList<>(studentList);
        for (int i = 0; i < num / groupSize; i++) {
            Group nwGroup = new Group();
            ArrayList<Student> groupStudents = new ArrayList<>();
            for (int j = 0; j < (groupSize); j++) {
                Random rand = new Random();
                Student possible = copyList.get(rand.nextInt(copyList.size()));
                //int index = studentList.indexOf(possible);
                // while(possible.inGroup) {
                //   possible = studentList.get(rand.nextInt(num));
                // }
                groupStudents.add(possible);
                copyList.remove(possible);
                //studentList.get(index).setInGroup(true);
            }
            nwGroup.setStudentList(groupStudents);
            int score = scoring(nwGroup);
            nwGroup.setScore(score);
            randGroup.add(nwGroup);
        }
        return randGroup;
    }

    public ArrayList<Group> PreferenceCluster() {
        ArrayList<Group> randGroup = new ArrayList<Group>();

        return randGroup;
    }


    //if the group contains any of the prefers
//    public boolean contain(ArrayList<Preference> prefer, Group gp) {
//        for(int i = 0; i < prefer.size();i++) {
//            if(prefergp.studentList)
//        }
//        return false;
//    }
    public boolean verifyData() {
        for (int j = 0; j < studentList.size(); j++) {
            ArrayList<Preference> curStuPrefer = studentList.get(j).getPrefer();
            for (int i = 0; i < curStuPrefer.size(); i++) {
                Student match = findMatch(curStuPrefer.get(i).name);
                if (match == null) {
                    System.out.println("the Student named: " + prefer.name + " is not a valid name");
                    return false;
                } else {
                    curStuPrefer.get(i).setStudent(match);
                }
            }
        }
        // studentList = studentList;
        return true;
    }

    /**
     * searches for student that matches the name
     *
     * @param name
     * @return the matching student or null if not found
     */
    public Student findMatch(String name) {
        for (Student stu : studentList) {
            if (name.equals(stu.getName())) {
                return stu;
            }
        }
        return null;
    }

    public void printStudents(ArrayList<Group> groups) {
        for (Group grp : groups) {
            System.out.println(grp + "            Group Score: " + grp.getScore());
        }
    }

    public void printStudents() {
        for (Student curStu : studentList) {
            System.out.println(curStu);
        }
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<Group> demoGroup() {
        ArrayList<Group> groups = new ArrayList<Group>();
        int count = 1;
        for (int i = 0; i < 9; i += 3) {
            Group newGroup = new Group();
            ArrayList<Student> student = new ArrayList<Student>();
            student.add(studentList.get(i));
            student.add(studentList.get(i + 1));
            student.add(studentList.get(i + 2));
            newGroup.setStudentList(student);
            newGroup.setGroupNum(count);
            groups.add(newGroup);
            count++;
            System.out.println("test");
        }
        return groups;
    }
}

