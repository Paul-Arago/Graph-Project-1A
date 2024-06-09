import java.util.List;

public class Court {

    // List of all balconies in the court.
    private List<Balcony> balconies;

    // List of all suitors in the court.
    private List<Suitor> suitors;

    // List of all courted ones in the court.
    private List<CourtedOne> courtedOnes;

    /**
     * Constructor for the Court class.
     * 
     * @param suitors List of all suitors in the court.
     * @param courtedOnes List of all courted ones in the court.
     */
    public Court(List<Suitor> suitors, List<CourtedOne> courtedOnes) {
        this.suitors = suitors;
        this.courtedOnes = courtedOnes;
    }

    /**
     * This method sets up the balconies in the court.
     */
    public void setupBalconies() {
        
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


}
