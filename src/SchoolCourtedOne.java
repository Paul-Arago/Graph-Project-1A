import java.util.List;

public class SchoolCourtedOne implements CourtedOne {
    private School school;
    private int capacity;
    private Balcony balcony;

    public SchoolCourtedOne(School school) {
        this.school = school;
        this.capacity = school.getCapacity();
        this.school.setCourtedOne(this);
    }

    @Override
    public void setBalcony(Balcony balcony) {
        this.balcony = balcony;
    }

    @Override
    public Balcony getBalcony() {
        return balcony;
    }

    @Override
    public Suitor getPreferredSuitor(List<Suitor> interestedSuitors) {
        Suitor preferredSuitor = null;

        for (Suitor suitor : interestedSuitors) {
            if (preferredSuitor == null) {
                preferredSuitor = suitor;
            } else {
                if (school.getPreference((Student) suitor.getWrappedObject()) > school.getPreference((Student) preferredSuitor.getWrappedObject())) {
                    preferredSuitor = suitor;
                }
            }
        }
        
        return preferredSuitor;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void unite(Suitor suitor) {
        school.addStudent((Student) suitor.getWrappedObject());
    }

    @Override
    public Boolean isAtCapacity() {
        return school.getStudents().size() >= capacity;
    }

    public Object getWrappedObject() {
        return school;
    }
}
