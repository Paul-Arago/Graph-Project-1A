import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class StudentCourtedOne implements CourtedOne {
    private Student student;
    private Integer capacity;
    private Balcony balcony;
    private  Map<Suitor, Integer> preferences;
    private List<Suitor> unitedSuitors;

    public StudentCourtedOne(Student student) {
        this.student = student;
        this.capacity = 1;
        this.preferences = new HashMap<>();
        this.unitedSuitors = new ArrayList<>();
    }

    @Override
    public void setupPreferences(List<Suitor> suitors) {
        for (Entry<School, Integer> entry : student.getPreferences().entrySet()) {
            for (Suitor suitor : suitors) {
                if (entry.getKey().equals((School) suitor.getWrappedObject())) {
                    preferences.put(suitor, entry.getValue());
                }
            }
        }
    }
}
