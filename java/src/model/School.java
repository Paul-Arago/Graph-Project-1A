package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {
    private String name;
    private List<Student> students;
    private int capacity;
    private Map<Student, Integer> preferences;

    public School(String name, int capacity) {
        this.name = name;
        this.preferences = new HashMap<Student, Integer>();
        this.students = new ArrayList<Student>();
        this.capacity = capacity;
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
}

