package model;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private Map<School,Integer> preferences;
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

    public void setSchool(School school) {
        this.school = school;
    }

    public School getSchool(){
        return school;
    }

}
