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
            courtedOnesChooseSuitor();

            // Remove the courted one if the suitor was not chosen.
            System.out.println("Removing the courted one of the suitors preferences if the suitor was not chosen...");
            removeCourtedOneFromRejectedSuitorsPreferences();

            // Move rejected suitors back to the court.
            System.out.println("Moving suitors back to the court...");
            moveRejectedSuitorsToCourt();

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
            for (Suitor suitor : balcony.getSuitors()) {
                if (!suitor.isUnited()) {
                    balcony.removeSuitor(suitor);
                }
            }
        }
    }

    private void removeCourtedOneFromRejectedSuitorsPreferences() {
        for (Balcony balcony : court.getBalconies()) {
            for (Suitor suitor : balcony.getSuitors()) {
                if (!suitor.isUnited()) {
                    suitor.removePreference(balcony.getCourtedOne());
                }
            }
        }
    }

    public void moveAtCapacityCourtedOnesToCourt() {
        for (Balcony balcony : court.getBalconies()) {
            if (balcony.getCourtedOne().isAtCapacity()) {
                balcony.setCourtedOne(null);
            }
        }
    }

    public void remainingSuitors() {
        System.out.println("Total number of Suitors: " + court.getSuitors().size());
        System.out.println("Number of remaining Suitors: " + court.getSuitors().stream().filter(suitor -> !suitor.isAtCapacity()).count());
        System.out.println("Number of Suitors at capacity: " + court.getSuitors().stream().filter(suitor -> suitor.isAtCapacity()).count());
    }

    public void remainingCourtedOnes() {
        System.out.println("Total number of CourtedOne: " + court.getCourtedOnes().size());
        System.out.println("Number of remaining CourtedOne: " + court.getCourtedOnes().stream().filter(courtedOne -> !courtedOne.isAtCapacity()).count());
        System.out.println("Number of CourtedOne at capacity: " + court.getCourtedOnes().stream().filter(courtedOne -> courtedOne.isAtCapacity()).count());
    }

    private void setupBalconies() {
        for (CourtedOne courtedOne : court.getCourtedOnes()) {
            Balcony balcony = new Balcony(courtedOne);
            courtedOne.setBalcony(balcony);
            court.addBalcony(balcony);
        }
    }

    private <T extends Participants> boolean updateAreAtCapacity(List<T> participants) {
        boolean areAtCapacity = true;
        for (T participant : participants) {
            if (participant != null && !participant.isAtCapacity()) {
                areAtCapacity = false;
                break;
            }
        }

    return areAtCapacity;
    }

    private void moveSuitorsToPreferredBalconies() {
        for (Suitor suitor : court.getSuitors()) {
            if (!suitor.isAtCapacity()){
                Map<CourtedOne, Integer> preferences = suitor.getPreferences();

                // Convert the entries to a list and sort it in descending order of values
                List<Map.Entry<CourtedOne, Integer>> sortedPreferences = new ArrayList<>(preferences.entrySet());
                sortedPreferences.sort(Map.Entry.<CourtedOne, Integer>comparingByValue().reversed());


                // Iterate over the sorted preferences
                for (Map.Entry<CourtedOne, Integer> entry : sortedPreferences) {
                    if (!entry.getKey().isAtCapacity()) {
                        entry.getKey().getBalcony().addSuitor(suitor);
                        break; // Exit the current loop and move on to the next suitor
                    }
                }
            }
        }
    }

    private void courtedOnesChooseSuitor() {
        for (Balcony balcony : court.getBalconies()) {

            if (!balcony.getCourtedOne().isAtCapacity() && !balcony.getSuitors().isEmpty()){
                CourtedOne courtedOne = balcony.getCourtedOne();
                Suitor preferredSuitor = courtedOne.getPreferredSuitor(balcony.getSuitors());
                unite(preferredSuitor, courtedOne);
            }
        }
    }

    private void unite(Suitor suitor, CourtedOne courtedOne) {
        courtedOne.unite(suitor);
        suitor.unite(courtedOne);
    }

    private void moveSuitorsToCourt() {
        for (Balcony balcony : court.getBalconies()) {
            balcony.removeAllSuitors();
        }
    }

    private void updateIsFinished() {
        for(Suitor suitor : court.getSuitors()) {
            if(!suitor.getPreferences().isEmpty()) {
                isFinished = false;
                return;
            }
        }
        isFinished = true;
    }
}
