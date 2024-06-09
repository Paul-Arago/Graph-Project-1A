import java.util.List;

public class SchoolCourtedOne implements CourtedOne {
    private School school;
    private int capacity;
    private Balcony balcony;

    public SchoolCourtedOne(School school) {
        this.school = school;
        this.capacity = school.getCapacity();
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
    public Suitor getPreference(List<Suitor> interestedSuitors) {
        return null;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void unite(Suitor suitor) {
        
    }

    @Override
    public Boolean isAtCapacity() {
        return null;
    }

    
}
