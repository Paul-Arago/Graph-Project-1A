package output.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Balcony;
import model.participant.suitor.Suitor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OutputGenerator {

    // Create a JsonNodeFactory
    JsonNodeFactory factory;

    // Create an ObjectMapper
    ObjectMapper mapper;

    // Create the output file
    private File outputFile;

    // Create the root object node
    private ObjectNode rootObjectNode;

    /**
     * Constructor for the OutputGenerator
     */
    public  OutputGenerator() {
        // Create a JsonNodeFactory
        factory = JsonNodeFactory.instance;

        // Create an ObjectMapper
        mapper = new ObjectMapper();

        // Create the root object node
        rootObjectNode = factory.objectNode();

        // Create the output directory
        outputFile = new File("../../../output/output.json");

        // Convert the URL to a File
        try {
            // Create the output file if it does not exist
            if (outputFile.createNewFile()) {
                System.out.println("Output file created: " + outputFile.getName());
            } else {
                System.out.println("Output file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error related to the output file occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Adds the output
     *
     * @param round The current round number
     * @param balconies The balconies to output, it is passed right after the CourtedOnes have chosen their Suitors
     */
    public void generateRoundOutput(Integer round, List<Balcony> balconies) {

        // Create the round object node
        ObjectNode roundNode = factory.objectNode();

        // Add the round to the round array
        rootObjectNode.set("round " + round, roundNode);

        for (Balcony balcony : balconies) {
            // Create the balcony object node
            ObjectNode balconyNode = mapper.createObjectNode();

            // Add the balcony to the round in the output
            roundNode.set(balcony.getCourtedOne().getName() + "'s balcony", balconyNode);

            // Create the courted one object node
            ObjectNode courtedOneNode = mapper.createObjectNode();

            // Add the courted one to the balcony in the output
            balconyNode.set("Courted One", courtedOneNode);

            // Add the courted one's name, capacity in the output
            courtedOneNode.put("Name", balcony.getCourtedOne().getName());
            courtedOneNode.put("Capacity", balcony.getCourtedOne().getCapacity());

            // Create the suitors array node
            ArrayNode suitorsNode = mapper.createArrayNode();

            // Add the currently united suitors to courted one in the output
            courtedOneNode.set("Current suitors", suitorsNode);

            // Add the suitors to the suitors array
            for (String suitor : balcony.getCourtedOne().getCurrentSelectedSuitors()) {
                // Add the name of the suitor to the suitors array
                suitorsNode.add(suitor);
            }

            // Create the current preferences for the courted one object node
            ObjectNode currentPreferencesNode = mapper.createObjectNode();

            // Add the current preferences for the courted one in the output
            courtedOneNode.set("Current preferences", currentPreferencesNode);

            // Add the current preferences for the courted one to the current preferences object
            for (Map.Entry<String, Integer> preference : balcony.getCourtedOne().getAllPreferences().entrySet()) {
                // Add the preference to the current preferences object
                currentPreferencesNode.put(preference.getKey(), preference.getValue());
            }

            // Create the suitors array node
            ArrayNode suitorsArrayNode = mapper.createArrayNode();

            // Add the suitors to the balcony in the output
            balconyNode.set("Suitors", suitorsArrayNode);

            // Add the suitors to the suitors array
            for (Suitor suitor : balcony.getSuitors()) {
                // Create the suitor object node
                ObjectNode suitorNode = mapper.createObjectNode();

                // Add the suitor to the suitors array
                suitorsArrayNode.add(suitorNode);

                // Add the suitor's name and capacity to the suitor object
                suitorNode.put("Name", suitor.getName());
                suitorNode.put("Capacity", suitor.getCapacity());

                // Create the courted ones array node
                ArrayNode courtedOnesNode = mapper.createArrayNode();

                // Add the currently united courted ones to the suitor array
                suitorNode.set("Current courted ones", courtedOnesNode);

                // Add the courted ones to the courted ones array
                for (String courtedOne : suitor.getCurrentSelectedCourtedOnes()) {
                    // Add the name of the courted one to the courted ones array
                    courtedOnesNode.add(courtedOne);
                }

                // Create the current preferences for the suitor object node
                ObjectNode currentPreferencesSuitorNode = mapper.createObjectNode();

                // Add the current preferences for the suitor in the output
                suitorNode.set("Current preferences", currentPreferencesSuitorNode);

                // Add the current preferences for the suitor to the current preferences object
                for (Map.Entry<String, Integer> preference : suitor.getAllPreferences().entrySet()) {
                    // Add the preference to the current preferences object
                    currentPreferencesSuitorNode.put(preference.getKey(), preference.getValue());
                }


            }

        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Write the JsonNode to a file
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, rootObjectNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}