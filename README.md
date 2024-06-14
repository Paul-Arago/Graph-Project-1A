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

This project uses a csv file to store the students and schools preferences. The highest the preference, the more the student or school wants to be with the other and vice versa. The resources file in defined in the `parser.Parser` class, by default it is set to `model/resources.csv`.

The CSV file must be formatted as follows:

```csv
,School1;capacitySchool1,School2;capacitySchool2, ...
Student1,preferenceSchool1ForStudent1;preferenceStudent1ForSchool1,preferenceSchool2ForStudent1;preferenceStudent1ForSchool2, ...
Student2,preferenceSchool1ForStudent2;preferenceStudent2ForSchool1,preferenceSchool2ForStudent2;preferenceStudent2ForSchool2, ...
```

The following image helps to better understand the format of the file:

![CSV file format](resources/csv_format.png)

## Usage  

The `biddingChoice` is either `1` or `2`. If `1` is chosen, the students will be pursuing the schools. On the other hand, if `2` is chosen, the schools will be pursuing the students.

The choice of who does the bidding is decided either through the command line or by passing an argument to the `Main` class when running the program.

To run the program, with the bidding choice decided through the command line, use the following command:

```bash
java Main [biddingChoice]
```

If a bidding choice is not provided, the program will prompt the user to enter a choice.

Note that wether or not the user uses an IDE, the root directory must be `src` in order for the `parser.Parser` class to find the resources file.

# Instructions

- Implement a student admission program using the stable marriage algorithm
- Input:
	- student and school preferences from a file in the format used in TD, or something else, the students and the schools must be given as an resources file and the user decides the bidding
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
class model.Student {
    String name
    Map<model.School, int> preference 
    Boolean isAvailable
    model.School school
    model.School choose(model.Balcony balcony)
}

class model.School {
    String name
    Map<model.Student, int> students
    int capacity
    Boolean isAvailable
    model.Balcony balcony
    model.Student choose(model.Balcony balcony)
}

class model.Court {
    <model.Balcony> balcony
    <model.School> school
    <model.Student> students
    Enumeration seekers
    void moveSeekersToBalcony(Seeker seeker)
}

class model.Balcony {
    model.Court court
    <model.School> schools
    <model.Student> students
    void moveSeekersToCourt(Seeker seeker)
    void responderChooses(Seeker seeker)
}

@enduml
```

## 2nd architecture

```plantuml
@startuml MariageAlgorithmClassDiagram

class "model.Balcony" {
    - courtedOne : model.courtedone.CourtedOne {readOnly}
    - suitors : List<model.suitor.Suitor>
    + model.Balcony(courtedOne : model.courtedone.CourtedOne)
    + addSuitor(suitor : model.suitor.Suitor) : void
    + removeAllSuitors() : void
}

class "model.Court" {
    - balconies : HashMap<model.courtedone.CourtedOne, model.Balcony>
    - courtedOnes : List<model.courtedone.CourtedOne>
    - suitors : List<model.suitor.Suitor>
    + model.Court(courtedOnes : List<model.courtedone.CourtedOne>, suitors : List<model.suitor.Suitor>)
    + createBalconies() : void
}

interface "model.suitor.Suitor" {
    + getFirstPreference() : model.courtedone.CourtedOne
    + getCapacity() : int
    + unite(courtedOne : model.courtedone.CourtedOne) : void
}

class "model.suitor.StudentSuitor" {
    - student : model.Student
    - capacity : int
    - preferences : List<model.courtedone.CourtedOne>
    + model.suitor.StudentSuitor(student : model.Student)
    + getFirstPreference() : model.courtedone.CourtedOne
}

class "model.suitor.SchoolSuitor" {
    - school : model.School
    - capacity : int
    - preferences : List<model.courtedone.CourtedOne>
    + model.suitor.SchoolSuitor(school : model.School)
    + getFirstPreference() : model.courtedone.CourtedOne
}

interface "model.courtedone.CourtedOne" {
    + setBalcony(balcony : model.Balcony) : void
    + getBalcony() : model.Balcony
    + getPreference(interestedSuitors : List<model.suitor.Suitor>) : model.suitor.Suitor
    + getCapacity() : int
    + unite(suitor : model.suitor.Suitor) : void
}

class "model.courtedone.SchoolCourtedOne" {
    - school : model.School
    - capacity : int
    + model.courtedone.SchoolCourtedOne(school : model.School)
    + getPreference(interestedSuitors : List<model.suitor.Suitor>) : model.suitor.Suitor
}

class "model.courtedone.StudentCourtedOne" {
    - student : model.Student
    - capacity : int
    + model.courtedone.StudentCourtedOne(student : model.Student)
    + getPreference(interestedSuitors : List<model.suitor.Suitor>) : model.suitor.Suitor
}

class "model.Student" {
    - name : String
    - school : model.School
    - preferences : HashMap<model.School, Integer>
}

class "model.School" {
    - name : String
    - students : List<model.Student>
    - capacity : int
    - preferences : HashMap<model.Student, Integer>
}

class "Matchmaker" {
    - court : model.Court
    - round : int
    + Matchmaker(court : model.Court)
    + startProcess() : void
    + moveSuitorToBalcony(suitor : model.suitor.Suitor, balcony : model.Balcony) : void
    + moveSuitorToCourt(suitor : model.suitor.Suitor) : void
    + unite(suitor : model.suitor.Suitor, courtedOne : model.courtedone.CourtedOne) : void
}

model.Balcony --> model.suitor.Suitor
model.Balcony --> model.courtedone.CourtedOne
model.Court --o model.Balcony
model.Court --> model.courtedone.CourtedOne
model.Court --> model.suitor.Suitor
model.suitor.Suitor <..> model.courtedone.CourtedOne
model.suitor.StudentSuitor --|> model.suitor.Suitor
model.suitor.StudentSuitor --* model.Student
model.suitor.SchoolSuitor --|> model.suitor.Suitor
model.suitor.SchoolSuitor --* model.School
model.courtedone.SchoolCourtedOne --|> model.courtedone.CourtedOne
model.courtedone.SchoolCourtedOne --* model.School
model.courtedone.StudentCourtedOne --|> model.courtedone.CourtedOne
model.courtedone.StudentCourtedOne --* model.Student
Matchmaker --o model.Court
Matchmaker --> model.Balcony
Matchmaker --> model.suitor.Suitor
Matchmaker --> model.courtedone.CourtedOne
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



