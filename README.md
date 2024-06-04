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
    void Bidding()
}

class Balcony {
    School school
    <Student> students
    Boolean schoolDoesBidding
    void moveStudentToBalcony(Student student)
}

@enduml
```

# Algorithm

## Logic

### The schools do the bidding

### The students do the bidding

The students go to the school they prefer the most.
When a student goes to a school, the school checks if it has capacity.
If it does, the student with the highest preference is added to the school.


## Pseudo code

```



```



