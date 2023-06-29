// Terry Ford Jr - Project 1 - CMP SCI 3130-002
// Polynomial Evaluation
// I really enjoyed this project, I felt it was interesting to see how crazy efficient Horner's Rule is!
// Sources:
//  Math.random() info : https://www.freecodecamp.org/news/generate-random-numbers-java/
//  System time info :   https://stackoverflow.com/questions/3382954/measure-execution-time-for-a-java-method

package edu.umsl.algorithms;
import java.util.Scanner;

public class Polynomials {
    public static void main(String[] args) {
        System.out.println("This program will allow you to compare the efficiency of different algorithms " +
                "that evaluate polynomials.");

//             Polynomial Generation
        int degree = 50000;
        double x = (Math.random() + .000000000000000001);     // Between 0 and 1 exclusive
        int[] coefficients = new int[degree + 1];

        System.out.println("Polynomial has degree = " + degree + ", x = " + x + " and random coefficients ranging"
                + " between 1 and 100 inclusive." );
        for (int i = 0; i <= degree; i++) {
            coefficients[i] = (1 + (int)(100 * Math.random()));       // Between 1 and 100 inclusive
//                 Print statement for small polynomials
//            System.out.print(coefficients[i] + "x^" + i);
//            if (i != degree)
//                System.out.print(" + ");
        }

        printMenu();
        long start;
        double total;

                // MAIN MENU LOOP
        while (true) {

            byte choice = inputValidate();
            switch (choice) {
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("You've chosen 1 for monomial evaluation using a simple for loop.");
                    start = System.currentTimeMillis();
                    total = coefficients[0];

                    for (int i = 1; i <= degree; i++) {
                        double exponentProduct = x;
                        for (int j = 1; j < i; j++) {
                            exponentProduct = (exponentProduct * x);
                        }
                        total += (coefficients[i] * exponentProduct);
                    }
                    System.out.print("Polynomial = " + total);
                    System.out.println("\tEvaluation took " + (System.currentTimeMillis() - start) + " ms!");
                    break;

                case 2:
                    System.out.println("You've chosen 2 for monomial evaluation using a Java power function.");
                    start = System.currentTimeMillis();
                    total = coefficients[0];

                    for (int i = 1; i <= degree; i++) {
                        total += (coefficients[i] * Math.pow(x, i));
                    }
                    System.out.print("Polynomial = " + total);
                    System.out.println("\t\tEvaluation took " + (System.currentTimeMillis() - start) + " ms!");
                    break;

                case 3:
                    System.out.println("You've chosen 3 for polynomial evaluation with Horner's Rule");
                    start = System.currentTimeMillis();
                    total = coefficients[degree];

                    for (int i = degree; i > -1; i--) {
                        total = ((total * x) + coefficients[i]);
                    }
                    System.out.print("Polynomial = " + total);
                    System.out.println("\t\tEvaluation took " + (System.currentTimeMillis() - start) + " ms!");
                    break;
            }
        }
    }


    public static void printMenu() {     // PRINT MENU METHOD
        System.out.println("Here are your choices:\n" +
                "\t- 0. End the program.\n" +
                "\t- 1. Monomial evaluation using multiplication and simple for loops.\n" +
                "\t- 2. Monomial evaluation using Java power function Math.pow().\n" +
                "\t- 3. Horner's Rule to evaluate the whole polynomial without calculating monomials.");
    }


    public static byte inputValidate() {      // INPUT VALIDATE METHOD
        byte input = 5;
        boolean retry;
        do {
            System.out.print("\nPlease enter your choice: ");
            Scanner sc = new Scanner(System.in);
            retry = false;

            try {                               // try/catch block to prevent throwing an exception
                input = Byte.parseByte(sc.nextLine());
            } catch (Exception ex) {
                System.out.println("Exceptions handled.");
                retry = true;
            }

            if (input > 3 || input < 0) {             // Size checking user if input is invalid
                retry = true;
                System.out.println("Must choose 0, 1, 2, or 3.\n");
            }
        } while (retry);
        return input;
    }
}