# PayMe2.0
# To build the application:

Step 1:
Download this project and extract it.

Step 2:
Create a local.properties file in the root directory, and add "sdk.dir={Your path to your android SDK}". For example:
"sdk.dir=C\:\\Users\\anton\\AppData\\Local\\Android\\Sdk". Every " \ " after "C:" should actually be two " \ ".

Step 2:
In Windows Powershell, rund the command "./gradlew build" from the root directory of the project.

The program will now build.

# About testing

We were not able to find a way to get the test coverage using the command line, but running them in Android Studio shows us the coverage for each class in the model. Some methods and lines are not covered in the tests. The methods not covered are private and basically do nothing, but are needed for our JSON to work. There are also some exceptions that are not covered, because that is basically a state in which the program can never be. 

The testing of the class PayMeModel is not included as a unit test, becauase it's instead an instrumentation test, which uses a virtual device to run. This is unfortunately needed for our storing of data, which requires a context to know where to store the files. The instrumentation test can not show coverage, but each method in this class is covered in the test.
