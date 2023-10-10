import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class Student {
    private  String name;
    private  ArrayList<Preference>  prefer;
    public Student(String name) {
        this.name = name;
        prefer = new ArrayList<Preference>();
    }
    public String getName() {
        return name;
    }
    public ArrayList<Preference> getPrefer() {
        return prefer;
    }

    public void setPrefer(ArrayList<Preference> prefer) {
        this.prefer = prefer;
    }

    public String toString() {
        String output = "name: " + name;
        for (Preference curPreference: prefer ) {
            output += " preference: " + curPreference;
        }
        return output;
    }
}
