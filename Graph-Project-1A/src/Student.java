import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private Map<School,Integer> preferencesMap;
    private Balcony balcony;
    private School school;

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

    public void setSchool(School school) {
        this.school = school;
    }

    public void setBalcony(Balcony balcony) {
        this.balcony = balcony;
    }

    public School getSchool(){
        return school;
    }

}
