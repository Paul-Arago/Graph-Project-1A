package model.participant.courtedone;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import model.Balcony;
import model.School;
import model.Student;
import model.participant.suitor.Suitor;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SchoolCourtedOne implements CourtedOne {
    private int id;
    @JsonIgnore
    private School school;

    private Integer capacity;
    @JsonIgnore
    private Balcony balcony;
    @JsonIgnore
    private  Map<Suitor, Integer> preferences;
    @JsonIgnore
    private List<Suitor> unitedSuitors;

    public SchoolCourtedOne(School school) {
        this.school = school;
        this.id = this.hashCode();
        this.capacity = school.getCapacity();
        this.preferences = new HashMap<>();
        this.unitedSuitors = new ArrayList<>();
    }

    // For json parsing
    public List<String> getCurrentSelectedSuitors() {
        List<String> unitedTo = new ArrayList<>();
        for (Suitor suitor : unitedSuitors) {
            unitedTo.add(suitor.getName());
        }
        return unitedTo;
    }

    // For json parsing
    public Map<String, Integer> getAllPreferences() {
        Map<String, Integer> allPreferences = new HashMap<>();
        for (Entry<Suitor, Integer> entry : preferences.entrySet()) {
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
    public void setupPreferences(List<Suitor> suitors) {
        for (Entry<Student, Integer> entry : school.getPreferences().entrySet()) {
            for (Suitor suitor : suitors) {
                if (entry.getKey().equals((Student) suitor.getWrappedObject())) {
                    preferences.put(suitor, entry.getValue());
                }
            }
        }
    }

    @Override
    public void setBalcony(Balcony balcony) {
        this.balcony = balcony;
    }

    @Override
    public Balcony getBalcony() {
        return balcony;
    }

    /**
     * This method will take the nth first students present among the interested suitors,
     * then compare them with the students already in the school.
     * If one (or more) of students who are already present then that student will be sent
     *
     * @param interestedSuitors
     * @return
     */
    @Override
    public  List<Suitor> getPreferredSuitors(List<Suitor> interestedSuitors) {
        
        List<Suitor> preferredSuitors = new ArrayList<>();

        for (Suitor unitedSuitor : unitedSuitors) {
            if (!interestedSuitors.contains(unitedSuitor)) {
                interestedSuitors.add(unitedSuitor);
            }
        }

        Map<Suitor, Integer> localPreferences = new HashMap<>(preferences); // Copy the preferences to a local map
        localPreferences.keySet().retainAll(interestedSuitors); // Keep only the interested suitors
        List<Entry<Suitor, Integer>> sortedPreferences = new ArrayList<>(localPreferences.entrySet());
        sortedPreferences.sort(Entry.comparingByValue()); // Sort the preferences by value

        for (Entry<Suitor, Integer> entry : sortedPreferences) {
            if (preferredSuitors.size() == capacity) {
                break;
            } else {
                preferredSuitors.add(entry.getKey());
            }
        }
    
        return preferredSuitors;
    }

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public Map<Suitor, Integer> getPreferences() {
        return Map.of();
    }

    @Override
    public void unite(Suitor suitor) {
        unitedSuitors.add(suitor);
        school.addStudent((Student) suitor.getWrappedObject());
    }

    @Override
    public void disunite(Suitor suitor) {
        unitedSuitors.remove(suitor);
        school.removeStudent((Student) suitor.getWrappedObject());
    }

    @Override
    public void disunite() {
        unitedSuitors.clear();
        school.removeAllStudents(); 
    }

    @JsonIgnore
    @Override
    public Object getWrappedObject() {
        return school;
    }

    @Override
    public String toString() {
        return school.getName() + "'s wrapper";
    }
}
