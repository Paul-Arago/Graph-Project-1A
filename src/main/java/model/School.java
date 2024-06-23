package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a School in the context of the application.
 * It contains a list of students, a map of preferences, and a capacity limit.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class School {
    // The name of the school.
    private final String name;

    // The unique identifier of the school.
    private final int id;

    // The list of students in the school.
    private List<Student> students;

    // The maximum number of students the school can accommodate.
    private final int capacity;

    // The map of student preferences.
    private Map<Student, Integer> preferences;

    /**
     * Constructor for the School class.
     *
     * @param name The name of the school.
     * @param capacity The maximum number of students the school can accommodate.
     */
    public School(String name, int capacity) {
        this.name = name;
        this.id = this.hashCode();
        this.preferences = new HashMap<>();
        this.students = new ArrayList<>();
        this.capacity = capacity;
    }

    /**
     * Returns the unique identifier of the school.
     *
     * @return The unique identifier of the school.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the school.
     *
     * @return The name of the school.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the map of student preferences.
     *
     * @return The map of student preferences.
     */
    public Map<Student, Integer> getPreferences() {
        return preferences;
    }

    /**
     * Returns the list of students in the school.
     *
     * @return The list of students in the school.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Returns the maximum number of students the school can accommodate.
     *
     * @return The maximum number of students the school can accommodate.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Adds a student to the list of students in the school.
     *
     * @param student The student to be added.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Removes a student from the list of students in the school.
     *
     * @param student The student to be removed.
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * Removes all students from the list of students in the school.
     */
    public void removeAllStudents() {
        students.clear();
    }

    /**
     * Returns the name of the school.
     *
     * @return The name of the school.
     */
    @Override
    public String toString() {
        return name;
    }
}