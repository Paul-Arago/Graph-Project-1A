# Getting Started

## Prerequisites

This project was developed using openjdk 22.0.1. To install it, follow the instructions on the [OpenJDK website](https://www.oracle.com/java/technologies/downloads/).

Only basic `Java.util`, `Java.io` and `Java.nio` libraries were used in this project. No additional libraries are required to run this project.

## Installation

To install this project, clone the repository using the following command:

```bash
git clone https://github.com/Paul-Arago/Graph-Project-1A.git
```

## Configuration

This project uses a csv file to store the students and schools preferences. The highest the preference, the more the student or school wants to be with the other and vice versa.

The CSV file must be formatted as follows:

```csv
,School1;capacitySchool1,School2;capacitySchool2, ...
Student1,preferenceSchool1ForStudent1;preferenceStudent1ForSchool1,preferenceSchool2ForStudent1;preferenceStudent1ForSchool2, ...
Student2,preferenceSchool1ForStudent2;preferenceStudent2ForSchool1,preferenceSchool2ForStudent2;preferenceStudent2ForSchool2, ...
```

The following image helps to better understand the format of the file:

![CSV file format](resources/csv_format.png)

## Usage



## Troubleshooting



# Instructions

- Implement a student admission program using the stable marriage algorithm
- Input:
	- student and school preferences from a file in the format used in TD, or something else, the students and the schools must be given as an input file and the user decides the bidding
	- User selects who does the bidding (serenating)
	- schools can accept mutiple students, students go to one school
		- schools have capacities
- output:
	- student to school assignement
	- number of rounds needed to converge
- specifications:
	- Should be able to handle more students than capacity of schools
	- Every school does its own ranking
- Evaluation (30% of final grade)
	- demo during the last session june 14
	- report + code by june 


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



