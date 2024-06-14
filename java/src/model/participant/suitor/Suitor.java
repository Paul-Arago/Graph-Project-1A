package model.participant.suitor;

import model.participant.Participant;
import model.participant.courtedone.CourtedOne;

import java.util.Map;
import java.util.List;

public interface Suitor extends Participant<Suitor, CourtedOne> {
    void setupPreferences(List<CourtedOne> courtedOnes);
    Map<CourtedOne, Integer> getPreferences();
    void unite(CourtedOne courtedOne);
    Boolean isUnitedTo(CourtedOne courtedone);
    void disunite(CourtedOne courtedOne);

    /**
     * Get the first n first preferences
     * @param n
     * @return
     */
    List<CourtedOne> getFirstPreferences(Integer n);
    void removePreference(CourtedOne courtedOne);
}
