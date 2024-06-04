import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private Map<School,Integer> preferencesMap;

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
}
