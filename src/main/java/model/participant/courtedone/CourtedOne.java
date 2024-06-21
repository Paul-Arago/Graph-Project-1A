package model.participant.courtedone;

import model.Balcony;
import model.participant.Participant;
import model.participant.suitor.Suitor;

import java.util.List;
import java.util.Map;

public interface CourtedOne extends Participant<CourtedOne, Suitor> {
    void setupPreferences(List<Suitor> suitors);
    Map<Suitor, Integer> getPreferences();
    void unite(Suitor suitor);
    void disunite(Suitor suitor);
    void setBalcony(Balcony balcony);
    Balcony getBalcony();
    List<Suitor> getPreferredSuitors(List<Suitor> interestedSuitors);

    /**
     * This method returns a list of the current selected suitors.
     * This method is used in order to generate the output file.
     *
     * @return List of preferences
     */
    List<String> getCurrentSelectedSuitors();
}
