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

3. **Run the application with multiple input files:**

   ```bash
   ./gradlew run --args="src/main/resources/testcases/input1.json src/main/resources/testcases/input2.json"
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
### Example Input (input2.json)

```json
{
"keys": {
    "n": 10,
    "k": 7
  },
  "1": {
    "base": "6",
    "value": "13444211440455345511"
  },
  "2": {
    "base": "15",
    "value": "aed7015a346d63"
  },
  "3": {
    "base": "15",
    "value": "6aeeb69631c227c"
  },
  "4": {
    "base": "16",
    "value": "e1b5e05623d881f"
  },
  "5": {
    "base": "8",
    "value": "316034514573652620673"
  },
  "6": {
    "base": "3",
    "value": "2122212201122002221120200210011020220200"
  },
  "7": {
    "base": "3",
    "value": "20120221122211000100210021102001201112121"
  },
  "8": {
    "base": "6",
    "value": "20220554335330240002224253"
  },
  "9": {
    "base": "12",
    "value": "45153788322a1255483"
  },
  "10": {
    "base": "7",
    "value": "1101613130313526312514143"
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

=== Processing Test Case 2 ===
Number of points (n): 10
Required points (k): 7

Using points:
x: 1, y: 995085094601491
x: 2, y: 21394886326566393
x: 3, y: 196563650089608567
x: 4, y: 1016509518118225951
x: 5, y: 3711974121218449851
x: 6, y: 10788619898233492461
x: 7, y: 26709394976508342463

Result:
Secret (constant term): 79836264049851
==============================

```

## Acknowledgments

- [Shamir's Secret Sharing](https://en.wikipedia.org/wiki/Shamir%27s_Secret_Sharing) for providing the theoretical basis for this implementation.
- Java and Gradle for being the foundation of this project.

## Contact

For any inquiries, please contact:

- **Name**: Reuben Sebastian Joseph  
- **Email**: reuben.joseph010@icloud.com  
- **GitHub**: [reuben809](https://github.com/reuben809)

