import java.util.Map;

public interface Suitor extends Participants{
    CourtedOne getFirstPreference();
    Map<CourtedOne, Integer> getPreferences();
    int getCapacity();
    void unite(CourtedOne courtedOne);
}
