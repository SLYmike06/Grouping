import java.util.ArrayList;
import java.util.Random;

public class Configuration {
    int configScore = 0;

    int numNoPrefer;
    ArrayList<Integer> scores;
    ArrayList<Student> studentList;
    int groupSize;
    ArrayList<Group> groupList;
    ArrayList<String> index;
    double sd;

    public Configuration(int configScore, ArrayList<Group> groupList, ArrayList<Integer> scores, double sd, ArrayList<Student> studentList, int groupSize) {
        this.configScore = configScore;
        this.groupList = groupList;
        this.scores = scores;
        this.sd = sd;
        numNoPrefer = 0;
        this.studentList = studentList;
        this.groupSize = groupSize;
    }

    public Configuration(ArrayList<Student> studentList, int groupSize) {
        configScore = 0;
        numNoPrefer = 0;
        groupList = new ArrayList<Group>();
        index = new ArrayList<String>();
        this.studentList = studentList;
        this.groupSize = groupSize;
        scores = new ArrayList<>();

    }

    public void generateRandomGroups() {
        ArrayList<Student> copyList = new ArrayList<>(studentList);
        int groupplus = studentList.size() % groupSize;
        int count = 0;
        for (int i = 0; i < groupplus; i++) {
            Group nwGroup = new Group();
            ArrayList<Student> groupStudents = new ArrayList<>();
            for (int j = 0; j < (groupSize+1); j++) {
                count++;
                Random rand = new Random();
                Student possible = copyList.get(rand.nextInt(copyList.size()));
                groupStudents.add(possible);
                copyList.remove(possible);
            }
            nwGroup.setStudentList(groupStudents);
            nwGroup.score();
            scores.add(nwGroup.getScore());
            groupList.add(nwGroup);
        }
        int normalGroup = (studentList.size() - count) / groupSize;
        for (int i = 0; i < normalGroup; i++) {
            Group nwGroup = new Group();
            ArrayList<Student> groupStudents = new ArrayList<>();
            for (int j = 0; j < (groupSize); j++) {
                Random rand = new Random();
                Student possible = copyList.get(rand.nextInt(copyList.size()));
                groupStudents.add(possible);
                copyList.remove(possible);
            }
            nwGroup.setStudentList(groupStudents);
            nwGroup.score();
            scores.add(nwGroup.getScore());
            groupList.add(nwGroup);
        }


    }

    public void generateSpecificGroup(int[] index, int[] template) {
       for(int i = 0; i < index.length;i++) {
           Group nwGroup = new Group();
           ArrayList<Student> groupStudents = new ArrayList<>();
           for (int j = 0; j < (groupSize+1); j++) {
               groupStudents.add(studentList.get(index[0]));
           }
           nwGroup.setStudentList(groupStudents);
           nwGroup.score();
           scores.add(nwGroup.getScore());
           groupList.add(nwGroup);
       }
    }

    public void score() {
        configScore = 0;
        for(int i = 0; i < groupList.size();i++) {
            configScore += groupList.get(i).getScore();
        }
        double mean = (double) configScore / groupList.size();
        double numeratorSum = 0.0;
            for (int j = 0; j < groupList.size(); j++) {
                numeratorSum = numeratorSum + Math.pow((scores.get(j) - mean), 2);
            }
            sd = Math.sqrt(numeratorSum / groupList.size());
    }



    public int getConfigScore() {
        return configScore;
    }

    public double getSD() {
        return sd;
    }

    public ArrayList<Group> getAllGP() {
        return groupList;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setConfigScore(int configScore) {
        this.configScore = configScore;
    }

    public void setAllGP(ArrayList<Group> allGP) {
        this.groupList = allGP;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public void setSD(double SD) {
        this.sd = SD;
    }
}
