# Card Game Tests

This framework was created from scratch for Crisp assignments. It is based on Playright and TestNG. Programming 
Language is Java 11.

## Folder Structure

Project architecture is based on 4 main folders under com.crisp directory.
This will make the project more understandable and easy to maintain. Folders are:

### Client: 
It has CrispApi class that sets up the API requests and methods to use in actual test class.
Instead of putting requests in test class, I created reusable methods with in this class
and called them in test class when needed.

### Data Files:
It has a class to store the test data. In this class, I created funtions to convert data from String to Map
to easily manipulate the data and use it in the assertions. Ideally, we can store the data in a csv, tsv or and 
excel file and retrieve the data as needed. Limited time did not let me do it.

### Models:
It consists of POJO classes that are used to serialize JSON responses using LOMBOK.

### Tests:
It has the test class to verify the requirements by making the assertions through TestNG.

#### How to set up the project and run the tests:
1. Install Java 8 or above(I recommend Java 11).
2. Go to file in intellij
3. Go to project structure
4. Select Java SDK version as Project SDK
3. Select project language level as 8
4. Open the project in IntelliJ IDE. Tests can be run by built in intellij runner. It can be triggered by clicking the green arrow at the 
top right corner of intellij main frame.


## Considerations:

### 1
I chose Java as the programming language to complete the assignment as soon as possible.
If requested, I can convert these code into C#.

### 2
I had more time, I would definitely create a testBase class to set up and tear down playwright
and ApiRequestContext, which would be inherited by the actual test class. This would also function as a singleton class
which ensures that only one instance of the class exists.

### 3 
I would like to discuss more about my code and what improvements can be made to make it more scalable.









