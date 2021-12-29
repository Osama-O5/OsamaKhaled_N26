# Petstore - REST Assured

This test automation project created for N26 API Task .
	
Solution information
----------------------
The project is supposed to automate the proposed test cases (listed in below section) using REST assured with a modular framework which allows us to scale & maintain with ease. The solution uses Maven for build compilation & dependency management. Allure reports to generate clear, user readable & presentable reports. Uses java faker library to generate random fake data wherever required.
	
Proposed Test cases for Automation:
----------------------
**Pet Endpoint**
- Creating a new pet
- Fetch all pets by statuses
- Finding pet by given id
- updating pet by given id
- updating pet by form data

**Store Endpoint**
- Fetch the inventory status
- Place a new order
- Fetch the given order by ID
- Delete an newly created order

**User Endpoint**
- Fetch user details by username
- Create a new user
- Log the user into the system
- Log out the current active session	
	
Instructions to run Swagger Petstore - OpenAPI 3.0 locally:
----------------------
- Clone this project (https://github.com/swagger-api/swagger-petstore)
- Follow instructions from the project's readme file
	
Run Automated Tests from IntelliJ / Terminal
----------------------
1. Navigate to 'PetStore-RestAssured' folder from terminal window
2. Run following command -> ``` mvn clean test```

OR -- Run every test class individually by click on rigth click on the class name then select Run "class name"

Reporting - Allure reports
----------------------
1.From 'PetStore-RestAssured' folder, navigate to 'target' folder from terminal
2.Run following command -> allure serve allure-results
3.An Allure report will open in browser with the last run report.

Tools/Tech stack
----------------------
- REST-assured
- Maven
- Junit5
- Allure Reports
- Java
- IntelliJ
- Git
