import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private Map<School,Integer> preferences;
    private Balcony balcony;
    private School school;

    public Student(String name) {
        this.name = name;
        this.preferences = new HashMap<School,Integer>();
    }

    public String getName() {
        return name;
    }

    public Map<School, Integer> getPreferences() {
        return preferences;
    }

    public School getFirstPreference() {
        return preferences.entrySet().stream()
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
