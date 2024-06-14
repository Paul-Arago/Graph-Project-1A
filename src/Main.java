import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Variable to store the user's choice for bidding
        String biddingChoice;

        /**
         * If one argument is passed, the bidding will be done automatically.
         * Otherwise, the user will be asked to choose who is bidding.
         */
        if (args.length == 1 && argumentIsValid(args[0])) {
            System.out.println("The argument passed is: " + args[0]);
            biddingChoice = args[0];
        } else {
            System.out.println("Either no argument was passed, more than one argument was passed, or the argument passed was invalid.");
            System.out.println("The bidding will be done manually.");
            
            //User choice for who is bidding
            Scanner scanner = new Scanner(System.in);
            biddingChoice = "";
            while(!biddingChoice.equals("1") && !biddingChoice.equals("2")) {
                System.out.print("Type 1 for school bidding or 2 for student bidding : ");
                biddingChoice = scanner.nextLine();
                if (!biddingChoice.equals("1") && !biddingChoice.equals("2")) {
                    System.out.print("Bad entry.\n");
                }
            }
            scanner.close();
        }


        // Parsing CSV file
        try{
            ParserCSV parser = new ParserCSV();
            parser.parse();
            List<Student> students = parser.getStudentsList();
            List<School> schools = parser.getSchoolsList();
            List<Suitor> suitors = new ArrayList<>();
            List<CourtedOne> courtedOnes = new ArrayList<>();
            Court court;
            
            /**
             * If the user chose 1, the suitors will be the students and the courted ones will be the schools.
             * Otherwise, the suitors will be the schools and the courted ones will be the students.
             */
            if (biddingChoice.equals("1")) {
                courtedOnes = getCourtedOneListBySchool(schools);
                suitors = getSuitorsListByStudent(students);
            }else if (biddingChoice.equals("2")){
                courtedOnes = getCourtedOneListByStudent(students);
                suitors = getSuitorsListBySchool(schools);
            }
            
            // Set up the preferences for each suitor
            for(Suitor suitor : suitors){
                suitor.setupPreferences(courtedOnes);
            }

            // Set up the preferences for each courted one
            for(CourtedOne courtedOne : courtedOnes){
                courtedOne.setupPreferences(suitors);
            }

            // Create a Court
            court = new Court(suitors, courtedOnes);
            
            // Create a Coordinator
            Coordinator coordinator = new Coordinator(court);

            // Start the algorithm
            coordinator.start();

            // Get the results
            // getResults(suitors, courtedOnes);
            getResultsSchoolsStudents(schools, students);


        }catch(ParsingException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }


    }

    private static void getResults(List<Suitor> suitors,List<CourtedOne> courtedOnes) {
        for (Suitor suitor : suitors) {
        }
    }
    
    private static void getResultsSchoolsStudents(List<School> schools, List<Student> students) {

        System.out.println();
        System.out.println(" --------------------------------Results-------------------------------- ");

        System.out.println("----------------Schools----------------");
        
        for (School school : schools) {
            System.out.println(school.getName());
            for (Student student : school.getStudents()) {
                System.out.println("  - " + student.getName());
            }
        }

        System.out.println("----------------Students----------------");

        for (Student student : students) {
            System.out.println(student.getName());
            if (student.getSchool() != null) {
                System.out.println("  - " + student.getSchool().getName());
            } else {
                System.out.println("  - ");
            }
        }
    }
    


    private static void showCourtedOnes(List<CourtedOne> courtedOnes) {
    }

    private static void showSuitors(List<Suitor> suitors) {
        for(Suitor suitor : suitors){
            System.out.println(suitor);
            for(Map.Entry<CourtedOne, Integer> entry : suitor.getPreferences().entrySet()){
                System.out.print("  - ");
                System.out.println(entry.getKey());
            }
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
            suitors.add(new SchoolSuitor(school));
        }
        return suitors;
    }

    private static List<CourtedOne> getCourtedOneListByStudent(List<Student> students){
        List<CourtedOne> courtedOnes = new ArrayList<>();
        for(Student student : students){
            courtedOnes.add(new StudentCourtedOne(student));
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
        
            for(Map.Entry<School, Integer> entry : student.getPreferences().entrySet()){
                System.out.print("  - ");
                System.out.println(entry.getKey());
            }
        }
    }


    private static void showSchools(List<School> schools){

        for(School school : schools){
            System.out.println(school);
            for(Map.Entry<Student, Integer> entry : school.getPreferences().entrySet()){
                System.out.print("  - ");
                System.out.println(entry.getKey());
            }
        }
    }

    private static boolean argumentIsValid(String argument) {
        return argument.equals("1") || argument.equals("2");
    }
}