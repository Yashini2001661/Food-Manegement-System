//ARRAY VERSION-TASK 01
import java.util.*;  //import java.util
import java.io.*; //import java io
import java.io.FileWriter; //import java.oi.FileWriter

public class Main {
    private static final int maximum_customers[] = {2, 3, 5};  //define the maximum number of customers
    private static String[][] cashiers = new String[3][maximum_customers[2]]; //declare the cashiers in the shop array
    private static int[] num_of_customers = new int[3];  //initialize the count of customers array
    private static final int maximum_burgers = 50; //initialize the  maximum stock of burgers available in the shop
    private static final int burgers_per_customer = 5; //initialize  burgers per each customer
    private static final int minimum_burgers = 10; //initialize the minimum stock of burgers in the shop
    private static int burger_stock = maximum_burgers; //initialize the current burger stock in the shop

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n                      ----------->>> WELCOME TO THE FOODIES FAVE FOOD CENTER! <<<-----------\n "); //Display welcome message
        System.out.println("HOME-COOKED CHEESE BURGER"); //display BURGER NAME
        System.out.println(">>> SPECIAL PRICE - $650 <<<"); //display price of a burger
//print menu
        System.out.println("****************************************************************************");
        while (true) {
        System.out.println("\nPlease select an option:\n" + " 100 or VAQ : View all Queues\n" + " 101 or VEQ : View all Empty Queues\n"
                + " 102 or ACQ : Add customer to a Queue\n" + " 103 or RCQ : Remove a customer from a Queue\n" + " 104 or PCQ : Remove a served customer\n"
                + " 105 or VCS : View Customers Sorted in alphabetical order\n" + " 106 or SPD : Store Program Data into file\n"
                + " 107 or LPD : Load Program Data from file\n" + " 108 or STK : View Remaining burgers Stock\n" + " 109 or AFS : Add burgers to Stock\n"
                + " 999 or EXIT : Exit the Program\n");
        System.out.println("***************************************************************************");

                System.out.print("Enter your option here ====>>> ");  //get user option
                String option = scanner.next().toUpperCase();  // Get user inputs and convert to uppercase

                System.out.println("Your Option is: " + option + "\n");  //display the user option
                switch (option) { //switch options
                    case "100":
                    case "VAQ":
                        viewAllQueues(); //calling for view all queues method
                        break;
                    case "101":
                    case "VEQ":
                        viewEmptyQueues(); //calling for view empty queues method
                        break;
                    case "102":
                    case "ACQ":
                        AddcustomerQueu(scanner);  //calling for add customer method
                        break;
                    case "103":
                    case "RCQ":
                        RemovecustomerQueue(scanner);  //calling for remove customer queue method
                        break;
                    case "104":
                    case "PCQ":
                        RemoveServedCustomer(); //calling for remove served customer method
                        break;
                    case "105":
                    case "VCS":
                        ViewCustomersSorted(); //calling for view customer sorted method
                        break;
                    case "106":
                    case "SPD":
                        StoreData();  //calling for store data method
                        break;
                    case "107":
                    case "LPD":
                        LoadData(); //calling for load data method
                        break;
                    case "108":
                    case "STK":
                        ViewRemainingburgersStock(); //calling for view remaining burgers stock method
                        break;
                    case "109":
                    case "AFS":
                        AddBurgersToStock(scanner);  //calling for add burgers to stock method
                        break;
                    case "999":
                    case "EXIT":
                        System.out.println("You have successfully exited the programme!"); //display exit message
                        System.out.println(">>Thank you! Come again!<<"); //display thank you message
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option selected! Try again!\n"); //display invalid message
                        System.out.println("-------------------------------------------------------------------------");
                }
            }
        }
    private static void viewAllQueues() {
        System.out.println("___VIEW ALL QUEUES___");//display view all queues header
        System.out.println("*****************");  //display header of cashiers
        System.out.println("*   Cashiers    *");
        System.out.println("*****************");

        for (int i = 0; i < 3; i++) {  //represent each type of cashier
            for (int j = 0; j < maximum_customers[i]; j++) {  //represent individual cashiers in each types
                if (cashiers[i][j] == null) {   //check  cashiers elements equal to zero
                    System.out.print("X "); //if cashier is available or there is no customer, print "X"

                } else {
                    System.out.print("O ");//if cashier is occupied by the customer,print "O"
                }
            }
            System.out.println();  //move to the next row of cashiers

        }
        System.out.println("Occupied-O       Not occupied-X\n"); //display occupied-o and occupied-x
        System.out.println("-------------------------------------------------------------------------");
    }

    private static void viewEmptyQueues() {
        System.out.println("___EMPTY QUEUES___"); //display empty queues header
        boolean empty = false; // find if any empty queue is found
        for (int i = 0; i < 3; i++) { //check empty queues
            if (num_of_customers[i] == 0) { //check number of customers equal to zero
                System.out.println("Cashier " + (i + 1) + " is empty.\n"); //display empty queues
                empty = true;// assign empty tag to true if an empty queue is found
            }
        }
        if (!empty) { //check if no empty queues
            System.out.println("No empty queues! All full!");  //display no empty queue message
        }
        System.out.println("-------------------------------------------------------------------------");
}

    private static void AddcustomerQueu(Scanner scanner) {
        System.out.println("___ADD CUSTOMER___"); //display add customer header
        System.out.print("Enter the cashier number (1-3): "); //get cashier number from 1-3

        while (!scanner.hasNextInt()) {  // Validate input as an integer
            String invalidInput = scanner.next();  // Read and discard the invalid input
            System.out.println("Invalid input! Please enter a valid cashier number (1-3): "); //display invalid input message
        }
        int cashierNum = scanner.nextInt() - 1; //read user input and subtract 1 from it to convert an index

        if (cashierNum < 0 || cashierNum >= 3) {  //check if  cashier number validated
            System.out.println("Error! An invalid cashier number! \n"); //if invalid num entered, display invalid message
            return;
        }

        if (num_of_customers[cashierNum] == maximum_customers[cashierNum]) { //check customer count equal to number of maximum customers
            System.out.println("Cashier " + (cashierNum + 1) + " is full! Customers are added to the waiting list."); //print already full and adding waiting list message
            return;
        }

        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the customer's name: \n");  //get the customer's name
        String customerName = scanner.nextLine(); //get name input

        cashiers[cashierNum][num_of_customers[cashierNum]] = customerName;  //add the customer name to the selected cashier's queue
        num_of_customers[cashierNum]++; // indicate that a new customer has been added to the queue by increment
        System.out.println(customerName+" added to the queue "+(cashierNum+1)+" Successfully!\n"); //print customer has been added message




        if (burger_stock <= minimum_burgers) { //  check the burger stock is below or equal to minimum stock
            System.out.println("Warning: Stock is running low! Only " + burger_stock + " burgers left.\n"); //print warning message to indicate remaining stock
        }
        System.out.println("-------------------------------------------------------------------------");
    }
    private static void RemovecustomerQueue(Scanner scanner) {
        System.out.println("___REMOVE CUSTOMER___"); //display remove customer header
        System.out.print("Enter the cashier number (1-3): ");  //get cashier number from 1-3
        while (!scanner.hasNextInt()) {  // Validate input as an integer
            String invalidInput = scanner.next();  // Read and discard the invalid input
            System.out.println("Invalid input! Please enter a valid cashier number (1-3): "); //display invalid input message
        }
        int cashierNum = scanner.nextInt() - 1; //get cashier number input and subtract 1 to access index

        if (cashierNum < 0 || cashierNum >= 3) { //check if cashier number is validate from 1-3
            System.out.println("Error! An invalid cashier number!\n");   //print invalid cashier number message
            System.out.println("-------------------------------------------------------------------------");
            return;
        }
        if (num_of_customers[cashierNum] == 0) { //check if the customer count equal to zero
            System.out.println("Cashier " + (cashierNum + 1) + " is empty\n");  //print the message to indicate cashier is empty
            System.out.println("-------------------------------------------------------------------------");
            return;
        }
        System.out.print("Enter the position of the customer to remove (1-" + num_of_customers[cashierNum] + "): \n"); //get the position of customer to remove

        while (!scanner.hasNextInt()) {  // Validate input as an integer
            String invalidInput = scanner.next();  // Read and discard the invalid input
            System.out.println("Invalid input! Please enter a valid position (1-"+ num_of_customers[cashierNum] + "): \n"); //display invalid input message
        }
        int position = scanner.nextInt();  //read the position input

        if (position < 1 || position > num_of_customers[cashierNum]) { //check the position is validated
            System.out.println("Error! An invalid position! \n"); //display invalid position message
            System.out.println("-------------------------------------------------------------------------");
            return;
        }
        for (int i = position - 1; i < num_of_customers[cashierNum] - 1; i++) { // shift the customers' names , starting from the specified position
            cashiers[cashierNum][i] = cashiers[cashierNum][i + 1];  //assign the position
        }

        cashiers[cashierNum][num_of_customers[cashierNum] - 1] = null;  //remove the customer by selected cashier's queue
        num_of_customers[cashierNum]--;   // indicate that a new customer has been remove the queue by decrement
        System.out.println("  Queue=> "+(cashierNum + 1)+" and position=> "+position +" Customer has been removed successfully!\n"); //print customer has been removed message
        System.out.println("-------------------------------------------------------------------------");
    }
    private static void RemoveServedCustomer() {
        System.out.println("___REMOVE SERVED CUSTOMER___");//display remove served customer header
        for (int i = 0; i < 3; i++) { //iterate to find the first cashier
            if (num_of_customers[i] > 0) { //check customer count grater then 0
                for (int j = 0; j < num_of_customers[i] - 1; j++) { //shift the customers' names in the cashier's queue
                    cashiers[i][j] = cashiers[i][j + 1]; //assign the cashier value
                }
                cashiers[i][num_of_customers[i] - 1] = null; // indicating that the first customer has been removed from the queue
                num_of_customers[i]--; //update customer count by decrement
                burger_stock -= burgers_per_customer; // reduce the stock by burgers per customer
                System.out.println("served customer has been removed successfully!\n"); //print customer has been removed message
                System.out.println("-------------------------------------------------------------------------");
                return;
            }
        }
        System.out.println("Error! No served customers in the queue to remove!\n"); //print no served customer message
        System.out.println("-------------------------------------------------------------------------");
    }
    private static void ViewCustomersSorted() {
        System.out.println("___VIEW CUSTOMERS SORTED IN ALPHABETICAL ORDER___"); //print view customer sorted header
        List<String> customers = new ArrayList<>();  //create an array list named customer

        for (int i = 0; i < 3; i++) { //iterate over cashiers
            for (int j = 0; j < num_of_customers[i]; j++) {  //iterate over customers
                customers.add(cashiers[i][j]);  //add customer to the list
            }
        }
        Collections.sort(customers); //sort customer names in alphabetical oder
        for (String customer : customers) {  //sort customers

            System.out.println(customer); //print customer names in oder
        }
        System.out.println("-------------------------------------------------------------------------");
    }
    private static void StoreData() {
        System.out.println("___STORE DATA___"); //display store data
        try {
            FileWriter fileWriter = new FileWriter("data.txt"); // Open the file for writing
            fileWriter.write("QUEUE DETAILS--> "); //display queue details
            for (int i = 0; i < 3; i++) {
                fileWriter.write("Cashier " + (i + 1) + " : " + num_of_customers[i] + " customer(s)   |  "); //print queue details about cashiers and customers
            }
            // Write the program data to the file
            fileWriter.write(" BURGER STOCK--> "+burger_stock);  //display burger stock details

            fileWriter.close(); // Close the file

            System.out.println("Data stored successfully!"); //display data stored successfully message
        }
        catch (IOException e) {
            System.out.println("Error while storing data! ");  //display error message
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    private static void LoadData() {
        System.out.println("___LOADING DATA___"); //display load data
        try {
            FileReader fileReader = new FileReader("data.txt"); // Open the file for reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);  // Read the program data from the file
            String data = bufferedReader.readLine();//read the text line
            bufferedReader.close();    // Close the file
            System.out.println( data); //print stored data
            System.out.println("Data loaded successfully!"); //display data loaded successfully message
        } catch (IOException e) {
            System.out.println("Error while loading data! "); //display error message
        }
        System.out.println("-------------------------------------------------------------------------");
    }
    private static void ViewRemainingburgersStock() {
        System.out.println("___REMAINING BURGERS___"); //display remaining burger stock header
        System.out.println("Remaining burgers in stock: " + burger_stock); //display remaining burgers in stock
        System.out.println("-------------------------------------------------------------------------");
    }
    private static void AddBurgersToStock(Scanner scanner) {
        System.out.println("___ADD BURGERS___"); //display add burgers header
        System.out.print("Enter the number of burgers to add: "); //get number of burgers to add
        while (!scanner.hasNextInt()) {  // Validate input as an integer
            String invalidInput = scanner.next();  // Read and discard the invalid input
            System.out.println("Invalid input! Please enter valid integer input: "); //display invalid input message
        }
        int quantity = scanner.nextInt(); //read quantity stock input
        burger_stock += quantity; //add burgers to the stock
        if (burger_stock>50){ //Check if the new burger stock is greater than the max burger stock
            System.out.println("Error!! The number of added burgers has exceeded the maximum number of burgers!"); //display overload message
            burger_stock -= quantity; //remove added burgers
            System.out.println("you can only add up "+(50-burger_stock)+" more!");} //Display  the number of burgers that can be added.
        else{
        System.out.println(quantity + " burgers added to the stock successfully!\n"); //display number of burgers added
        System.out.println("Total number of burgers in stock now: "+burger_stock + "\n"); }//display total number of burgers in the current stock
        System.out.println("-------------------------------------------------------------------------");
    }
}
//End of the code