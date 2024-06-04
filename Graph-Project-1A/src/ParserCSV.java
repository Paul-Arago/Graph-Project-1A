import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParserCSV {
    private List<Student> studentsList;
    private List<School> schoolsList;

    public ParserCSV() {
        this.studentsList = new ArrayList<Student>();
        this.schoolsList = new ArrayList<School>();
    }

    public void parse(){
        String line = "";
        String splitBy = ",";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/Input.csv"));
            //Create school list with first line of the file
            if((line = br.readLine()) != null){
                String[] res = line.split(splitBy);
                for(String s : res){
                    School school = new School(s, 5);
                    schoolsList.add(school);
                }
            }

            //Creation of student and preferences retrieving (for schools AND students)
            while ((line = br.readLine()) != null)
            {
                String[] res = line.split(splitBy);
                Student student = new Student(res[0]);
                //Split preferences and add them to school or student
                int resLength = res.length;
                for(int i = 1; i < resLength; i++){
                    String[] preferences = res[i].split("-");
                    if(preferences.length == 2){
                        int schoolPreference = Integer.parseInt(preferences[0].trim());
                        int studentPreference = Integer.parseInt(preferences[1].trim());
                        student.getPreferencesMap().put(schoolsList.get(i-1), studentPreference);
                        schoolsList.get(i-1).addStudent(student);
                        schoolsList.get(i-1).getPreferencesMap().put(student, schoolPreference);
                    }
                    else{
                        //throw something
                    }
                }
                studentsList.add(student);
            }
        }
        catch (IOException e)
        {
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
