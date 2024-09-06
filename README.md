# cheapgurusearch
Repo contains the code for Implementation for flight search with top 5 cheapest and longest duration flights
Cheap guru Project Automation
This project automates flight search functionalities using Selenium WebDriver and Cucumber with TestNG.
Project Overview
This automation suite is designed to test flight search functionalities using Selenium WebDriver for browser automation, Cucumber for BDD (Behavior-Driven Development), and TestNG for test execution and reporting.
Prerequisites
Ensure you have the following installed:
1.	JDK 8 or later
2.	Maven
3.	Chrome browser
4.	ChromeDriver (Make sure it matches your Chrome browser version)
Setup Instructions

1. Clone the Repository
sh
Copy code
git clone https://github.com/your-repo-url.git cd your-repo-directory 

2. Configure Maven
Ensure Maven is installed and configured. You can check by running:
sh
Copy code
mvn -v 

3. Add ChromeDriver
Download the ChromeDriver that matches your Chrome browser version from here and place it in a directory that is included in your system's PATH.

4. Update Dependencies
Navigate to your project directory and run:
sh
Copy code
mvn clean install 
This will download and install all the necessary dependencies specified in the pom.xml file.
Running the Tests

1. Run Tests Using Maven
To execute tests, use the following Maven command:
sh
Copy code
mvn test 
This command will compile the code and run all the tests defined in the TestRunner class.

2. Generate Test Reports
The test results will be available in the target/cucumber-reports.html file. You can open this file in a browser to view detailed test reports.
Test Runner Configuration
The TestRunner class is configured to run Cucumber tests with TestNG:
java
Copy code
@CucumberOptions( features = "src/test/resources/Feature", glue = "stepdefinitions", plugin = {"pretty", "html:target/cucumber-reports.html"}, tags = "@smoke or @regression", monochrome = true ) public class TestRunner extends AbstractTestNGCucumberTests { @Override @DataProvider(parallel = true) public Object[][] scenarios() { return super.scenarios(); } } 
Key Options:
•	features: Directory where feature files are located.
•	glue: Package containing step definitions.
•	plugin: Configuration for reporting.
•	tags: Tags to filter which tests to run.
•	monochrome: Makes console output readable.
Test Listener
The TestListener class implements TestNG's ITestListener to log test execution events:
java
Copy code
public class TestListener implements ITestListener { @Override public void onTestStart(ITestResult result) { System.out.println("Test Started: " + result.getMethod().getMethodName()); } @Override public void onTestSuccess(ITestResult result) { System.out.println("Test Passed: " + result.getMethod().getMethodName()); } @Override public void onTestFailure(ITestResult result) { System.out.println("Test Failed: " + result.getMethod().getMethodName()); } @Override public void onTestSkipped(ITestResult result) { System.out.println("Test Skipped: " + result.getMethod().getMethodName()); } @Override public void onStart(ITestContext context) { System.out.println("Test Suite Started: " + context.getName()); } @Override public void onFinish(ITestContext context) { System.out.println("Test Suite Finished: " + context.getName()); } } 
Contributing
If you wish to contribute to this project, please fork the repository and submit a pull request with your changes. Ensure all tests pass before submitting your pull request.
License
This project is licensed under the MIT License - see the LICENSE file for details.
Contact
For any questions or support, please contact [your-email@example.com].

