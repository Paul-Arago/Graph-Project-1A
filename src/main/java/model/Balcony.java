package model;

import model.participant.courtedone.CourtedOne;
import model.participant.suitor.Suitor;

import java.util.ArrayList;
import java.util.List;

public class Balcony {

    // The courted one at the balcony.
    final private CourtedOne courtedOne;

    // List of all suitors at the balcony.
    private List<Suitor> suitors;

    /**
     * Constructor for the model.Balcony class.
     * 
     * @param courtedOne The courted one at the balcony.
     */
    public Balcony(CourtedOne courtedOne) {
        this.courtedOne = courtedOne;
        suitors = new ArrayList<>();
    }

    /**
     * This method adds a suitor to the balcony.
     * 
     * @param suitor The suitor to be added to the balcony.
     */
    public void addSuitor(Suitor suitor) {
        this.suitors.add(suitor);
    }

    public void removeAllSuitors() {
        suitors.clear();
    }

    public CourtedOne getCourtedOne() {
        return courtedOne;
    }

    public List<Suitor> getSuitors() {
        return suitors;
    }

}