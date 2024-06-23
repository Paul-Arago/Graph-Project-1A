package model;

import model.participant.courtedone.CourtedOne;
import model.participant.suitor.Suitor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Court in the context of the application.
 * It contains lists of suitors, courted ones and balconies.
 */
public class Court {

    // List of all balconies in the court.
    private List<Balcony> balconies;

    // List of all courted ones in the court.
    private final List<CourtedOne> courtedOnes;

    // List of all suitors in the court.
    private final List<Suitor> suitors;

    /**
     * Constructor for the model.Court class.
     *
     * @param suitors List of all suitors in the court.
     * @param courtedOnes List of all courted ones in the court.
     */
    public Court(List<Suitor> suitors, List<CourtedOne> courtedOnes) {
        this.suitors = suitors;
        this.courtedOnes = courtedOnes;
        this.balconies = new ArrayList<>();
    }

    /**
     * Returns the list of courted ones in the court.
     *
     * @return List of courted ones.
     */
    public List<CourtedOne> getCourtedOnes() {
        return courtedOnes;
    }

    /**
     * Returns the list of suitors in the court.
     *
     * @return List of suitors.
     */
    public List<Suitor> getSuitors() {
        return suitors;
    }

    /**
     * Adds a new balcony to the list of balconies in the court.
     *
     * @param balcony The balcony to be added.
     */
    public void addBalcony(Balcony balcony) {
        balconies.add(balcony);
    }

    /**
     * Returns the list of balconies in the court.
     *
     * @return List of balconies.
     */
    public List<Balcony> getBalconies() {
        return balconies;
    }

}