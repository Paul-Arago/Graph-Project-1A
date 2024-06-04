import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Parsing CSV file
        try{
            ParserCSV parser = new ParserCSV();
            parser.parse();
            List<Student> studentList = parser.getStudentsList();
            List<School> schoolList = parser.getSchoolsList();
        }catch(ParsingException e){
            System.out.println(e.getMessage())  ;
        }
    }
}