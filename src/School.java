import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {
    private List<Student> students;
    private String name;
    private Map<Student, Integer> preferences;
    private int capacity;
    private Balcony balcony;
    private SchoolCourtedOne courtedOne; // Temporary solution

    public School(String name, int capacity) {
        this.name = name;
        this.preferences = new HashMap<Student, Integer>();
        this.students = new ArrayList<Student>();
        this.capacity = capacity; // TODO : What way to decide capacity
    }

    public String getName() {
        return name;
    }

    public void setCourtedOne(SchoolCourtedOne courtedOne) {
        this.courtedOne = courtedOne;
    }

    public CourtedOne getCourtedOne() {
        return courtedOne;
    }

    public Map<Student, Integer> getPreferencesMap() {
        return preferences;
    }

    public int getPreference(Student student) {
        return preferences.get(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Balcony getBalcony() {
        return balcony;
    }

    public void setBalcony(Balcony balcony) {
        this.balcony = balcony;
    }

    
}
