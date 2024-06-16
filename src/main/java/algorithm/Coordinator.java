package algorithm;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import model.Balcony;
import model.Court;
import model.participant.courtedone.CourtedOne;
import model.participant.suitor.Suitor;
import output.generator.OutputGenerator;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Coordinator {
    private Court court;
    private int round;
    private boolean hasConverged;
    private OutputGenerator outputGenerator;

    /**
     * The string representation of all the suitors preferences.
     * This is used to determine if the process is finished.
     * If the string representation of the suitors preferences is the same two rounds in a row, the process is finished.
     */
    String suitorsPreferenceString;

    public Coordinator(Court court) {
        this.court = court;
        this.round = 1;
        hasConverged = false;
        outputGenerator = new OutputGenerator();
    }

    public void start() {
        System.out.println("Starting process...");

        // Set up the balconies in the court.
        System.out.println("Setting up balconies...");
        setupBalconies();

        // Start the rounds.
        System.out.println("Starting rounds...");

        while (!hasConverged()) {
            System.out.println("----------------------------------");
            System.out.println("Round: " + round );

            // Move suitors to preferred balconies.
            System.out.println("Moving suitors to preferred balconies...");
            moveSuitorsToPreferredBalconies();

            // Make courted ones choose suitors.
            System.out.println("Making courted ones choose suitors and uniting them (temporarily)...");
            courtedOnesChooseSuitors();

            // Remove the courted one if the suitor was not chosen.
            System.out.println("Removing the courted one of the suitors preferences if the suitor was rejected...");
            updateSuitorsPreferences();

            // Add round to the output
            generateRoundOutput(round, court.getBalconies());

            // Move rejected suitors back to the court.
            System.out.println("Moving all suitors back to the court...");
            moveSuitorsToCourt();

            // Print the total number of Suitors, and then the number of remaining Suitors.
            remainingSuitors();

            // Print the total number of model.courtedone.CourtedOne, and then the number of remaining model.courtedone.CourtedOne.
            remainingCourtedOnes();

            // Increment the round.
            round++;
        }

        System.out.println("Process completed.");
    }

    private void generateRoundOutput(int round, List<Balcony> balconies) {
        outputGenerator.generateRoundOutput(round, balconies);
    }


    public void remainingSuitors() {
        System.out.println("Total number of Suitors: " + court.getSuitors().size());
        System.out.println("Suitors with no preferences left: " + court.getSuitors().stream().filter(suitor -> suitor.getPreferences().isEmpty()).count());
    }

    public void remainingCourtedOnes() {
        System.out.println("Total number of model.courtedone.CourtedOne: " + court.getCourtedOnes().size());
    }

    private void setupBalconies() {
        for (CourtedOne courtedOne : court.getCourtedOnes()) {
            Balcony balcony = new Balcony(courtedOne);
            courtedOne.setBalcony(balcony);
            court.addBalcony(balcony);
        }
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
            suitor.disunite(balcony.getCourtedOne());
        }
    }


    private void updateSuitorsPreferences() {
        for (Balcony balcony : court.getBalconies()) {
            for (Suitor suitor : balcony.getSuitors()) {
                if (!suitor.isUnitedTo(balcony.getCourtedOne())) {
                    suitor.removePreference(balcony.getCourtedOne());
                }
            }
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
    private Boolean hasConverged() {
        StringBuilder newSuitorsPreferenceString = new StringBuilder();
        for (Suitor suitor : court.getSuitors()) {
            newSuitorsPreferenceString.append(suitor.getPreferences().toString());
        }

        if (newSuitorsPreferenceString.toString().equals(suitorsPreferenceString)) {
            hasConverged = true;
        }
        suitorsPreferenceString = newSuitorsPreferenceString.toString();
        return hasConverged;
    }
}
