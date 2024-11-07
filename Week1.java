import java.time.Period;
import java.util.Scanner;
import java.time.LocalDate;

//Week1

public class Week1 {
    public static void main(String[] args) {

            //Task 1
            System.out.println("Hello World"); //Output's "Hello world" using Println (Print Line)


            Scanner scanner = new Scanner(System.in); //Introduces the java.util.Scanner


            //Task 2
            System.out.print("Enter your name "); //Output's a question to later store as a variable
            String name = scanner.nextLine(); // Read the user's previous input and defines/stores this as a variable
            System.out.println("Hello " + name + "!"); //Output's the information


            //Task 3
            System.out.print("Enter the Length of the Rectangle ");
            double length = scanner.nextDouble();
            
            System.out.print("Enter the Width of the Rectangle");
            double width =scanner.nextDouble();

            double area = length * width;
            double perimeter = 2*(length+width);

            System.out.println("The area of this Rectangle is "+ area);
            System.out.println("The perimeter of this Rectangle is "+ perimeter);


            //Task 4
            System.out.println("Enter the first number: ");
            double num1 = scanner.nextDouble();  // Using double to handle decimal numbers
    
            System.out.println("Enter the second number: "); // Prompt the user to enter the second number
            double num2 = scanner.nextDouble();  
    
            double average = (num1 + num2) / 2;// Calculating the average
    
            System.out.println("The average of the two numbers is: " + average);// Displaying the result

            //Task 5
            System.out.print("Enter the first integer: ");
            int number1 = scanner.nextInt();
    
            System.out.print("Enter the second integer: "); // Prompt the user to enter the second integer
            int number2 = scanner.nextInt();
    
            // Displays the Values
            System.out.println("Original order: "+number1 +"| " + number2);
             System.out.println("Swapped order: "+number2 +"| "+ number1);

            //Task 6

            
    }
}


