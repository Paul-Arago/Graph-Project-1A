package model.participant.suitor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import model.School;
import model.Student;
import model.participant.courtedone.CourtedOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StudentSuitor implements Suitor {
    private Student student;
    private int id;
    private Integer capacity;
    private Map<CourtedOne, Integer> preferences;

    public StudentSuitor(Student student) {
        this.student = student;
        id = this.hashCode();
        this.capacity = 1;
        this.preferences = new HashMap<>();
    }
    public int getId() {
        return id;
    }


    @Override
    public String getName() {
        return student.getName();
    }
    @Override
    public void setupPreferences(List<CourtedOne> courtedOnes) {
        for (Map.Entry<School, Integer> entry : student.getPreferences().entrySet()) {
            for (CourtedOne courtedOne : courtedOnes) {
                if (entry.getKey().equals((School) courtedOne.getWrappedObject())) {
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
        student.setSchool((School) courtedOne.getWrappedObject());
    }

    @Override
    public Boolean isUnitedTo(CourtedOne courtedOne) {
        if (student.getSchool() !=null) {
            return student.getSchool().equals((School) courtedOne.getWrappedObject());
        } else {
            return false;
        }
    }

    @Override
    public void disunite(CourtedOne courtedOne) {
        student.setSchool(null);
    }

    @Override
    public void disunite() {
        student.setSchool(null);
    }

    @Override
    public Map<CourtedOne, Integer> getPreferences() {
        return this.preferences;
    }

    @Override
    public Object getWrappedObject() {
        return student;
    }

    @Override
    public void removePreference(CourtedOne courtedOne) {
        student.getPreferences().remove((School) courtedOne.getWrappedObject());
        preferences.remove(courtedOne);
    }
}
