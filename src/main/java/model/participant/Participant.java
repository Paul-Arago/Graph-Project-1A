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

    /**
     * This method sets up the preferences of the participant (S) based on the list of participants (O).
     *
     * @param participants
     */
    void setupPreferences(List<O> participants);

    /**
     * This method returns the preferences of the participant (S) as a map.
     *
     * @return
     */
    Map<O, Integer> getPreferences();

    /**
     * This method returns the wrapped object of the participant (S).
     *
     * @return
     */
    Object getWrappedObject();

    /**
     * This method returns the name of the participant (S).
     *
     * @return
     */
    void unite(O participant);

    /**
     * This method returns the name of the participant (S).
     *
     * @return
     */
    void disunite(O participant);

    /**
     * This method returns the name of the participant (S).
     *
     * @return
     */
    void disunite();

    /**
     * This method returns the name of the participant (S).
     *
     * @return
     */
    Integer getCapacity();

    /**
     * This method returns the name of the participant (S).
     *
     * @return
     */
    String getName();

    /**
     * This method returns a list of the preferences of the participant.
     * This method is used in order to generate the output file.
     *
     * @return
     */
    Map<String, Integer> getAllPreferences();
}
