import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ParserCSV parser = new ParserCSV();
        parser.parse();
        List<Student> studentList = parser.getStudentsList();
        List<School> schoolList = parser.getSchoolsList();

        for(Student s : studentList) {
            System.out.println(s.getName());
            System.out.println(s.getPreferencesMap());
            /*for (Map.Entry<School, Integer> entry : s.getPreferencesMap().entrySet()) {
                School key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key.getName() + " : " + value);
            }*/
        }

        for(School s : schoolList) {
            System.out.println(s.getName());
            System.out.println(s.getPreferencesMap());
            /*for (Map.Entry<Student, Integer> entry : s.getPreferencesMap().entrySet()) {
                Student key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key.getName() + " : " + value);
            }*/
        }
    }
}