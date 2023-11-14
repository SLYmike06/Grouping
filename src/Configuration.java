import java.util.ArrayList;

public class Configuration {
    int configScore = 0;
    ArrayList<Integer> scores;
    ArrayList<Group> allGP;
    double SD;

    public Configuration(int configScore, ArrayList<Group> allGP, ArrayList<Integer> scores, double SD) {
        this.configScore = configScore;
        this.allGP = allGP;
        this.scores = scores;
        this.SD = SD;
    }

    public Configuration() {
        configScore = 0;
        allGP = new ArrayList<Group>();
    }

    public int getConfigScore() {
        return configScore;
    }

    public double getSD() {
        return SD;
    }

    public ArrayList<Group> getAllGP() {
        return allGP;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setConfigScore(int configScore) {
        this.configScore = configScore;
    }

    public void setAllGP(ArrayList<Group> allGP) {
        this.allGP = allGP;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public void setSD(double SD) {
        this.SD = SD;
    }
}
