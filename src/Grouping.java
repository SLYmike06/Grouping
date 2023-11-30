import java.io.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;


public class Grouping {
    public static void main(String[] args) throws IOException {
        Grouping test = new Grouping();
        ArrayList<Configuration> configList = test.generateNConfig(10000);
        Configuration con = test.pickTopConfigs(configList);
        test.printConfigInfo(con);
    }
    private int num;
    private int groupSize;
    private ArrayList<Student> studentList;

    public Grouping() throws IOException {
        studentList = new ArrayList<>();
        num = 0;
        groupSize = 0;
        readFile();
        verifyData();
    }

    public void readFile() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("./Untitled spreadsheet - Sheet1 (4).csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.getRecordNumber() == 1) {
                    num = Integer.parseInt(csvRecord.get(0));
                } else if (csvRecord.getRecordNumber() == 2) {
                    groupSize = Integer.parseInt(csvRecord.get(0));
                } else {
                    int size = csvRecord.size();
                    Student newStudent = new Student(csvRecord.get(0));
                    ArrayList<Preference> newStudentPreference = new ArrayList<Preference>();
                    for (int i = 1; i < size; i += 2) {
                        if (!csvRecord.get(i).isEmpty() || !csvRecord.get(i + 1).isEmpty()) {
                            // System.out.println(" desire: " + csvRecord.get(i));
                            newStudentPreference.add(new Preference(csvRecord.get(i).charAt(0), csvRecord.get(i + 1)));
                        }
                    }
                    newStudent.setPrefer(newStudentPreference);
                    studentList.add(newStudent);
                }
            }
        }
        catch(Exception e) {

        }
    }
    public boolean verifyData() {
        for (int j = 0; j < studentList.size(); j++) {
            ArrayList<Preference> curStuPrefer = studentList.get(j).getPrefer();
            for (int i = 0; i < curStuPrefer.size(); i++) {
                Student match = findMatch(curStuPrefer.get(i).name);
                if (match == null) {
                    System.out.println("the Student named: " + curStuPrefer.get(i).name + " is not a valid name for " + studentList.get(j).getName());
                    return false;
                } else {
                    curStuPrefer.get(i).setStudent(match);
                }
            }
        }
        return true;
    }

    public Configuration pickTopConfigs(ArrayList<Configuration> list) {
        PriorityQueue<Configuration> pq = new PriorityQueue<Configuration>(new Comparator<Configuration>() {
            public int compare(Configuration s1, Configuration s2) {
                if(s1.getConfigScore() > s2.getConfigScore()) {
                    return -1;
                } else if(s1.getConfigScore() < s2.getConfigScore()) {
                    return 1;
                } else {
                    if(s1.getSD() > s2.getSD()) {
                        return 1;
                    } else if(s1.getSD() < s2.getSD()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });
        pq.addAll(list);
        return pq.peek();
    }
    public void printConfigInfo(Configuration con) {
        System.out.println("The score for the best configuration is: " + con.getConfigScore());
        System.out.println("The groups in this configuration are the following:");
        System.out.println("Standard Deviation for this configuration: " + con.getSD());
        printStudents(con.getAllGP());
    }



    public ArrayList<Configuration> generateNConfig(int n) {
        ArrayList<Configuration> listConfig = new ArrayList<>();
        for(int i = 0; i < n;i++ ) {
            ArrayList<Group> testGroupsList = randCluster();
            Configuration config = new Configuration();
            config.setConfigScore(configurationScore(testGroupsList));
            ArrayList<Integer>scores = new ArrayList<Integer>();
            for(int j = 0; j < testGroupsList.size();j++) {
                scores.add(testGroupsList.get(j).getScore());
            }
            config.setScores(scores);
            StandardDeviation sd = new StandardDeviation(scores);
            config.setAllGP(testGroupsList);
            config.setSD(sd.SD());
            listConfig.add(config);
            System.out.println(config.getConfigScore() + "," + config.getSD());
        }

        return listConfig;
    }




    public ArrayList<Group> geneticCluster() {
        ArrayList<Group> randGroup = new ArrayList<Group>();
        return randGroup;
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

