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

    }
}


