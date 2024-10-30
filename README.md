# Shamir Secret Sharing

## Overview

This project implements Shamir's Secret Sharing scheme, a cryptographic method that allows a secret to be divided into multiple parts, where only a subset of those parts can be combined to reconstruct the original secret. This approach ensures that no single participant has access to the entire secret, enhancing security and fault tolerance.

## Features

- Divide a secret into multiple shares.
- Reconstruct the secret from a subset of shares.
- Support for configurable parameters (number of shares, threshold shares required for reconstruction).
- Command-line interface for easy interaction.
- Input and output handled via JSON files.

## Prerequisites

- Java 21 or later
- Gradle 8.10 or later

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/reuben809/ShamirSecretSharing.git
   cd ShamirSecretSharing
   ```

2. **Build the project:**

   ```bash
   ./gradlew clean build
   ```

3. **Run the application:**

   ```bash
   ./gradlew run --args="src/main/resources/testcases/input1.json"
   ```

## Usage

The application processes test cases defined in JSON files located in the `src/main/resources/testcases` directory. Each test case should specify the number of points and the required points for reconstruction. The application will output the secret (constant term) based on the provided points.

### Example Input (input1.json)

```json
{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2",
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
}
```

### Output

Upon running the application, you will receive output indicating the processed test cases and the reconstructed secret.

```
=== Processing Test Case 1 ===
Number of points (n): 4
Required points (k): 3

Using points:
x: 1, y: 4
x: 2, y: 7
x: 3, y: 12

Result:
Secret (constant term): 3
==============================

```

## Acknowledgments

- [Shamir's Secret Sharing](https://en.wikipedia.org/wiki/Shamir%27s_Secret_Sharing) for providing the theoretical basis for this implementation.
- Java and Gradle for being the foundation of this project.

