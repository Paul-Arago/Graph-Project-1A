package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashMap;
import java.util.Map;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
    private String name;
    private int id;
    private Map<School,Integer> preferences;
    private School school;

    public Student(String name) {
        this.name = name;
        this.id = this.hashCode();
        this.preferences = new HashMap<School,Integer>();
    }

    public int getId() {
        return id;
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
