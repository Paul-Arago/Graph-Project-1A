import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolSuitor implements Suitor {
    private School school;
    private Integer capacity;
    private Map<CourtedOne, Integer> preferences;
    private Boolean isUnited;

    public SchoolSuitor(School school) {
        this.school = school;
        this.capacity = school.getCapacity();
        this.isUnited = false;
        this.preferences = new HashMap<>();
    }

    @Override
    public void setupPreferences(List<CourtedOne> courtedOnes) {
        for (Map.Entry<Student, Integer> entry : school.getPreferences().entrySet()) {
            for (CourtedOne courtedOne : courtedOnes) {
                if (entry.getKey().equals((Student) courtedOne.getWrappedObject())) {
                    preferences.put(courtedOne, entry.getValue());
                }
            }
        }
    }

    @Override
    public CourtedOne getFirstPreference() {
        Map.Entry<CourtedOne, Integer> minEntry = Collections.min(preferences.entrySet(), Map.Entry.comparingByValue());
        return minEntry.getKey();
    }

    @Override
    public  List<CourtedOne> getFirstPreferences(Integer n) {
        List<CourtedOne> firstPreferences = new ArrayList<>();
        List<Map.Entry<CourtedOne, Integer>> entries = new ArrayList<>(preferences.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        for (int i = 0; i < n; i++) {
            firstPreferences.add(entries.get(i).getKey());
        }
        return firstPreferences;
    }

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public void unite(CourtedOne courtedOne) {
        school.addStudent((Student) courtedOne.getWrappedObject());
        isUnited = true;
    }

    @Override
    public void disunite(CourtedOne courtedOne) {
        school.removeStudent((Student) courtedOne.getWrappedObject());
        isUnited = false;
    }

    @Override
    public void disunite() {
        school.removeAllStudents();
        isUnited = false;
    }

    @Override
    public void seperate() {
        school.removeAllStudents();
        isUnited = false;
    }

    @Override
    public Map<CourtedOne, Integer> getPreferences() {
        return preferences;
    }

    public School getSchool() {
        return school;
    }

    @Override
    public Object getWrappedObject() {
        return school;
    }

    @Override
    public Boolean isUnited() {
        return isUnited;
    }

    @Override
    public Boolean isUnitedTo(CourtedOne courtedOne) {
        return school.getStudents().contains((Student) courtedOne.getWrappedObject());
    }

    @Override
    public void removePreference(CourtedOne courtedOne) {
        school.getPreferences().remove((Student) courtedOne.getWrappedObject());
        preferences.remove(courtedOne);
    }
}
