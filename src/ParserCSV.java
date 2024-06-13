import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ParserCSV {
    private List<Student> studentsList;
    private List<School> schoolsList;

    public ParserCSV() {
        this.studentsList = new ArrayList<Student>();
        this.schoolsList = new ArrayList<School>();
    }

    public void parse() throws ParsingException{
        String line = "";
        String splitBy = ",";
        String splitPreferencesBy = ";";
        try
        {
            Path path = Paths.get("src", "InputV2.csv");
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));

            //Create school list with first line of the file
            if((line = br.readLine()) != null){
                List<String> firstLine = new ArrayList<>(Arrays.stream(line.split(splitBy)).toList());
                if(firstLine.size() > 0 && firstLine.get(0).isEmpty()){
                    firstLine.remove(0);
                }else{
                    throw new ParsingException("Bad format.\nEnsure that the CSV file is correctly formatted.");
                }
                for(String s : firstLine){
                    School school = new School(s, 2);
                    schoolsList.add(school);
                }
            }
            

            //Creation of student and preferences retrieving (for schools AND students)
            while ((line = br.readLine()) != null)
            {
                //Split preferences and add them to school or student
                String[] splittedLine = line.split(splitBy);
                Student student = new Student(splittedLine[0]);
                for(int i = 1; i < splittedLine.length; i++){
                    
                    String[] preferences = splittedLine[i].replaceAll(" ", "").split(splitPreferencesBy); // why not just remove the spaces in the file?
                    if(preferences.length == 2){
                        int schoolPreference = Integer.parseInt(preferences[0].trim());
                        int studentPreference = Integer.parseInt(preferences[1].trim());
                        student.getPreferences().put(schoolsList.get(i-1), studentPreference);
                        //schoolsList.get(i-1).addStudent(student);
                        schoolsList.get(i-1).getPreferences().put(student, schoolPreference);
                    }else{
            
                        throw new ParsingException("Bad value format.\nCheck that value are in this format : " +
                                "\"value - value\"");
                    }
                }
                studentsList.add(student);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public List<School> getSchoolsList() {
        return schoolsList;
    }
}
