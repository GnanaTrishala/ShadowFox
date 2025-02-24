import java.util.Scanner;

public class Calculator {

    public static double basicOperations(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    System.out.println("Error! Division by zero is not allowed.");
                    return Double.NaN; // NaN means Not a Number
                }
                return num1 / num2;
            default:
                System.out.println("Invalid operator!");
                return Double.NaN;
        }
    }

    public static double scientificOperations(int choice, double num) {
        switch (choice) {
            case 1:
                return Math.sqrt(num); // Square root
            case 2:
                return Math.pow(num, 2); // Square
            case 3:
                return Math.pow(num, 3); // Cube
            case 4:
                return Math.log(num); // Logarithm
            case 5:
                return Math.exp(num); // Exponential
            default:
                System.out.println("Invalid choice!");
                return Double.NaN;
        }
    }

    // Method for unit conversions
    public static double unitConversion(int choice, double value) {
        switch (choice) {
            case 1:
                return (value * 9 / 5) + 32; // Celsius to Fahrenheit
            case 2:
                return (value - 32) * 5 / 9; // Fahrenheit to Celsius
            case 3:
                return value * 74.85; // USD to INR (Example rate)
            case 4:
                return value / 74.85; // INR to USD (Example rate)
            default:
                System.out.println("Invalid choice!");
                return Double.NaN;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Enhanced Console-Based Calculator =====");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                System.out.println("Exiting... Thank you for using the calculator!");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter first number: ");
                    double num1 = scanner.nextDouble();
                    System.out.print("Enter operator (+, -, *, /): ");
                    char operator = scanner.next().charAt(0);
                    System.out.print("Enter second number: ");
                    double num2 = scanner.nextDouble();
                    double result = basicOperations(num1, num2, operator);
                    System.out.println("Result: " + result);
                    break;

                case 2:
                    System.out.println("1. Square Root");
                    System.out.println("2. Square");
                    System.out.println("3. Cube");
                    System.out.println("4. Logarithm");
                    System.out.println("5. Exponential");
                    System.out.print("Choose a scientific operation: ");
                    int sciChoice = scanner.nextInt();
                    System.out.print("Enter number: ");
                    double sciNum = scanner.nextDouble();
                    double sciResult = scientificOperations(sciChoice, sciNum);
                    System.out.println("Result: " + sciResult);
                    break;

                case 3:
                    System.out.println("1. Celsius to Fahrenheit");
                    System.out.println("2. Fahrenheit to Celsius");
                    System.out.println("3. USD to INR");
                    System.out.println("4. INR to USD");
                    System.out.print("Choose a conversion type: ");
                    int convChoice = scanner.nextInt();
                    System.out.print("Enter value: ");
                    double convValue = scanner.nextDouble();
                    double convResult = unitConversion(convChoice, convValue);
                    System.out.println("Converted Value: " + convResult);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
