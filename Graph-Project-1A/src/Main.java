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

        List<Student> studentList;
        List<School> schoolList;
        //Parsing CSV file
        try{
            ParserCSV parser = new ParserCSV();
            parser.parse();
            studentList = parser.getStudentsList();
            schoolList = parser.getSchoolsList();
        }catch(ParsingException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        //Creating court

        Court court = new Court();
    }
}