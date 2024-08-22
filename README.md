# Test Automation Framework

## Overview

This project is a comprehensive test automation framework for an eCommerce application, utilizing Selenium WebDriver, TestNG, and Allure for reporting. The framework is designed to cover various test scenarios, including:

- User authentication
- Product selection
- Cart functionality
- End-to-end user journeys

The framework is also designed for straightforward integration with CI/CD pipelines such as Jenkins. It supports automated building, testing, and reporting, ensuring continuous integration .

## Project Structure

The project is organized into the following packages:

- **`com.evy.test`**: Contains TestNG test classes.
  - `BaseTest.java`: Provides setup and teardown methods for tests.
  - `AddProductToCartTest.java`: Tests adding products to the cart.
  - `EndToEndTest.java`: Tests a complete user journey from login to order placement.
  - `LoginTest.java`: Tests various login scenarios.
  - `ProductDropdownTest.java`: Tests category selection from dropdown menus.
  - `ProductListingTest.java`: Tests product selection by name.
  - `RegisterTest.java`: Tests various user registration scenarios.
- **`com.evy.framework.config`**: Manages configuration settings.
- **`com.evy.framework.driver`**: Handles WebDriver instances.
- **`com.evy.framework.pages`**: Contains page object classes.
- **`com.evy.framework.utils`**: Includes utility classes, such as assertion utilities.
- **`com.evy.framework.listener`**: Custom TestNG listeners for enhanced reporting.

## Building and Running

To build and run the framework, use the following Maven commands:

- **Clean and Install:**
  ```bash
  mvn clean install

- **Generate and Serve Allure Report:**
  ```bash
  mvn allure:serve

