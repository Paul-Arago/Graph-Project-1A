# Stable marriage algorithm report

## Introduction

The stable marriage algorithm is a well-known algorithm that allows to solve the problem of finding a stable matching between two equally sized sets of elements given an ordering of preferences for each element.

In this project, a stable marriage algorithm has been implemented for school admissions. Because schools can be matched with multiple students, the algorithm has been adapted to allow for this.  
In this report, the term `suitor` will be used to refer to the group of entities that is seeking out the other group and being accepted or rejected by them. Meanwhile, the term `courted one` will be used to refer to the group of entities that is being sought out by the other group and accepting or rejecting them (they so the bidding).

The project was conducted in Java and can be accessed on GitHub at the following [link](https://github.com/Paul-Arago/Graph-Project-1A).

## Getting Started

The following parts are copied from the README present on the GitHub repo.

### Prerequisites

**Note that the project was developed using IntelliJ IDEA, so it is recommended to use this IDE to run the project as it will automatically install the necessary dependencies.**

This project was developed using openjdk 21.0.3. To install it, follow the instructions on the [OpenJDK website](https://www.oracle.com/java/technologies/downloads/).

Use the following command to install OpenJDK on Ubuntu:

```bash
sudo apt install openjdk-21-jdk
```

### Dependencies

The project uses Maven for dependency management. The following dependencies were used:

- Jackson Databind: Used to generate the output file.

To install these dependencies, Maven will need to be installed on your machine. If it's not already installed, you can download it from the [official Maven website](https://maven.apache.org/download.cgi), or by using the following command:

```bash
sudo apt install maven
```

#### **WARNING**

The project uses the "Jackson Databind" library to create JSON output file. If this library is causing any issues, you can correct any bugs by commenting these lines if the file Coordinator.java : 61, 31, 18, 80-82.


#### Installing the dependencies

1. To install this project, clone the repository using the following command:

```bash
git clone https://github.com/Paul-Arago/Graph-Project-1A.git
```

2. Navigate to the project directory:

```bash
cd Graph-Project-1A
```

3. Install the dependencies (from the project directory) using the following command:

```bash
mvn clean install
```

### Configuration

This project uses a csv file to store the students and schools preferences. The highest the preference, the more the student or school wants to be with the other and vice versa. The resources file in defined in the `parser.Parser` class, by default it is set to `model/resources.csv`.

The CSV file must be formatted as follows:

```csv
,School1;capacitySchool1,School2;capacitySchool2, ...
Student1,preferenceSchool1ForStudent1;preferenceStudent1ForSchool1,preferenceSchool2ForStudent1;preferenceStudent1ForSchool2, ...
Student2,preferenceSchool1ForStudent2;preferenceStudent2ForSchool1,preferenceSchool2ForStudent2;preferenceStudent2ForSchool2, ...
```

The following image helps to better understand the format of the file:

![CSV file format](resources/csv_format.png)

### Usage

The choice of who does the bidding is decided either through the command line (if no argument is passed) or by passing an argument to the `Main` class when running the program.

The `biddingChoice` argument is either `1` or `2`. If `1` is chosen, the students will be pursuing the schools. On the other hand, if `2` is chosen, the schools will be pursuing the students.


To run the program, with the bidding choice decided through the command line, navigate to `\Graph-Project-1A\src\main\java`:

```bash
cd src/main/java
```

Then run the program using the following command:

```bash
mvn -f ../../../pom.xml exec:java -Dexec.mainClass="Main" -Dexec.args="[biddingChoice]"
```

or

```bash
mvn -f ../../../pom.xml exec:java -Dexec.mainClass="Main"
```

example:

```bash
mvn -f ../../../pom.xml exec:java -Dexec.mainClass="Main" -Dexec.args="1"
```

Note regardless of whether the user uses an IDE, the root directory must be `java` in order for `parser.Parser` and `OutputGenerator` to work correctly.


## Algorithm

The algorithm implemented in this project is inspired by the Stable Marriage Algorithm, but has been adapted to allow for multiple matches between suitors and courted ones.  

In order to do this, the termination condition of the algorithm has been changed. Indeed, in the regular Gale-Shapley algorithm, the algorithm terminates when all suitors have been matched, but this would not work in our case. That is because both the suitors and the courted ones can be matched with multiple entities.  
To circumvent this issue, the decision has been made to terminate the algorithm when the preferences of the suitors have converged. This means that the suitors have been matched with the same courted ones for a certain number of iterations (2 in this case).

### Structure

Since using Java for this project had been decided (for the sake of familiarity), the objects introduced were structured in a way that would mirror the example provided in class.
In this case, the objects are as follows:

- `Suitor`
- `CourtedOne`
- `Court`
- `Balcony`

The `Suitor` and `CourtedOne` objects are the entities that are being matched. They both have a list of preferences that they use to determine who they want to be matched with.  
The `Court` object is the place where all the entities are placed at the beginning of the algorithm
The `Balcony` object is the place where the courted ones are placed at the beginning of the matching process (the courted ones will remain until the end of the algorithm). At each round the suitors will move to the balconies to serenade the courted ones, after which the courted ones will choose the suitors they want to be matched with. Finally, the suitors will move back to the court with their preferences updated. 

The choice of using vague terms such as `Suitor` and `CourtedOne` was made to allow for both schools and students to be one or the other. This way, the same algorithm can be used for both cases (or other objects that respect the defined interfaces).

Here is the structure of the algorithm:

1. The courted ones and the suitors are placed in the court.
2. The courted ones are placed on the balconies.
3. While the preferences have not converged:
	1. The suitors move to the preferred balconies (they serenade).
	2. The courted ones choose the suitors.
	3. The suitors and courted ones are united.
	4. The suitors update their preferences.
	5. The suitors move back to the court.

## Implementation

### Preferences

The preferences are stored within a CSV file.

Here is the format :

![plot](./resources/csv_format.png)

### Classes

#### Coordinator

Class that processes all the stable marriage algorithm.

#### CourtedOne

Every class that implements this interface will be the courted one in the algorithm
(see SchoolCourtedOne, StudentCourtedOne).

#### Suitor

Every class that implements this interface will be the suitor in the algorithm.
(see SchoolSuitor, StudentSuitor).

#### Court

A court has a list of Balconies, a list of Suitors and a list of CourtedOnes.

#### Balcony

A balcony has a CourtedOne object and a list of Suitor objects.

#### School

A school has a list of Students, and a HashMap composed of Student objects has the keys, and integers representing the preferences as the value.

#### Student

A student has a School, and a HashMap composed of School objects has the keys, and integers representing the preferences as the value.


### Results

#### Output

The output of the program is a JSON file that contains the students and schools assignments. The file is saved in the `output` directory and is named `output.json`.

The first layer of the JSON file defines each round of the algorithm.
The second layer defines each balcony.
Within each balcony, the courted one is defined, followed by the suitors.
In the both the CourtedOne and Suitor objects, the attributes are as follows:
- `name`: the name of the school or student
- `capacity`: the capacity of the school or student (1)
- `preferences`: the preferences of the school or student
- `unitedSuitors`: the suitors that are currently united with the school or student

Here is an example of the output file:

```json
{
  "round 1" : {
    "model.School 1's balcony": {
      "Courted One": {
        "Name": "model.School 1",
        "Capacity": 2,
        "Current suitors": [
          "model.Student 1",
          "model.Student 2"
        ],
        "Current preferences": {
          "model.Student 1": 4,
          "model.Student 4": 6,
          "model.Student 5": 5,
		  "...": "..."
        }
      },
      "Suitors": [
        {
          "Name": "model.Student 1",
          "Capacity": 1,
          "Current courted ones": [
            "model.School 1"
          ],
          "Current preferences": {
            "model.School 1": 2,
            "model.School 2": 10,
            "model.School 3": 7,
            "model.School 4": 4
          }
        },
        {
          "Name": "model.Student 2",
          "Capacity": 1,
          "Current courted ones": [
            "model.School 1"
          ],
          "Current preferences": {
            "model.School 1": 1, 
			"...": "..."
          }
        }
      ]
    },
    "model.School 2's balcony": {
		"...": "..."
    },
	  "...": "..."
  },
	"...": "..."
}
```

#### Complexity

The complexity of the Stable Marriage algorithm is O(n²). That is because in the worst case scenario, each suitor would end up proposing to every courted one.

In our case though, some constant-time overhead will likely be introduced due to method calls of the classes present in the program.

## Conclusion 

In this project, we successfully implemented a stable marriage algorithm made for school admissions, adapting the classic Gale-Shapley algorithm to accommodate scenarios where multiple students can be matched to each school. The implementation required adjustments to the termination condition and the algorithm's structure to ensure stability and efficiency in the matching process.

Using Java for this project made its structure more complex due to the fact that classes such as `CourtedOne` `Suitor` `Coordinator`, ... If we had to do restart the program from scratch, we would choose a more straight forward language like python.
             