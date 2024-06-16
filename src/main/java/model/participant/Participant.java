package model.participant;

import java.util.Map;
import java.util.List;

/**
 * This interface represents a participant in a matching process.
 *
 * @param <S> This represents the class implementing the interface (S = Self).
 *           For instance, if model.suitor.Suitor implements model.participant.Participant, S = model.suitor.Suitor.
 * @param <O> This represents the opposed class the class implementing the interface (O = Opposite).
 *           For instance, if model.suitor.Suitor implements model.participant.Participant, O = model.courtedone.CourtedOne.
 */
public interface Participant<S extends Participant,O extends Participant> {
    void setupPreferences(List<O> participants);
    Map<O, Integer> getPreferences();
    Object getWrappedObject();
    void unite(O participant);
    void disunite(O participant);
    void disunite();
    Integer getCapacity();
    String getName();
}
