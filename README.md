# Java classes

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
    <School> school
    <Student> students
    Enumeration seekers
    void moveSeekersToBalcony(Seeker seeker)
}

class Balcony {
    <School> schools
    <Student> students
    void responderChooses(Seeker seeker)
}

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



