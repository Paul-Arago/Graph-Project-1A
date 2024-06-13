import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Coordinator {
    private Court court;
    private int round;
    private boolean isFinished;

    public Coordinator(Court court) {
        this.court = court;
        this.round = 0;
        isFinished = false;
    }

    public void start() {
        System.out.println("Starting process...");

        // Set up the balconies in the court.
        System.out.println("Setting up balconies...");
        setupBalconies();

        // Start the rounds.
        System.out.println("Starting rounds...");
        

        // Maybe the while loop should call a method that updates the isFinished variable.
        updateIsFinished();
        while (!isFinished) {
            System.out.println("Round: " + round );

            // Move suitors to preferred balconies.
            System.out.println("Moving suitors to preferred balconies...");
            moveSuitorsToPreferredBalconies();

            // Make courted ones choose suitors.
            System.out.println("Making courted ones choose suitors and uniting them temporarily...");
            courtedOnesChooseSuitors();

            // Remove the courted one if the suitor was not chosen.
            System.out.println("Removing the courted one of the suitors preferences if the suitor was not chosen...");
            updateSuitorsPreferences();

            // Move rejected suitors back to the court.
            System.out.println("Moving suitors back to the court...");
            moveSuitorsToCourt();

            // Update the isFinished variable.
            System.out.println();
            updateIsFinished();

            // Print the total number of Suitors, and then the number of remaining Suitors.
            remainingSuitors();

            // Print the total number of CourtedOne, and then the number of remaining CourtedOne.
            remainingCourtedOnes();

            // Increment the round.
            round++;
        }

        System.out.println("Process completed.");
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
            Map<CourtedOne, Integer> preferences = suitor.getPreferences();

            // Convert the entries to a list and sort it in descending order of values
            List<Map.Entry<CourtedOne, Integer>> sortedPreferences = new ArrayList<>(preferences.entrySet());
            sortedPreferences.sort(Map.Entry.<CourtedOne, Integer>comparingByValue());

            // Iterate over the sorted preferences
            for (Map.Entry<CourtedOne, Integer> entry : sortedPreferences) {
                entry.getKey().getBalcony().addSuitor(suitor);
            }
        }
    }

    private void courtedOnesChooseSuitors() {
        for (Balcony balcony : court.getBalconies()) {
            if (!balcony.getSuitors().isEmpty()){
                CourtedOne courtedOne = balcony.getCourtedOne();
                List<Suitor> preferredSuitors = courtedOne.getPreferredSuitors(balcony.getSuitors());
                unite(preferredSuitors, courtedOne);
            }
        }
    }

    private void unite(List<Suitor> suitors, CourtedOne courtedOne) {

        for (Suitor suitor : suitors) {
            suitor.unite(courtedOne);
            courtedOne.unite(suitor);
        }
    }

    private void moveSuitorsToCourt() {
        for (Balcony balcony : court.getBalconies()) {
            balcony.removeAllSuitors();
        }
    }

    /**
     * The only termination condition is when the unions last for two rounds in a row.
     */
    private void updateIsFinished() {
        
    }
}
