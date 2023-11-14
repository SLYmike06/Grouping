import java.util.ArrayList;

public class StandardDeviation {
    ArrayList<Integer> scores;
    double sum = 0.0;
    double standardDeviation = 0.0;
    double mean = 0.0;
    double res = 0.0;
    double sq = 0.0;

    public StandardDeviation(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    double SD()
    {
        int n = scores.size();

        for (int i = 0; i < n; i++) {
            sum = sum + scores.get(i);
        }

        mean = sum / (n);

        for (int i = 0; i < n; i++) {

            standardDeviation
                    = standardDeviation + Math.pow((scores.get(i) - mean), 2);

        }

        sq = standardDeviation / n;
        res = Math.sqrt(sq);
        return res;
    }
}

