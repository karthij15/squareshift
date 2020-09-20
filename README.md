# squareshift coding challenge
## Airplane	seating	algorithm

### Problem Statement
Write	a	program	that	helps	seat	audiences	in	a	flight	based	on	the	
following	input	and	rules.
Rules	for	seating	
• Always	seat	passengers	starting	from	the	front	row	to	back,	
starting	from	the	left	to	the	right	
• Fill	aisle	seats	first	followed	by	window	seats	followed	by	center	
seats	(any	order	in	center	seats)	
Input	to	the	program	will	be		
• a	2D	array	that	represents	the	rows	and	columns	[[3,4],	[4,5],	[2,3],	[3,4]]	
• Number	of	passengers	waiting	in	queue

## Algorithm
1. Retrieve input Array dimenstion as string convert it to List (Jackson Mapper used)
2. create array for each slots with the given row & column specs
3. Fill the array with seat marker
   Default marker will be "M"(Middle)
   if first or last column marker will "A"(Aisle) 
   if first slot with fistcolumn or last slot with last column marker will be "W" (Window) [This will override previously pouplated aisle allocated window seat]
4. These markerd slots will be List<String[][]> - List of 2D String Array
5. for all three different seat types iterate with the counter and map the passenger seat
6. Iterate array for every row in each slot. Replace marker with passanger count

## Tech
1. Java 1.8
2. Maven

## Installation
```
mvn clean package
```

## Run the Application
```
cd target
java -jar squareshift-test-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
