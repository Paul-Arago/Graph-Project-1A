import java.util.List;
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
            List<Student> studentList = parser.getStudentsList();
            List<School> schoolList = parser.getSchoolsList();
        }catch(ParsingException e){
            System.out.println(e.getMessage())  ;
        }
    }
}