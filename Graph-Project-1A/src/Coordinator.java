import java.util.List;
import java.util.Map;

public class Coordinator {
    private Court court;
    private int round;
    private boolean isFinished;
    private boolean suitorsAreAtCapacity;
    private boolean courtedOnesAreAtCapacity;

    public Coordinator(Court court) {
        this.court = court;
        this.round = 0;
        isFinished = false;
        suitorsAreAtCapacity = false;
        courtedOnesAreAtCapacity = false;
    }

    public void start() {
        System.out.println("Starting process...");

        // Setup the balconies in the court.
        System.err.println("Setting up balconies...");
        setupBalconies();

        // Start the rounds.
        System.err.println("Starting rounds...");
        

        // Maybe the while loop should call a method that updates the isFinished variable.
        updateIsFinished();
        while (!isFinished) {
            System.out.println("Round: " + round );

            // Move suitors to preferred balconies.
            System.out.println("Moving suitors to preferred balconies...");
            moveSuitorsToPreferredBalconies();

            // Make courted ones choose suitors, and unite them (maybe unite them in a separate method?)
            System.out.println("Making courted ones choose suitors, and uniting them...");
            courtedOnesChooseSuitor();

            // Move suitors back to the court.
            System.out.println("Moving suitors back to the court...");
            moveSuitorsToCourt();
            
            // Update the isFinished variable.
            System.out.println();
            updateIsFinished();
        }

        System.out.println("Process completed.");
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
            Map<CourtedOne, Integer> preferences = suitor.getPreferences();

            preferences.entrySet().stream()
                .sorted(Map.Entry.<CourtedOne, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> {
                   if (!entry.getKey().isAtCapacity()) {
                        System.out.println("Suitor " + suitor + " is moving to " + entry.getKey());
                       entry.getKey().getBalcony().addSuitor(suitor);
                       return;
                   }
                });
        }
    }

    private void courtedOnesChooseSuitor() {
        for (Balcony balcony : court.getBalconies()) {
            CourtedOne courtedOne = balcony.getCourtedOne();
            Suitor preferredSuitor = courtedOne.getPreferredSuitor(balcony.getSuitors());
            unite(preferredSuitor, courtedOne);
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
        isFinished = updateAreAtCapacity(court.getSuitors()) 
                  && updateAreAtCapacity(court.getCourtedOnes());
    }



}
