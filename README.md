# Java classes

## First architecture

```plantuml
@startuml
class Student {
    String name
    Map<School, int> preference 
    Boolean isAvailable
    School school
    School choose(Balcony balcony)
}

class School {
    String name
    Map<Student, int> students
    int capacity
    Boolean isAvailable
    Balcony balcony
    Student choose(Balcony balcony)
}

class Court {
    <Balcony> balcony
    <School> school
    <Student> students
    Enumeration seekers
    void moveSeekersToBalcony(Seeker seeker)
}

class Balcony {
    Court court
    <School> schools
    <Student> students
    void moveSeekersToCourt(Seeker seeker)
    void responderChooses(Seeker seeker)
}

@enduml
```

## 2nd architecture

```plantuml
@startuml MariageAlgorithmClassDiagram

class "Balcony" {
    - respondent : Respondent {readOnly}
    - seekers : List<Seeker>
    + Balcony(respondent : Respondent)
    + addSeeker(seeker : Seeker) : void
    + removeAllSeekers() : void
}

class "Court" {
    - balconies : HashMap<Respondent, Balcony>
    - respondents : List<Respondent>
    - seekers : List<Seeker>
    + Court(respondents : List<Respondent>, seekers : List<Seeker>)
    + createBalconies() : void
}

interface "Seeker" {
    + getFirstPreference() : Respondent
    + getCapacity() : int
    + unite(respondent : Respondent) : void
}

class "StudentSeeker" {
    - student : Student
    - capacity : int
    - preferences : List<Respondent>
    + StudentSeeker(student : Student)
    + getFirstPreference() : Respondent
}

class "SchoolSeeker" {
    - school : School
    - capacity : int
    - preferences : List<Respondent>
    + SchoolSeeker(school : School)
    + getFirstPreference() : Respondent
}

interface "Respondent" {
    + setBalcony(balcony : Balcony) : void
    + getBalcony() : Balcony
    + getPreference(interestedSeekers : List<Seeker>) : Seeker
    + getCapacity() : int
    + unite(seeker : Seeker) : void
}

class "SchoolRespondent" {
    - school : School
    - capacity : int
    + SchoolRespondent(school : School)
    + getPreference(interestedSeekers : List<Seeker>) : Seeker
}

class "StudentRespondent" {
    - student : Student
    - capacity : int
    + StudentRespondent(student : Student)
    + getPreference(interestedSeekers : List<Seeker>) : Seeker
}

class "Student" {
    - name : String
    - school : School
    - preferences : HashMap<School, Integer>
}

class "School" {
    - name : String
    - students : List<Student>
    - capacity : int
    - preferences : HashMap<Student, Integer>
}

class "Matchmaker" {
    - court : Court
    - round : int
    + Matchmaker(court : Court)
    + startProcess() : void
    + moveSeekerToBalcony(seeker : Seeker, balcony : Balcony) : void
    + moveSeekerToCourt(seeker : Seeker) : void
    + unite(seeker : Seeker, respondent : Respondent) : void
}

Balcony --> Seeker
Balcony --> Respondent
Court --o Balcony
Court --> Respondent
Court --> Seeker
Seeker <..> Respondent
StudentSeeker --|> Seeker
StudentSeeker --* Student
SchoolSeeker --|> Seeker
SchoolSeeker --* School
SchoolRespondent --|> Respondent
SchoolRespondent --* School
StudentRespondent --|> Respondent
StudentRespondent --* Student
Matchmaker --o Court
Matchmaker --> Balcony
Matchmaker --> Seeker
Matchmaker --> Respondent
@enduml
```

# Algorithm

## Logic

The seekers is the party that is looking for a match.
The responders are the party that is being sought after.

At the very beginning all of the seekers are placed in the court.

From here day 1 starts.

The seekers are moved to the balcony based on their preferences.

The responders are choose a seeker based on their preferences.

The seekers are moved back to the court.

From here day 2 starts.

...


## Pseudo code

```



```



