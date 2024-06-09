import java.util.List;
import java.util.Map;

public class Coordinator {
    private Court court;
    private int round;
    private boolean isFinished = false;
    private boolean suitorsAreAtCapacity = false;
    private boolean courtedOnesAreAtCapacity = false;

    public Coordinator(Court court) {
        this.court = court;
        this.round = 0;
    }

    public void start() {
        System.out.println("Starting process...");

        // Setup the balconies in the court.
        System.err.println("Setting up balconies...");
        setupBalconies();

        // Start the rounds.
        System.err.println("Starting rounds...");
        
        updateIsFinished();
        while (!isFinished) {
            System.out.println("Round: " + round );

            // Move suitors to preferred balconies.
            System.err.println("Moving suitors to preferred balconies...");

            

            updateIsFinished();
        }

        System.out.println("Process completed.");
    }

    private void setupBalconies() {
        for (CourtedOne courtedOne : court.getCourtedOnes()) {
            court.addBalcony(new Balcony(courtedOne));
        }
    }

    private <T extends Participants> boolean updateAreAtCapacity(List<T> participants) {
    boolean areAtCapacity = true;

        for (T participant : participants) {
            if (!participant.isAtCapacity()) {
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
                        entry.getKey().getBalcony().addSuitor(suitor);
                        return;
                    }
                    
                });


        }
    }

    private void updateIsFinished() {
        isFinished = updateAreAtCapacity(court.getSuitors()) 
                  && updateAreAtCapacity(court.getCourtedOnes());
    }



}
