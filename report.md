# Stable marriage algorithm report

## Introduction

The stable marriage algorithm is a well-known algorithm that allows to solve the problem of finding a stable matching between two equally sized sets of elements given an ordering of preferences for each element.

In this project, a stable marriage algorithm has been implemented for school admissions. Because schools can be matched with multiple students, the algorithm has been adapted to allow for this.  
In this report, the term `suitor` will be used to refer to the group of entities that is seeking out the other group and being accepted or rejected by them. Meanwhile, the term `courted one` will be used to refer to the group of entities that is being sought out by the other group and accepting or rejecting them (they so the bidding).

The project was conducted in Java and can be accessed on GitHub at the following [link](https://github.com/Paul-Arago/Graph-Project-1A).

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

### Classes

### Results

#### Output

#### Complexity

## Conclusion 

### What we learned
                                           