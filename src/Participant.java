import java.util.Map;
import java.util.List;

/**
 * This interface represents a participant in a matching process.
 *
 * @param <S> This represents the class implementing the interface (S = Self).
 *           For instance, if Suitor implements Participant, S = Suitor.
 * @param <O> This represents the opposed class the class implementing the interface (O = Opposite).
 *           For instance, if Suitor implements Participant, O = CourtedOne.
 */
public interface Participant<S extends Participant,O extends Participant> {
    void setupPreferences(List<O> participants);
    Map<O, Integer> getPreferences();
    Object getWrappedObject();
    void unite(O participant);
    void disunite(O participant);
    Boolean isUnited();
    Integer getCapacity();
}
