import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Coordinator {
    private Court court;
    private int round;
    private boolean isFinished;
    private String balconyState;
    
    /**
     * The string representation of all the suitors preferences.
     * This is used to determine if the process is finished.
     * If the string representation of the suitors preferences is the same two rounds in a row, the process is finished.
     */
    String suitorsPreferenceString;

    public Coordinator(Court court) {
        this.court = court;
        this.round = 1;
        isFinished = false;
        this.balconyState = "";
    }

    public void start() {
        System.out.println("Starting process...");

        // Set up the balconies in the court.
        System.out.println("Setting up balconies...");
        setupBalconies();

        // Start the rounds.
        System.out.println("Starting rounds...");

        while (!isFinished()) {
            System.out.println("----------------------------------");
            System.out.println("Round: " + round );

            // Move suitors to preferred balconies.
            System.out.println("Moving suitors to preferred balconies...");
            moveSuitorsToPreferredBalconies();
            //getBalconyState();

            // Make courted ones choose suitors.
            System.out.println("Making courted ones choose suitors and uniting them (temporarily)...");
            courtedOnesChooseSuitors();

            // Remove the courted one if the suitor was not chosen.
            System.out.println("Removing the courted one of the suitors preferences if the suitor was rejected...");
            updateSuitorsPreferences();

            // Move rejected suitors back to the court.
            System.out.println("Moving all suitors back to the court...");
            moveSuitorsToCourt();


            // Print the total number of Suitors, and then the number of remaining Suitors.
            remainingSuitors();

            // Print the total number of CourtedOne, and then the number of remaining CourtedOne.
            remainingCourtedOnes();

            // Print the suitors preferences.
            //System.out.println("Suitors preferences: " + suitorsPreferenceString);

            // Increment the round.
            round++;
        }

        System.out.println("Process completed.");
    }

    private void getBalconyState() {
        String newBalconyState = "";
        for (Balcony balcony : court.getBalconies()) {
            newBalconyState += balcony.getCourtedOne().getWrappedObject().toString() + ": " + balcony.getSuitors().toString() + "\n";
        }
        if (newBalconyState.equals(balconyState)) {
        }
        balconyState = newBalconyState;
    }

    private void moveRejectedSuitorsToCourt() {
        for (Balcony balcony : court.getBalconies()) {
            List<Suitor> suitorsToRemove = new ArrayList<>();
            for (Suitor suitor : balcony.getSuitors()) {
                if (!suitor.isUnited()) {
                    suitorsToRemove.add(suitor);
                }
            }
            balcony.getSuitors().removeAll(suitorsToRemove);
        }

    }

    private void updateSuitorsPreferences() {
        for (Balcony balcony : court.getBalconies()) {
            for (Suitor suitor : balcony.getSuitors()) {
                if (!suitor.isUnited()) {
                    suitor.removePreference(balcony.getCourtedOne());
                }
            }
        }
    }

    public void remainingSuitors() {
        System.out.println("Total number of Suitors: " + court.getSuitors().size());
        System.out.println("Suitors with no preferences left: " + court.getSuitors().stream().filter(suitor -> suitor.getPreferences().isEmpty()).count());
    }

    public void remainingCourtedOnes() {
        System.out.println("Total number of CourtedOne: " + court.getCourtedOnes().size());
    }

    private void setupBalconies() {
        for (CourtedOne courtedOne : court.getCourtedOnes()) {
            Balcony balcony = new Balcony(courtedOne);
            courtedOne.setBalcony(balcony);
            court.addBalcony(balcony);
        }
    }

    private <T extends Participant> boolean updateAreAtCapacity(List<T> participants) {
        boolean areAtCapacity = true;
        for (T participant : participants) {
            if (participant != null) {
                areAtCapacity = false;
                break;
            }
        }

    return areAtCapacity;
    }

    private void moveSuitorsToPreferredBalconies() {
        for (Suitor suitor : court.getSuitors()) {
            if (!suitor.getPreferences().isEmpty()){
                List<CourtedOne> firstPreferences = suitor.getFirstPreferences(suitor.getCapacity());
                for (CourtedOne preference : firstPreferences) {
                    preference.getBalcony().addSuitor(suitor);
                }
            }
        }
    }

    private void courtedOnesChooseSuitors() {
        for (Balcony balcony : court.getBalconies()) {
            if (!balcony.getSuitors().isEmpty()){
                CourtedOne courtedOne = balcony.getCourtedOne();
                List<Suitor> preferredSuitors = courtedOne.getPreferredSuitors(balcony.getSuitors());
                disuniteAllBalcony(balcony);

                if (preferredSuitors != null) {
                    unite(preferredSuitors, courtedOne);
                }
            }
        }
    }

    private void unite(List<Suitor> suitors, CourtedOne courtedOne) {

        for (Suitor suitor : suitors) {
            suitor.unite(courtedOne);
            courtedOne.unite(suitor);
        }
    }

    private void disuniteAllBalcony (Balcony balcony) {
        balcony.getCourtedOne().disunite();
        for (Suitor suitor : balcony.getSuitors()) {
            suitor.disunite();
        }
    }

    private void moveSuitorsToCourt() {
        for (Balcony balcony : court.getBalconies()) {
            balcony.removeAllSuitors();
        }
    }

    /**
     * The only termination condition is when every suitor has the same preferences two rounds in a row.
     */
    private Boolean isFinished() {
        StringBuilder newSuitorsPreferenceString = new StringBuilder();
        for (Suitor suitor : court.getSuitors()) {
            newSuitorsPreferenceString.append(suitor.getPreferences().toString());
        }

        if (newSuitorsPreferenceString.toString().equals(suitorsPreferenceString)) {
            isFinished = true;
        }
        suitorsPreferenceString = newSuitorsPreferenceString.toString();
        return isFinished;
    }
}
