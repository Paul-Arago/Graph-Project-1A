import java.util.ArrayList;
import java.util.List;

public class Balcony {

    // The courted one at the balcony.
    private CourtedOne courtedOne;

    // List of all suitors at the balcony.
    private List<Suitor> suitors;

    /**
     * Constructor for the Balcony class.
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

    public void setCourtedOne(CourtedOne courtedOne) {
        this.courtedOne = courtedOne;
    }

    public boolean hasCourtedOne() {
        return courtedOne != null;
    }
}