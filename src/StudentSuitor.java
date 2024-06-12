import java.util.HashMap;
import java.util.Map;

public class StudentSuitor implements Suitor {
    private Student student;
    private int capacity;
    private Map<CourtedOne, Integer> preferences;

    public StudentSuitor(Student student) {
        this.student = student;
        this.capacity = 1;
        this.createSuitorMap();
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
        student.setSchool((School) courtedOne.getWrappedObject());
    }

    @Override
    public Boolean isAtCapacity() {
        return student.getSchool() != null;
    }
    private void createSuitorMap(){
        preferences = new HashMap<>();

        Map<School, Integer> mapStudentPreferences = student.getPreferencesMap();

        for(Map.Entry<School, Integer> entry : mapStudentPreferences.entrySet()){
            preferences.put(entry.getKey().getCourtedOne(), entry.getValue()); //new SchoolCourtedOne(entry.getKey())
        }
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

}
