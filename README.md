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
    - courtedOne : CourtedOne {readOnly}
    - suitors : List<Suitor>
    + Balcony(courtedOne : CourtedOne)
    + addSuitor(suitor : Suitor) : void
    + removeAllSuitors() : void
}

class "Court" {
    - balconies : HashMap<CourtedOne, Balcony>
    - courtedOnes : List<CourtedOne>
    - suitors : List<Suitor>
    + Court(courtedOnes : List<CourtedOne>, suitors : List<Suitor>)
    + createBalconies() : void
}

interface "Suitor" {
    + getFirstPreference() : CourtedOne
    + getCapacity() : int
    + unite(courtedOne : CourtedOne) : void
}

class "StudentSuitor" {
    - student : Student
    - capacity : int
    - preferences : List<CourtedOne>
    + StudentSuitor(student : Student)
    + getFirstPreference() : CourtedOne
}

class "SchoolSuitor" {
    - school : School
    - capacity : int
    - preferences : List<CourtedOne>
    + SchoolSuitor(school : School)
    + getFirstPreference() : CourtedOne
}

interface "CourtedOne" {
    + setBalcony(balcony : Balcony) : void
    + getBalcony() : Balcony
    + getPreference(interestedSuitors : List<Suitor>) : Suitor
    + getCapacity() : int
    + unite(suitor : Suitor) : void
}

class "SchoolCourtedOne" {
    - school : School
    - capacity : int
    + SchoolCourtedOne(school : School)
    + getPreference(interestedSuitors : List<Suitor>) : Suitor
}

class "StudentCourtedOne" {
    - student : Student
    - capacity : int
    + StudentCourtedOne(student : Student)
    + getPreference(interestedSuitors : List<Suitor>) : Suitor
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
    + moveSuitorToBalcony(suitor : Suitor, balcony : Balcony) : void
    + moveSuitorToCourt(suitor : Suitor) : void
    + unite(suitor : Suitor, courtedOne : CourtedOne) : void
}

Balcony --> Suitor
Balcony --> CourtedOne
Court --o Balcony
Court --> CourtedOne
Court --> Suitor
Suitor <..> CourtedOne
StudentSuitor --|> Suitor
StudentSuitor --* Student
SchoolSuitor --|> Suitor
SchoolSuitor --* School
SchoolCourtedOne --|> CourtedOne
SchoolCourtedOne --* School
StudentCourtedOne --|> CourtedOne
StudentCourtedOne --* Student
Matchmaker --o Court
Matchmaker --> Balcony
Matchmaker --> Suitor
Matchmaker --> CourtedOne
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



