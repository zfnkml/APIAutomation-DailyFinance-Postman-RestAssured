# API Automation (Rest Assured)- Daily Finance

## Project Description

This project demonstrates API automation testing for the **Daily Finance** web application. The project includes creating a Postman collection for core features, automating the collection using **Rest Assured**, and following the best practices of POM (Page Object Model) architecture.

## Features Tested

The following features were tested and automated:

1. **User Management**:
    - Register a new user
    - Login as admin
    - Get the user list
    - Search for a user by user ID
    - Edit user information (e.g., first name, phone number)
    - Login as a regular user
2. **Item Management**:
    - Get the item list
    - Add a new item
    - Edit an item name
    - Delete an item from the item list

## Prerequisites

- **Java 11 or higher**
- **Gradle** (build tool)
- **Postman** (for initial API collection creation)
- **Allure CLI** (for generating detailed test reports)

## Setup Instructions

1. Clone the repository:
    
    ```bash
    git clone https://github.com/zfnkml/APIAutomation-DailyFinance-Postman-RestAssured.git
    
    ```
    
2. **Navigate to the Project Directory**:
    
    ```bash
    cd APIAutomation-DailyFinance-Postman-RestAssured
    
    ```
    
3. **Build the Project**:
    
    ```bash
    gradle build
    
    ```
    
4. **Run the Automated Tests**:
    
    ```bash
    gradle test
    
    ```
    
5. **Generate Allure Report**:
    
    ```bash
    allure serve build/allure-results
    
    ```
    
6. Import the Postman collection to Postman:

## Postman Collection

The Postman collection used for testing can be accessed here:

- [Postman Collection Documentation](https://documenter.getpostman.com/view/17817250/2sAYQZJYRY)

## Test Cases

Detailed test cases are documented in the following link:

- [Test Case Documentation](https://docs.google.com/spreadsheets/d/1xpN2TTMZc2MjYNM9h_v80sKn7TegN6Bl/edit?usp=sharing&ouid=107301444027344847556&rtpof=true&sd=true)

## Allure Report

Below is a screenshot of the Allure report generated during test execution:

![image](https://github.com/user-attachments/assets/94b6c4d3-1b62-4618-90bc-078a0de390ad)

![image](https://github.com/user-attachments/assets/bf5c17e3-86a2-4368-8187-b09151fc024d)

## Acknowledgments

- Special thanks to the creators of Rest Assured and Allure for their excellent tools.
- Inspired by the Daily Finance API testing requirements.
