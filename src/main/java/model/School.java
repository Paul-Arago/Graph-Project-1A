package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class School {
    private String name;
    private int id;
    private List<Student> students;
    private int capacity;
    private Map<Student, Integer> preferences;

    public School(String name, int capacity) {
        this.name = name;
        this.id = this.hashCode();
        this.preferences = new HashMap<Student, Integer>();
        this.students = new ArrayList<Student>();
        this.capacity = capacity;
    }

public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Student, Integer> getPreferences() {
        return preferences;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeAllStudents() {
        students.clear();
    }

    @Override
    public String toString() {
        return name;
    }
}

