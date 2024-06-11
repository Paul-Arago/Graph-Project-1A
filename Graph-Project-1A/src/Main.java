import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //User choice for who is bidding
        Scanner scanner = new Scanner(System.in);
        String biddingChoice = "";
        while(!biddingChoice.equals("1") && !biddingChoice.equals("2")) {
            System.out.print("Type 1 for school bidding or 2 for student bidding : ");
            biddingChoice = scanner.nextLine();
            if (!biddingChoice.equals("1") && !biddingChoice.equals("2")) {
                System.out.print("Bad entry.\n");
            }
        }

        //Parsing CSV file
        try{
            ParserCSV parser = new ParserCSV();
            parser.parse();
            List<Student> students = parser.getStudentsList();
            List<School> schools = parser.getSchoolsList();
            Court court;
            if(biddingChoice.equals("1")) {
                court = new Court(getSuitorsListByStudent(students), getCourtedOneListBySchool(schools));
            }else{
                court = new Court(getSuitorsListBySchool(schools), getCourtedOneListByStudent(students));
            }

            Coordinator coordinator = new Coordinator(court);
            
            for(Student student : students) {
                System.out.println(student);
                for(Map.Entry<School, Integer> entry : student.getPreferencesMap().entrySet()){
                    System.out.println(entry.getKey());
                }
            }

            // Show all Students and their preferences
            showStudents(students);


            
            coordinator.start();    

        }catch(ParsingException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }


    }

    private static List<Suitor> getSuitorsListByStudent(List<Student> students){
        List<Suitor> suitors = new ArrayList<>();
        for(Student student : students){
            suitors.add(new StudentSuitor(student));
        }
        return suitors;
    }

    private static List<Suitor> getSuitorsListBySchool(List<School> schools){
        List<Suitor> suitors = new ArrayList<>();
        for(School school : schools){
            //suitors.add(new SchoolSuitor(school));
        }
        return suitors;
    }

    private static List<CourtedOne> getCourtedOneListByStudent(List<Student> students){
        List<CourtedOne> courtedOnes = new ArrayList<>();
        for(Student student : students){
            //courtedOnes.add(new StudentCourtedOne(student));
        }
        return courtedOnes;
    }

    private static List<CourtedOne> getCourtedOneListBySchool(List<School> schools){
        List<CourtedOne> courtedOnes = new ArrayList<>();
        for(School school : schools){
            courtedOnes.add(new SchoolCourtedOne(school));
        }
        return courtedOnes;
    }

    private static void showStudents(List<Student> students){
        for(Student student : students){
            System.out.println(student);
        
            for(Map.Entry<School, Integer> entry : student.getPreferencesMap().entrySet()){
                System.out.print("- ");
                System.out.println(entry.getKey());
            }
        }
    }
}