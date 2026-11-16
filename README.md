# Java Selenium Automation

A UI test automation project built with Java, Selenium WebDriver, JUnit 5, and Maven. It automates key user workflows on the SauceDemo demo application.

**Author:** DeMarko Moore

## Project Overview

This project demonstrates browser-based functional testing using Selenium WebDriver and the Page Object Model (POM).

The test suite covers login, product inventory, shopping cart, and checkout workflows. It also captures screenshots when test methods fail.

## Technology Stack

- Java 17
- Selenium WebDriver 4.27.0
- JUnit 5.11.4
- Apache Maven
- Chrome / Chromium
- GitHub Actions

## Test Coverage

### Login
- Valid user login
- Invalid login credentials
- Empty username validation
- Locked-out user login

### Product Inventory
- Verify all six products are displayed
- Sort products by name in ascending order
- Sort products by price in ascending order

### Shopping Cart
- Verify the cart badge updates after adding a product
- Verify the selected product appears in the cart
- Verify the product name in the cart
- Remove a product and verify the cart is empty

### Checkout
- Validate required customer information
- Verify valid information advances to checkout overview
- Verify product details and order totals
- Complete checkout and verify order confirmation

## Design and Implementation

- **Page Object Model:** Separates page locators and browser interactions from test logic.
- **Reusable test setup:** A shared base class initializes and closes WebDriver.
- **Explicit waits:** Waits for important page elements and interactions.
- **Failure screenshots:** Saves screenshots under `target/screenshots/` when test methods fail.
- **Continuous integration:** GitHub Actions runs Maven tests on pushes, pull requests, and manual workflow dispatches.

## Prerequisites

- Java 17 or later
- Apache Maven
- Google Chrome or Chromium

## Run the Tests

From the project directory, run:

```bash
mvn test
```

Maven generates test reports under `target/surefire-reports/`.

Failure screenshots are saved under `target/screenshots/`.

## Continuous Integration

The GitHub Actions workflow runs the Maven test suite using Java 17 and Chrome. It uploads test reports and, when a workflow run fails, available failure screenshots as artifacts.

## Latest Local Test Results

- Tests run: 16
- Passed: 16
- Failed: 0
- Errors: 0
- Skipped: 0
- Result: BUILD SUCCESS
