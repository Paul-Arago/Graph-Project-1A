import java.util.Map;
import java.util.List;

public interface Suitor extends Participant<Suitor, CourtedOne> {
    void setupPreferences(List<CourtedOne> courtedOnes);
    Map<CourtedOne, Integer> getPreferences();
    void unite(CourtedOne courtedOne);
    void disunite(CourtedOne courtedOne);
    CourtedOne getFirstPreference();
    void removePreference(CourtedOne courtedOne);
    void seperate();
}
