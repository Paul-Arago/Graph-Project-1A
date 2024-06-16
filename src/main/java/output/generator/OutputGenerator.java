package output.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Balcony;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OutputGenerator {

    JsonNodeFactory factory;

    // Create an ObjectMapper
    ObjectMapper mapper;

    private File outputDirectory;

    private ObjectNode rootObjectNode;

    public  OutputGenerator() {
        // Create a JsonNodeFactory
        factory = JsonNodeFactory.instance;

        // Create an ObjectMapper
        mapper = new ObjectMapper();

        // Create the root object node
        rootObjectNode = factory.objectNode();

        // Get the resource as a URL
        URL resourceUrl = getClass().getClassLoader().getResource("output.json");

        // Convert the URL to a File
        if (resourceUrl != null) {
            outputDirectory = new File(resourceUrl.getFile());
            outputDirectory.mkdirs();
        } else {
            throw new RuntimeException("Output directory not found");
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
            ObjectNode balconyNode = factory.objectNode();

            // Add the balcony to the round
            roundNode.set(balcony.getCourtedOne().getName() + "'s balcony", balconyNode);

            // Create the courted one object node
            ObjectNode courtedOneNode = mapper.valueToTree(balcony.getCourtedOne());
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Write the JsonNode to a file
            mapper.writeValue(new File(outputDirectory, "output.json"), rootObjectNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}