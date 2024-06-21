package model.participant.suitor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import model.School;
import model.Student;
import model.participant.courtedone.CourtedOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SchoolSuitor implements Suitor {
    @JsonIgnore
    private School school;
    private int id;
    private Integer capacity;
    @JsonIgnore
    private Map<CourtedOne, Integer> preferences;

    public SchoolSuitor(School school) {
        this.school = school;
        id = this.hashCode();
        this.capacity = school.getCapacity();
        this.preferences = new HashMap<>();
    }

    // For json parsing
    public List<String> getCurrentSelectedCourtedOnes() {
        List<String> unitedTo = new ArrayList<>();
        for (Student student : school.getStudents()) {
            unitedTo.add(student.getName());
        }
        return unitedTo;
    }

    // For json parsing
    public Map<String, Integer> getAllPreferences() {
        Map<String, Integer> allPreferences = new HashMap<>();
        for (Map.Entry<CourtedOne, Integer> entry : preferences.entrySet()) {
            allPreferences.put(entry.getKey().getName(), entry.getValue());
        }
        return allPreferences;
    }

        // For json parsing
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return school.getName();
    }

    @Override
    public void setupPreferences(List<CourtedOne> courtedOnes) {
        for (Map.Entry<Student, Integer> entry : school.getPreferences().entrySet()) {
            for (CourtedOne courtedOne : courtedOnes) {
                if (entry.getKey().equals((Student) courtedOne.getWrappedObject())) {
                    preferences.put(courtedOne, entry.getValue());
                }
            }
        }
    }

    @Override
    public  List<CourtedOne> getFirstPreferences(Integer n) {
        List<CourtedOne> firstPreferences = new ArrayList<>();
        List<Map.Entry<CourtedOne, Integer>> entries = new ArrayList<>(preferences.entrySet());
        entries.sort(Map.Entry.comparingByValue());
        for (int i = 0; i < n; i++) {
            firstPreferences.add(entries.get(i).getKey());
        }
        return firstPreferences;
    }

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public void unite(CourtedOne courtedOne) {
        school.addStudent((Student) courtedOne.getWrappedObject());
    }

    @Override
    public void disunite(CourtedOne courtedOne) {
        school.removeStudent((Student) courtedOne.getWrappedObject());
    }

    @Override
    public void disunite() {
        school.removeAllStudents();
    }

    @Override
    public Map<CourtedOne, Integer> getPreferences() {
        return preferences;
    }

    @JsonIgnore
    @Override
    public Object getWrappedObject() {
        return school;
    }

    @Override
    public Boolean isUnitedTo(CourtedOne courtedOne) {
        return school.getStudents().contains((Student) courtedOne.getWrappedObject());
    }

    @Override
    public void removePreference(CourtedOne courtedOne) {
        school.getPreferences().remove((Student) courtedOne.getWrappedObject());
        preferences.remove(courtedOne);
    }
}
