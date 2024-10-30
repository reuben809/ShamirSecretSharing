package com.shamir;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShamirSecretSharing {

    // Function to decode value from given base to decimal
    private static BigInteger decodeValue(String value, String base) {
        try {
            return new BigInteger(value, Integer.parseInt(base));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value or base: value=" + value + ", base=" + base);
        }
    }

    // Function to read and parse JSON input
    private static List<Point> parseInput(JSONObject jsonInput) {
        List<Point> points = new ArrayList<>();
        JSONObject keys = (JSONObject) jsonInput.get("keys");
        int n = Integer.parseInt(keys.get("n").toString());
        int k = Integer.parseInt(keys.get("k").toString());

        if (k > n) {
            throw new IllegalArgumentException("k cannot be greater than n");
        }

        // Parse each point
        for (Object key : jsonInput.keySet()) {
            if (!key.equals("keys")) {
                JSONObject point = (JSONObject) jsonInput.get(key);
                String base = (String) point.get("base");
                String value = (String) point.get("value");

                try {
                    int x = Integer.parseInt(key.toString());
                    BigInteger y = decodeValue(value, base);
                    points.add(new Point(x, y));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid point data: key=" + key);
                }
            }
        }

        if (points.size() < k) {
            throw new IllegalArgumentException("Not enough points provided");
        }

        // Sort points by x value and take first k points
        points.sort((a, b) -> Integer.compare(a.x, b.x));
        return points.subList(0, k);
    }

    // Lagrange interpolation to find the constant term (secret)
    private static BigInteger findSecret(List<Point> points) {
        BigInteger secret = BigInteger.ZERO;
        int k = points.size();

        for (int i = 0; i < k; i++) {
            BigInteger term = points.get(i).y;
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    int xi = points.get(i).x;
                    int xj = points.get(j).x;

                    numerator = numerator.multiply(BigInteger.valueOf(-xj));
                    denominator = denominator.multiply(BigInteger.valueOf(xi - xj));
                }
            }

            try {
                term = term.multiply(numerator).divide(denominator);
                secret = secret.add(term);
            } catch (ArithmeticException e) {
                throw new IllegalStateException("Error in calculation: division by zero or overflow");
            }
        }

        return secret;
    }

    // Read JSON from file
    private static String readJsonFile(String filePath) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath, e);
        }
    }

    // Process a single test case
    private static void processTestCase(String jsonStr, int testCaseNumber) {
        try {
            System.out.println("\n=== Processing Test Case " + testCaseNumber + " ===");

            // Parse JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonInput = (JSONObject) parser.parse(jsonStr);

            // Print input details
            JSONObject keys = (JSONObject) jsonInput.get("keys");
            System.out.println("Number of points (n): " + keys.get("n"));
            System.out.println("Required points (k): " + keys.get("k"));

            // Parse input and get points
            List<Point> points = parseInput(jsonInput);

            // Print points being used
            System.out.println("\nUsing points:");
            for (Point p : points) {
                System.out.println("x: " + p.x + ", y: " + p.y);
            }

            // Find the secret
            BigInteger secret = findSecret(points);

            // Print result
            System.out.println("\nResult:");
            System.out.println("Secret (constant term): " + secret);
            System.out.println("==============================");

        } catch (ParseException e) {
            System.err.println("Error parsing JSON in test case " + testCaseNumber + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input in test case " + testCaseNumber + ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error in test case " + testCaseNumber + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                // Process all provided input files
                for (int i = 0; i < args.length; i++) {
                    String jsonStr = readJsonFile(args[i]);
                    processTestCase(jsonStr, i + 1);
                }
            } else {
                // Use default test cases
                String[] testCases = {
                        // Test Case 1
                        "{\"keys\":{\"n\":4,\"k\":3},\"1\":{\"base\":\"10\",\"value\":\"4\"},\"2\":{\"base\":\"2\",\"value\":\"111\"},\"3\":{\"base\":\"10\",\"value\":\"12\"},\"6\":{\"base\":\"4\",\"value\":\"213\"}}",

                        // Test Case 2
                        "{\"keys\":{\"n\":3,\"k\":2},\"1\":{\"base\":\"10\",\"value\":\"5\"},\"2\":{\"base\":\"2\",\"value\":\"110\"},\"3\":{\"base\":\"10\",\"value\":\"9\"}}"
                };

                for (int i = 0; i < testCases.length; i++) {
                    processTestCase(testCases[i], i + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
}