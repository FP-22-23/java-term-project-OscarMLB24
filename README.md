# Programming Fundamentals' Second Semester Project (Course 2022/23)
Author/a: Óscar Alfonso Mitillo López-Barajas   UVUS:MFC0613

## *Folder's structure*

* **/src**: Directory with the source code.
  * **fp.types**: Package containing project types.
  * **fp.test**: Package containing the test class of the project.
  * **fp.utils**:  Package containing the utility classes (checkers). 
* **/data**: Contains the project dataset.
    * **WorldCupMatches.csv**: .csv file containing data from all the World Cup matches of the history until 2014.
    
## *Dataset's structure*

Originally, the dataset had 20 columns but it was expanded by adding one more column containing the minute and second, expressed as a Double value, of the first shot which was randomly generated. Each row contains information about one Football World Cup match until the World Cup of 2014 played in Brasil. This 21 columns are now explained:

* **year**: of type `Integer`,  indicates the year.
* **dateTime**: of type `LocalDateTime`, indicates the date and time.
* **stage**: of type `String`, indicates the stage of the tournament (Final, Semi-finals. quarter-finals...).
* **stadium**: of type `String`, indicates the name of the stadium.
* **city**: of type `String`, indicates the name of the city.
* **homeTeamName**: of type `String`, indicates the name of the team playing as local.
* **homeTeamGoals**: of type `Integer`, indicates the local team number of goals. 
* **awayTeamName**: of type `String`, indicates the name of the team playing as visitant.
* **awayTeamGoals**: of type `Integer`, indicates the visitant team number of goals.
* **winConditions**: of type `String`, description of the victory (it does not take any value in any match).
* **attendance**: of type `Integer`, indicates the number of spectators.
* **halfTimeHomeGoals**: of type `Integer`, indicates the local team number of goals before the break.
* **halfTimeAwayGoals**: of type `Integer`, indicates the visitant team number of goals before the break.
* **referee**: of type `String`, indicates the name of the main referee.
* **assistant1**: of type `String`, indicates the name of the second referee.
* **assistant2**: of type `String`, indicates the name of the third referee.
* **roundId**: of type `Integer`, indicates the round identification.
* **matchId**: of type `Integer`, indicates the match identification.
* **homeTeamInitials**: of type `String`, indicates the initials of the team playing as local.
* **awayTeamInitials**: of type `String`, indicates the initials of the team playing as visitant.
* **firstShotMinute**: of type `Double`, indicates the minute when the first shot was taken.

## Implemented types

The implemented types in the project are the following:

### Base Type - Match
Represents one Football World Cup match.

**Propperties**:

- _year_, of type `Integer`, public. Indicates the year.
- _dateTime_, of type `LocalDateTime`, public. Indicates the date and time.
- _stage_, of type `String`, public. Indicates the stage of the tournament (Final, Semi-finals. quarter-finals...).
- _location_, of type `Location`, public. object type that contains the stadium and city.
- _homeTeamName_, of type `String`, public. Indicates the name of the team playing as local.
- _homeTeamGoals_, of type `Integer`, public. Indicates the local team number of goals. 
- _awayTeamName_, of type `String`, public. Indicates the name of the team playing as visitant.
- _awayTeamGoals_, of type `Integer`, public. Indicates the visitant team number of goals.
- _winConditions_, of type `String`, public. Description of the victory (it is always " ").
- _attendance_, of type `Integer`, public. Indicates the number of spectators (It can be `null` if there is no information).
- _halfTimeHomeGoals_, of type `Integer`, public. Indicates the local team number of goals before the break.
- _halfTimeAwayGoals_, of type `Integer`, public. Indicates the visitant team number of goals before the break.
- _referees_, of type `List\<String\>`, public. Contains the name of the 3 referees.
- _roundId_, of type `Integer`, public. Indicates the round identification.
- _*matchId_, of type `Integer`, public. Indicates the match identification.
- _homeTeamInitials_, of type `String`, public. Indicates the initials of the team playing as local.
- _awayTeamInitials_, of type `String`, public. Indicates the initials of the team playing as visitant.
- _firstShotMinute_, of type `Double`, public. Indicates the minute when the first shot was taken.

**Derived properties**:

- _recent_, of type `Boolean`, public. It takes False as value if that match was played before the 21th century, True otherwise.
- _totalGoals_, of type `Integer`, public. Indicates the total number of goals between both teams.
- _winner_, of type `String`, public. Indicates the winner.

**Constructors**: 

- C1: It has one parameter for each basic property of the type.
- C2: It creates a `Match` object from the following parameters: ```LocalDateTime localDate, String stage, String homeTeamName, Integer homeTeamGoals, Integer awayTeamGoals, String awayTeamName, Integer matchId```.

**Restrictions**:
 
- R1: The year must be after 1930 (when the first World Cup took place), it must be equal of before the current year, it must be equal to the year of the given date and it must be a multiple of 4 starting from 1930 (World Cups are played every 4 years).
- R2: Local team goals must be greater than 0.
- R3: Visitant team goals must be greater than 0.
- R4: The attendance must be greater than 0.
- R5: Local team goals before the break must be greater than 0 and less than total local team goals.
- R6: Visitant team goals before the break must be greater than 0 and less than total visitant team goals.
- R7: The round identification must be greater than 0.
- R8: The match identification must be greater than 0.
- R9: The date and time must be before the current one.

***Equality criteria***: Two matches are equal if local team name, visitant team name, stage and year are equal.

**Natural order**: By date and time.

**Other methods**:

- _String toString()_: Returns a String with all the match information.
- _String shortFormat()_: Returns a String with the most important information in a compressed way.
- _void addReferee(String r)_: Adds a referee to the list `Referees`.
- _void removeReferee()_: Removes a referee from the list `Referees`.

#### Auxiliary types

- Result, enum. It can take LOCAL_WINS, VISITANT_WINS and DRAW as values.
- Location, class. It has String `stadium` and String `city` as properties (it also has a toString method, equality criteria and natural order using both properties).

### Factory- MatchFactory

Factory class to construct objects of type Matches.

- _Matches readMatches(String file)_: Creates an object of type Matches from the stored information in the csv file, whose path is given as parameter.

- _(private) Match parseMatch(String s)_: Creates an object of type Match from the stored information in a line of the csv file, each line is given as parameter.

### Container Type - Matches

Container class of the object of type Match.

**Properties**:

-  _matches_, of type `List\<Match\>`, private. List containing all the matches. 
 
**Constructors**: 

- C1: By default constructor. Creates an empty object of type Match.
- C2:  Constructor with one parameter of type Collection\<Match\>. Creates an object of type Matches with the matches included in the collection given as parameter.
- C3: Constructor with one parameter of type Stream\<Match\>. Creates an object of type Matches with the matches included in the stream given as parameter.

**Equality criteria**: Two matches are equal if all their elements are equal.

**Other methods**:

- _Match getMatch(int i)_: Returns the match in position i.

- _List<Match> getMatches(int i,int e): Returns the matches contained in the interval (i,e).
 
- _Integer getNumberMatches()_: Returns the total number of matches in the list.

- _void addMatch(Match m)_: Adds the match m to the list.

- _void addMatches(List<Match> l)_: Adds the all the matches contanined in l to the list.

- _void removeMatch(Match m / int i)_: If the parameter is of type `Match`, it removes that match from the list (in case it exists). If the patameter is of type `Integer`, ir removes the match in position i.

- _Boolean checkMatchData(String variable, String value)_: Checks if there is a match in the whole list in which the property "variable" coincides with the given value. For example, variable = "homeTeamName" and value = "argentina", it will return `true` if there is a match where the local team name is argentina, otherwise, it will return `false`.

- _Double averageValue(String variable)_: Computes the average value of the given property "variable". If "variable" does not coincide with any object `Match` property, it will return `null`. If it coincides, it will return a Double value of the average. In case there is any error computing the average, the result would be 0.0.

- _List<Match> filterBy(String variable, String value)_: Filters the list of matches in order to get all the matches whose property "variable" coincides with the given "value". If there is an exception or no match satisfies that conditions, the result will be `null`.

- _Map<String, List<Match>> matchesPerCountry()_: Returns a dictionary relating all the national football teams names (keys) to all their played matches (values).

- _Map<String, Long> numberWonMatchesPerCountry()_: Returns a dictionary relating all the national football teams names (keys) to their number of won mathces (values).

- _Match maxOrMinAfterFiltering(String filteringVariable, String filteringValue, String  functionVariable , String function)_: Filters the list of matches in order to get all the matches whose property "variable" coincides with the given "value". If there is no match with that property or value, the list will be empty (using filterBy). Then, it returns the match with the maximum or minimum value (depending on the parameter "function", which can be max or min) comparing by the given parameter "functionVariable". If there is an exception or no match satisfies that conditions, the result will be `null`.

- _List<Match> sortedFiltering(String filteringVariable, String filteringValue, String sortingVariable, String reversed)_: Filters the list of matches in order to get all the matches whose property "variable" coincides with the given "value". If there is no match with that property or value, the list will be empty (using filterBy). Then, it returns a list of matches sorted (depending on the parameter "reversed", it will be reversed or not) comparing by the given parameter "sortingVariable". If there is an exception or no match satisfies that conditions, the result will be an empty list.
 
- _List<String> printMatches()_: Returns a list containing the short format of all the matches.
 

- _Map<Integer, Integer> maxAttendanceEachYear()_: Returns a dictionary relating all the World Cup years (keys) to the maximum attendance to a match (values).
 
- _SortedMap<String,List<Match>> matchesWithMoreTotalGoalsPerCountry(Integer n)_: Returns a dictionary relating all the national football teams names (keys) to a list of the "n" (parameter) matches with the greatest number of total goals (values). The keys are sorted alphabetically.
 
- _String countryWithMostWonMatches(String function)_: Returns the name of the national football team with most or lest (depending on the patameter "function", which can be max or min) won matches.
 
- _worldCupWinnerPerYear()_: Returns a dictionary relating all the World Cup years (keys) to the winner of that World Cup (values).
