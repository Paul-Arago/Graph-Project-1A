import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Parsing CSV file
        try{
            ParserCSV parser = new ParserCSV();
            parser.parse();
            List<Student> studentList = parser.getStudentsList();
            List<School> schoolList = parser.getSchoolsList();
        }catch(ParsingException e){
            e.getMessage();
        }
    }
}