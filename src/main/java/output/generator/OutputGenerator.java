package output.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Balcony;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
            ObjectNode balconyNode = mapper.valueToTree(balcony);

            // Add the balcony to the round in the output
            roundNode.set(balcony.getCourtedOne().getName() + "'s balcony", balconyNode);
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