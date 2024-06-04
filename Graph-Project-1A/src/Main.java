import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ParserCSV parser = new ParserCSV();
        parser.parse();
        List<Student> studentList = parser.getStudentsList();
        List<School> schoolList = parser.getSchoolsList();
    }
}