import java.util.HashMap;
import java.util.Map;

public class Student implements SeekerInterface<School> {
    private String name;
    private Map<School,Integer> preferencesMap;
    private Balcony balcony;

    public Student(String name) {
        this.name = name;
        this.preferencesMap = new HashMap<School,Integer>();
    }

    public String getName() {
        return name;
    }

    public Map<School, Integer> getPreferencesMap() {
        return preferencesMap;
    }

    public School getFirstPreference() {
        return preferencesMap.entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null);
    }

    public Balcony getBalcony() {
        return balcony;
    }

    public void setBalcony(Balcony balcony) {
        this.balcony = balcony;
    }

}
