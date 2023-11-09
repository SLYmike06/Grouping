import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class Student {
    boolean inGroup;
    private  String name;
    private  ArrayList<Preference>  prefer;
    public Student(String name) {
        this.name = name;
        prefer = new ArrayList<Preference>();
        inGroup = false;
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

    public void setInGroup(boolean inGroup) {
        this.inGroup = inGroup;
    }

    public boolean equStu(Student stu) {
        return this.name.equals(stu.name);
    }

    public String toString() {
        String output = "name: " + name;
        for (Preference curPreference: prefer ) {
            output += " preference: " + curPreference;
        }
        return output;
    }
}
