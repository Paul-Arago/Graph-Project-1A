import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentSuitor implements Suitor {
    private Student student;
    private Integer capacity;
    private Map<CourtedOne, Integer> preferences;
    private Boolean isUnited;

    public StudentSuitor(Student student) {
        this.student = student;
        this.capacity = 1;
        this.isUnited = false;
        this.preferences = new HashMap<>();
    }

    @Override
    public void setupPreferences(List<CourtedOne> courtedOnes) {
        for (Map.Entry<School, Integer> entry : student.getPreferences().entrySet()) {
            for (CourtedOne courtedOne : courtedOnes) {
                if (entry.getKey().equals((School) courtedOne.getWrappedObject())) {
                    preferences.put(courtedOne, entry.getValue());
                }
            }
        }
    }

    @Override
    public CourtedOne getFirstPreference() {
        Map.Entry<CourtedOne, Integer> maxEntry = Collections.min(preferences.entrySet(), Map.Entry.comparingByValue());
        return maxEntry.getKey();
    }

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public void unite(CourtedOne courtedOne) {
        student.setSchool((School) courtedOne.getWrappedObject());
        isUnited = true;
    }

    @Override
    public void disunite(CourtedOne courtedOne) {

    }

    @Override
    public void seperate() {
        student.setSchool(null);
        isUnited = false;
    }

    @Override
    public Map<CourtedOne, Integer> getPreferences() {
        return this.preferences;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public Object getWrappedObject() {
        return student;
    }

    @Override
    public Boolean isUnited() {
        return isUnited;
    }

    @Override
    public void removePreference(CourtedOne courtedOne) {
        student.getPreferences().remove((School) courtedOne.getWrappedObject());
        preferences.remove(courtedOne);
    }

}
