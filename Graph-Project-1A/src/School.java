import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Student> students;
    private String name;
    private List<Integer> preferences;
    private int capacity;

    public School(String name, List<Integer> preferences, int capacity) {
        this.name = name;
        this.preferences = preferences;
        this.students = new ArrayList<Student>();
        this.capacity = 5; // TODO : What way to decide capacity
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPreferences() {
        return preferences;
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
}

