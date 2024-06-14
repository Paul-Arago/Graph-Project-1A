package model;

import model.participant.courtedone.CourtedOne;
import model.participant.suitor.Suitor;

import java.util.ArrayList;
import java.util.List;

public class Court {

    // List of all balconies in the court.
    private List<Balcony> balconies;
    
    // List of all courted ones in the court.
    private List<CourtedOne> courtedOnes;

    // List of all suitors in the court.
    private List<Suitor> suitors;

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

    public List<CourtedOne> getCourtedOnes() {
        return courtedOnes;
    }

    public List<Suitor> getSuitors() {
        return suitors;
    }

    public void addBalcony(Balcony balcony) {
        balconies.add(balcony);
    }

    public List<Balcony> getBalconies() {
        return balconies;
    }

}
