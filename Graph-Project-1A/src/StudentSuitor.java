import java.util.Map;

public class StudentSuitor implements Suitor {
    private Student student;
    private int capacity;

    public StudentSuitor(Student student) {
        this.student = student;
        this.capacity = 1;
    }

    @Override
    public CourtedOne getFirstPreference() {
        return null;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void unite(CourtedOne courtedOne) {

    }

    @Override
    public Boolean isAtCapacity() {
        return null;
    }

    @Override
    public Map<CourtedOne, Integer> getPreferences() {
        return null;
    }

}
