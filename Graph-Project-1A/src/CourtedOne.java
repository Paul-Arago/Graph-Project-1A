import java.util.List;

public interface CourtedOne extends Participants{
    void setBalcony(Balcony balcony);
    Balcony getBalcony();
    Suitor getPreferredSuitor(List<Suitor> interestedSuitors);
    int getCapacity();
    void unite(Suitor suitor);

}
