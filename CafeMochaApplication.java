
package com.mycompany.cafemochaapplication;
import java.util.Scanner;
import java.io.*;


public class CafeMochaApplication {
    private static final String Username = "wickramasinghe";//admin login username
    private static final String Password = "1234cafe";//admin login password
    private static final Scanner scanner=new Scanner(System.in);//user input data
    private static final String CUSTORMER_FILE = "C:\\Users\\ASUS\\Desktop\\custormer.txt";//custom data store file
    private static final String ORDER_FILE = "C:\\Users\\ASUS\\Desktop\\orders.txt";//order details store file
    private static final String MENU_FILE = "C:\\Users\\ASUS\\Desktop\\menu.txt";//menu details store file
    private static final String BILL_FILE = "C:\\Users\\ASUS\\Desktop\\bill.txt";//billing details store file
    static int custormerIdCounter = 1;//Auto incrementing customer ID
    static int orderNumberCounter = 1;//Auto incrementing order number
    static int itemIdCounter = 1;//Auto incrementing item ID

    public static void main(String[] args) {
        if(!login() ) {//if you given wrong password or username then you can't login
            System.out.println("\nInvalid Username or Password! please check..!");//login error message
        }else{
            System.out.println("\nLogin Successfully!");//login successful message
            System.out.println("__        __         _                                       _____              ____            __                        \n" +
"\\ \\      / /   ___  | |   ___    ___    _ __ ___     ___    |_   _|   ___      / ___|   __ _   / _|   ___                 \n" +
" \\ \\ /\\ / /   / _ \\ | |  / __|  / _ \\  | '_ ` _ \\   / _ \\     | |    / _ \\    | |      / _` | | |_   / _ \\                \n" +
"  \\ V  V /   |  __/ | | | (__  | (_) | | | | | | | |  __/     | |   | (_) |   | |___  | (_| | |  _| |  __/                \n" +
"   \\_/\\_/     \\___| |_|  \\___|  \\___/  |_| |_| |_|  \\___|     |_|    \\___/     \\____|  \\__,_| |_|    \\___|                \n" +
" __  __                  _                 __  __                                                                     _   \n" +
"|  \\/  |   ___     ___  | |__     __ _    |  \\/  |   __ _   _ __     __ _    __ _    ___   _ __ ___     ___   _ __   | |_ \n" +
"| |\\/| |  / _ \\   / __| | '_ \\   / _` |   | |\\/| |  / _` | | '_ \\   / _` |  / _` |  / _ \\ | '_ ` _ \\   / _ \\ | '_ \\  | __|\n" +
"| |  | | | (_) | | (__  | | | | | (_| |   | |  | | | (_| | | | | | | (_| | | (_| | |  __/ | | | | | | |  __/ | | | | | |_ \n" +
"|_|  |_|  \\___/   \\___| |_| |_|  \\__,_|   |_|  |_|  \\__,_| |_| |_|  \\__,_|  \\__, |  \\___| |_| |_| |_|  \\___| |_| |_|  \\__|\n" +
" ____                  _                                                    |___/                                         \n" +
"/ ___|   _   _   ___  | |_    ___   _ __ ___                                                                              \n" +
"\\___ \\  | | | | / __| | __|  / _ \\ | '_ ` _ \\                                                                             \n" +
" ___) | | |_| | \\__ \\ | |_  |  __/ | | | | | |                                                                            \n" +
"|____/   \\__, | |___/  \\__|  \\___| |_| |_| |_|                                                                            \n" +
"         |___/                                                                                                            ");//welcome messege
            initializeCustormerIdCounter();//start for counting custormer id based on custormer file(all customers receive unique IDs.)
        
            initializeOrderNumberCounter();//start for counting order numbers based on order file(all orders are received unique number)
            
        
            initializeItemIdCounter();//start for counting item id based on menu file(all items are received unique id)
            
            while (true) {
            displayMainMenu();//main menu function
            int choice = scanner.nextInt();//store the value that user input
            scanner.nextLine(); 
            switch(choice){
                case 1:
                    custormerDetails();//custormer details function
                    break;
                case 2:
                    orderDetails();//order details function
                    break;
                case 3:
                    displayOrderDetailsByOrderNumber();//display an order details function
                    break;
                case 4:
                    calculateBill();//bill details function
                    break;
                case 5:
                    menuItem();//item details function
                    break;
                case 6:
                    displayHelp();//help function
                    break;
                case 7:
                    System.out.println(" _____   _                       _                                   __                                  _                           \n" +
"|_   _| | |__     __ _   _ __   | | __    _   _    ___    _   _     / _|   ___    _ __     _   _   ___  (_)  _ __     __ _           \n" +
"  | |   | '_ \\   / _` | | '_ \\  | |/ /   | | | |  / _ \\  | | | |   | |_   / _ \\  | '__|   | | | | / __| | | | '_ \\   / _` |          \n" +
"  | |   | | | | | (_| | | | | | |   <    | |_| | | (_) | | |_| |   |  _| | (_) | | |      | |_| | \\__ \\ | | | | | | | (_| |          \n" +
"  |_|   |_| |_|  \\__,_| |_| |_| |_|\\_\\    \\__, |  \\___/   \\__,_|   |_|    \\___/  |_|       \\__,_| |___/ |_| |_| |_|  \\__, |          \n" +
"                                          |___/                                                                      |___/           \n" +
" _     _                 ____            __            __  __                  _                                                     \n" +
"| |_  | |__     ___     / ___|   __ _   / _|   ___    |  \\/  |   ___     ___  | |__     __ _                                         \n" +
"| __| | '_ \\   / _ \\   | |      / _` | | |_   / _ \\   | |\\/| |  / _ \\   / __| | '_ \\   / _` |                                        \n" +
"| |_  | | | | |  __/   | |___  | (_| | |  _| |  __/   | |  | | | (_) | | (__  | | | | | (_| |                                        \n" +
" \\__| |_| |_|  \\___|    \\____|  \\__,_| |_|    \\___|   |_|  |_|  \\___/   \\___| |_| |_|  \\__,_|                                        \n" +
" __  __                                                                     _       ____                  _                          \n" +
"|  \\/  |   __ _   _ __     __ _    __ _    ___   _ __ ___     ___   _ __   | |_    / ___|   _   _   ___  | |_    ___   _ __ ___      \n" +
"| |\\/| |  / _` | | '_ \\   / _` |  / _` |  / _ \\ | '_ ` _ \\   / _ \\ | '_ \\  | __|   \\___ \\  | | | | / __| | __|  / _ \\ | '_ ` _ \\     \n" +
"| |  | | | (_| | | | | | | (_| | | (_| | |  __/ | | | | | | |  __/ | | | | | |_     ___) | | |_| | \\__ \\ | |_  |  __/ | | | | | |  _ \n" +
"|_|  |_|  \\__,_| |_| |_|  \\__,_|  \\__, |  \\___| |_| |_| |_|  \\___| |_| |_|  \\__|   |____/   \\__, | |___/  \\__|  \\___| |_| |_| |_| (_)\n" +
" _   _                            |___/                                   _           _     |___/         _                          \n" +
"| | | |   __ _  __   __   ___      __ _      __ _   _ __    ___    __ _  | |_      __| |   __ _   _   _  | |                         \n" +
"| |_| |  / _` | \\ \\ / /  / _ \\    / _` |    / _` | | '__|  / _ \\  / _` | | __|    / _` |  / _` | | | | | | |                         \n" +
"|  _  | | (_| |  \\ V /  |  __/   | (_| |   | (_| | | |    |  __/ | (_| | | |_    | (_| | | (_| | | |_| | |_|                         \n" +
"|_| |_|  \\__,_|   \\_/    \\___|    \\__,_|    \\__, | |_|     \\___|  \\__,_|  \\__|    \\__,_|  \\__,_|  \\__, | (_)                         \n" +
"                                            |___/                                                 |___/                              ");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");//error message(when input anothe number)
            
            }//close switch case

            }//close while loop
            
    //close if statement
        }   
        
    }
    //-----------------------------------------------------login form-------------------------------------------------------------------------------//
    
    public static boolean login(){
        System.out.println("\n---------------------------------------");
        System.out.println("           Admin login form");
        System.out.println("---------------------------------------");
        System.out.println("Enter Username: ");//display enter username
        String username=scanner.nextLine();
        System.out.println("Enter Password: ");//display enter password
        String password=scanner.nextLine();
        return username.equals(Username) && password.equals(Password);//checking username and password are correct
    }//end login form
    
    //------------------------------------------------main menu options-------------------------------------------------------------------------------//
    
    public static void displayMainMenu() {
        System.out.println("\n---------------------------------------");
        System.out.println("               Main Menu");
        System.out.println("---------------------------------------");
        System.out.println("1. Custormer Details");//display main menu options
        System.out.println("2. Order Details");
        System.out.println("3. Display Order Details");
        System.out.println("4. Calculate Bill");
        System.out.println("5. Menu Item");
        System.out.println("6. Help");
        System.out.println("7. Exit");//logout option
        System.out.println("Enter your choice: ");//adding choosen number 
    }//end main menu
    
    //-----------------------------------------------1.custormer details----------------------------------------------------------------------------//
    
    public static void custormerDetails(){
        int type;
        do{ System.out.println("\n---------------------------------------");
            System.out.println("     Cafe Mocha Custormer Details");
            System.out.println("---------------------------------------");
            System.out.println("1.Add New Custormer");//using crud operations(add,read,update,delete and exit)
            System.out.println("2.Read Custormer");
            System.out.println("3.Update Custormer Details");
            System.out.println("4.Delete Custormer Details");
            System.out.println("5.Exit");
            System.out.println("Enter your choice: ");
            type=scanner.nextInt();
            scanner.nextLine();//spacing line
            switch(type){
                case 1:
                    addCustormer();//add custormer function
                    break;
                    
                case 2:  
                    readCustormer();//read custormer function
                    break;
                    
                case 3:
                    updateCustormer();//update custormer function
                    break;
                    
                case 4:
                    deleteCustormer();//delete custormer function
                    break;
                           
                case 5:
                    System.out.println("\nExiting Custormer Details Section");//exit from custormer details
                    return;
                    
                default:
                    System.out.println("\nInvalid choice,please try again.");//error messege
            }
         
        }while(type !=5);//end do while loop
        scanner.close();//close the scanner
    }

    
    public static void initializeCustormerIdCounter() {//starting counting custormer id automatically
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTORMER_FILE))) {//read file and auto close
            String line;
            int maxId = 0;//assign variable max custormer id found in file
            while ((line = reader.readLine()) != null) {//read the file line by line
                String[] customerDetails = line.split(",");//used to split custormer details
                int customerId = Integer.parseInt(customerDetails[0]);
                if (customerId > maxId) {//check if customer id higher than max id
                    maxId = customerId;//update max id 
                }
            }
            custormerIdCounter = maxId + 1; //increment by 1 to for next custormer id
        } catch (IOException e) {
            System.out.println("Error initializing customer ID counter: " + e.getMessage());//print error messege
        }
    }
    
//******************************************************add custormer details*******************************************************************//
    public static void addCustormer() {
        System.out.println("\n---------Add New Customer---------------");
        String custormerId = String.valueOf(custormerIdCounter++); //automatically increment customer ID
        System.out.print("\nEnter Customer Name: ");
        String customerName = scanner.nextLine();//read and store name
        System.out.print("Enter Customer Address: ");
        String address = scanner.nextLine();//read and store address
        System.out.print("Enter Telephone Number: ");
        String phone = scanner.nextLine();//read and store phonenumber
        System.out.print("Enter email: ");
        String email = scanner.nextLine();//read and store email

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTORMER_FILE, true))) {//open file for add data and close file
            writer.write(custormerId + "," + customerName + "," + address + "," + phone + "," + email);//data store 
            writer.newLine();//add new line to next custormer
            System.out.println("\nCustomer Details added successfully!");//comfirm successfully messege
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());//error messege during file writing process
        }
    }//end of adding new custormer
    
//************************************************************read custormer details************************************************************//
     
    public static void readCustormer() {
        System.out.println("\n----------Custmer list---------------");//print messege
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTORMER_FILE))) {//open file for read and closing file
            String line;
            while ((line = reader.readLine()) != null) {//read data store line by line
                String[] customerDetails = line.split(",");//split custormer details with ','
                System.out.println("\nCustomer ID: " + customerDetails[0]);//print each customer detail with appropriate labels
                System.out.println("Customer Name: " + customerDetails[1]);
                System.out.println("Address: " + customerDetails[2]);
                System.out.println("Phone: " + customerDetails[3]);
                System.out.println("Email: " + customerDetails[4]);
                System.out.println("----------------------------------------");//print line between different custormer details
            }
        } catch (IOException e) {//error handling
            System.out.println("Error reading file: " + e.getMessage());//error messege during file read
        }
    }
    
//***********************************************************update custormer details*********************************************************//   
     
    public static void updateCustormer() {
        System.out.println("\n---------Update Customer Details------------");//print messege
        System.out.print("\nEnter Custormer ID to Update: ");//enter custormer id have to update
        String custormerId = scanner.nextLine();

        File inputFile = new File(CUSTORMER_FILE);//define custormer file
        File tempFile = new File("temp_custormer.txt");//define tempery file

        boolean found = false;//check if custormer id was found

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));//open original and tempery files
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {//read line by line in original file
                String[] custormerDetails = currentLine.split(",");//split data
                if (custormerDetails[0].equals(custormerId)) {//checking if the custormer id matches to the update one
                    System.out.print("Enter New Customer Name: ");//enter have to edit details
                    String customerName = scanner.nextLine();
                    System.out.print("Enter New Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter New Telephone Number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter New Email: ");
                    String email = scanner.nextLine();

                    writer.write(custormerId + "," + customerName + "," + address + "," + phone + "," + email);//write updated details in tempery file
                    writer.newLine();
                    found = true;//if found custormer id and updated details then true process
                    System.out.println("\nCustomer Details updated successfully!");//print successfully messege
                } else {
                    writer.write(currentLine);//if does not match custormer id then write old details in tempery file
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing to file: " + e.getMessage());//display error messege during update file
        }
        
        if (inputFile.delete()) {//delete original file
            tempFile.renameTo(inputFile);//rename tempery file to original file name
            if (!found) {//if not found custormer id 
                System.out.println("\nCustomer ID not found.");//display error messege
            }
        } else {
            System.out.println("\nError updating the customer.");//error messege when did not delete process original file 
        }
    }//end updating process

//**********************************************************delete custormer details*******************************************************//     

     public static void deleteCustormer() {
        System.out.println("-----------Delete details---------------");//print messege
        System.out.print("\nEnter Custormer ID to Delete: ");//enter custormer id to have delete
        String custormerId = scanner.nextLine();

        File inputFile = new File(CUSTORMER_FILE);//define original file
        File tempFile = new File("temp_custormer.txt");//define tempery file

        boolean found = false;//check if custormer id was found

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));//open original and tempery files
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {//read line by line in original file
                String[] custormerDetails = currentLine.split(",");//split data
                if (!custormerDetails[0].equals(custormerId)) {//checking if the custormer id matches to the delete one
                    writer.write(currentLine);//if the id doesn't match, write to line tempery file
                    writer.newLine();
                } else {
                    found = true;//if id match
                    System.out.println("\nCustomer details deleted successfully!");//print messege successfully
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing to file: " + e.getMessage());//display error messegr during file reading and writing
        }
        
        if (inputFile.delete()) {//delete original file
            tempFile.renameTo(inputFile);//rename tempery file to original file name
            if (!found) {//if not found custormer id 
                System.out.println("\nCustomer ID not found.");//display error messege
            }
        } else {
            System.out.println("\nError deleting the customer.");//error messege when did not delete process original file 
        }
    }
    
//----------------------------------------------------------------2.order details--------------------------------------------------------//
     
    public static void orderDetails(){
        int type;
        do{ System.out.println("\n---------------------------------------");
            System.out.println("        Cafe Mocha Order Section");//print header messege
            System.out.println("---------------------------------------");
            System.out.println("1.Add New Order");//crud operations
            System.out.println("2.Delete Order Details");
            System.out.println("3.Exit");
            System.out.println("Enter your choice: ");
            type=scanner.nextInt();
            scanner.nextLine();
            switch(type){
                case 1:
                    addOrder();//add order function
                    break;
                    
                case 2:
                    deleteOrder();//delete order function
                    break;
                           
                case 3:
                    System.out.println("\nExiting Order Details Section");//exit order section
                    return;
                    
                default:
                    System.out.println("\nInvalid choice,please try again.");//error messege
            }
         
        }while(type !=3);//do while loop close
        scanner.close();//close the scanner
    }
 //**************************************************add order details********************************************************************//   
    
    public static void initializeOrderNumberCounter() {//starting counting order number automatically
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {//read file and open file
            String line;
            int maxOrderNumber = 0;//assign variable max order number found in file
            while ((line = reader.readLine()) != null) {//read file line by line
                String[] orderDetails = line.split(",");//split order details 
                int orderNumber = Integer.parseInt(orderDetails[0]);
                if (orderNumber > maxOrderNumber) {//check order number higher than max order number
                    maxOrderNumber = orderNumber;//update max order number
                }
            }
            orderNumberCounter = maxOrderNumber + 1;//count=count+1
        } catch (IOException e) {
            System.out.println("Error initializing order number counter: " + e.getMessage());//error messege when increment automatically order number
        }
    }
    
   public static void addOrder() {//add order
        System.out.println("------------Add New order---------------");//display messege
        System.out.print("\nEnter Custormer Id: ");//must to have already got id
        String custormerId = scanner.nextLine();

        if (!customerExists(custormerId)) {//check custormer id to already have custormer file
            System.out.println("\nCustormer ID does not exist. Please add the customer first.");//display error messege 
            return;
        }

        String orderNumber = String.valueOf(orderNumberCounter++); //auto incremented order number
        System.out.print("Enter Custormer Name: ");//custormer name
        String custormerName = scanner.nextLine();
        System.out.print("Enter Custormer Phonenumber: ");//custormer phonenumber
        String custormerPhonenumber = scanner.nextLine();
        System.out.print("Enter Item Id: ");//must to have already got item id
        String itemId = scanner.nextLine();
        
        if (!itemExists(itemId)) {//check item id to already have item list
        System.out.println("\nItem ID does not exist. Please add the item first.");//display error when id not exit
        return;}
        System.out.print("Enter Order Quantity: ");//order quantity
        String orderQuantity = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE, true))) {//open order file 
            writer.write(orderNumber + "," + custormerId + "," +custormerName+","+ custormerPhonenumber + "," + itemId + "," + orderQuantity);//store data
            writer.newLine();//for new order
            System.out.println("\nOrder added successfully!");//print successfully messege
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());//error messege when during write details
        }
    }
   
   
     
    public static boolean itemExists(String itemId) {//if an item with given item id exits in the menu file
    try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {//open menu file for reading
        String line;
        while ((line = reader.readLine()) != null) {//read line by line
            String[] itemDetails = line.split(",");//split details
            if (itemDetails[0].equals(itemId)) {//check item id and provid item id
                return true;//if matching,then return true
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading menu file: " + e.getMessage());
    }
    return false;//return false if item not found
    }

    public static boolean customerExists(String custormerId) {//if an custormer with given custormer id in the custormer file
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTORMER_FILE))) {//open custormer file
            String line;
            while ((line = reader.readLine()) != null) {//read line by line
                String[] custormerDetails = line.split(",");//split data
                if (custormerDetails[0].equals(custormerId)) {//check custormer id and provid custormer id
                    return true;//if matching id then return true
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
        return false;//return false if custormer id not found
    }

//**********************************************************delete order************************************************************************************//

    public static void deleteOrder() {
        System.out.println("-------------Delete Order-----------------");//header
        System.out.print("\nEnter Order Number to Delete: ");//enter order number want to delete
        String orderNumber = scanner.nextLine();

        File inputFile = new File(ORDER_FILE);//define original file
        File tempFile = new File("temp_orders.txt");//define tempery file

        boolean found = false;//if found order number
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));//open both files
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {//read line by line
                String[] orderDetails = line.split(",");
                if (!orderDetails[0].equals(orderNumber)) {//if not same to order number and given order number
                    writer.write(line);//write old details tempery file
                    writer.newLine();
                } else {
                    found = true;//if same both numbers
                    System.out.println("\nOrder deleted successfully!");//display successfull messege
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }

        if (inputFile.delete() && tempFile.renameTo(inputFile) && !found) {//delete original file,rename tempery file,not found order number
            System.out.println("\nOrder Number not found.");//print
        }
    }

//-----------------------------------------------------3. display order details --------------------------------------------------------------------//

    public static void displayOrderDetailsByOrderNumber() {//display order details by order number
    System.out.println("\n---------------------------------------");
    System.out.println("        Display Order Details ");//header
    System.out.println("---------------------------------------");
    System.out.print("Enter Order Number: ");//enter order number
    String orderNumber = scanner.nextLine();

    String orderDetails = findOrderByOrderNumber(orderNumber);//get order details

    if (orderDetails != null) {//order found
        String[] details = orderDetails.split(",");//split data
        String itemId = details[4]; //extract item ID from order details
        String itemDetails = findItemByItemId(itemId); //get item details
      
        System.out.println("\n********************************");
        System.out.println("         Order Details:");//print
        System.out.println("********************************");
        System.out.println("Order Number: " + details[0]);//print order details with assumes order format
        System.out.println("Customer ID: " + details[1]);
        System.out.println("Customer Name: " + details[2]);
        System.out.println("Customer Phone: " + details[3]);
        System.out.println("Order Item ID: " + details[4]);
        System.out.println("Order Quantity: " + details[5]);

        if (itemDetails != null) {//item found
            String[] itemInfo = itemDetails.split(",");//split data
            System.out.println("\n********************************");
            System.out.println("          Item Details");//print
            System.out.println("********************************");
            System.out.println("Item Name: " + itemInfo[1]);//print item details with assume menu item format
            System.out.println("Price: Rs" + itemInfo[3]);
            System.out.println("Category: " + itemInfo[4]);
        }else {
            System.out.println("Item not found for the given order.");//if not found item id
        }
    } else {
        System.out.println("Order not found.");//if not found order number
    }
}

// ************************************************find order number****************************************************************************//
public static String findOrderByOrderNumber(String orderNumber) {
    try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {//open file
        String line;
        while ((line = reader.readLine()) != null) {//read line by line
            String[] orderDetails = line.split(",");//split details
            if (orderDetails[0].equals(orderNumber)) {//check order number and given number were same
                return line; //return order details if order number matches
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading order file: " + e.getMessage());//print error messege
    }
    return null; //return null if order not found
}


// *************************************************find item id *************************************************************************************//

public static String findItemByItemId(String itemId) {
    try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {//open menu file
        String line;
        while ((line = reader.readLine()) != null) {//read file
            String[] itemDetails = line.split(",");//split data
            if (itemDetails[0].equals(itemId)) {//check item id and give item id
                return line; //return item details if item ID matches
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading menu file: " + e.getMessage());//print error messege
    }
    return null; //return null if item not found
}

//------------------------------------------------------------------4.calculate bill------------------------------------------------------------------------//    
    
    public static void calculateBill(){//start
        int type;
        do{ System.out.println("\n---------------------------------------");
            System.out.println("     Cafe Mocha Bill Calculations ");//header
            System.out.println("---------------------------------------");
            System.out.println("1.Add New Bill");//display crud operation messeges
            System.out.println("2.Display Bill");
            System.out.println("3.Delete Bill");
            System.out.println("4.Exit");
            System.out.println("Enter your choice: ");
            type=scanner.nextInt();
            scanner.nextLine();
            switch(type){
                case 1:
                    addBill();//crud operations add
                    break;
                    
                case 2:  
                    readBill();//read
                    break;
                    
                case 3:
                    deleteBill();//delete
                    break;
                           
                case 4:
                    System.out.println("\nExiting Billing Section");//exit
                    return;
                    
                default:
                    System.out.println("\nInvalid choice,please try again.");
            }
         
        }while(type !=4);//close do while
        scanner.close();//close in scanner
    
    }//end
    
//***************************************************************add bill**************************************************************************//
    
    public static void addBill(){//start
    System.out.println("\n----------- Calculate Bill ------------");//print
    System.out.print("\nEnter Order Number: ");//enter order number(order should have been already placed )
    String orderNumber = scanner.nextLine();

    String orderDetails = findOrderByOrderNumber(orderNumber);//search order details

    if (orderDetails != null) {
        String[] details = orderDetails.split(",");
        String itemId = details[4]; //extract item ID from order details
        String itemDetails = findItemByItemId(itemId); //get item details
        int orderQuantity = Integer.parseInt(details[5]); //extract quantity from order details

        if (itemDetails != null) {
            String[] itemInfo = itemDetails.split(",");
            double price = Double.parseDouble(itemInfo[3]); //extract price from item details
            double total = price * orderQuantity; //calculate the total 
            double taxRate = 0.10; //tax rate
            double taxAmount = total * taxRate; //add tax
            double totalWithTax = total + taxAmount; //calculate total with tax

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(BILL_FILE, true))) {//open file
                writer.write(orderNumber + "," + details[2] + "," + itemInfo[1] + "," + orderQuantity + "," + price + "," + total + "," + taxAmount + "," + totalWithTax);//store data
                writer.newLine();//for next bill
                System.out.println("\nBill added successfully!");//if calculate succesfull
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());//error messege during file writing
            }

            System.out.println("\n********************************");//details header
            System.out.println("           CAFE MOCHA");
            System.out.println("            RECEIPT");
            System.out.println("********************************");
            System.out.println("Customer Name: " + details[2]);//print custormer name which 3rd detail order details
            System.out.println("Order Number: " + details[0]);//print order number which 1st detail order details
            System.out.println("Item Name: " + itemInfo[1]);//print item name which 2nd detail item details
            System.out.println("Quantity: " + orderQuantity);//print item quantity which item details
            System.out.println("Price per item: Rs " + price);//print per price which item details
            System.out.println("Total before tax: Rs " + total);//print tax which calculate bill details
            System.out.println("Tax (10%): Rs " + taxAmount);//print tax amount
            System.out.println("Total Bill (with tax): Rs " + totalWithTax);//print final total 
            System.out.println("********************************");
            System.out.println("           THANK YOU!");
            System.out.println("********************************");
        } else {
            System.out.println("Item not found for the given order.");//if item id not found
        }
    } else {
        System.out.println("Order not found.");//if order number not found
    }
}//end
//*******************************************************************read bill*************************************************************************//    
  
public static void readBill() {//start
    System.out.println("\n------------- Bill List --------------");//header
    try (BufferedReader reader = new BufferedReader(new FileReader(BILL_FILE))) {//open bill file 
        String line;
        while ((line = reader.readLine()) != null) {//read line by line 
            String[] billDetails = line.split(",");//split data
            System.out.println("\n********************************");
            System.out.println("           CAFE MOCHA");
            System.out.println("            RECEIPT");
            System.out.println("********************************");
            System.out.println("Order Number: " + billDetails[0]);//print order number where 1st detail in billing data
            System.out.println("Customer Name: " + billDetails[1]);
            System.out.println("Item Name: " + billDetails[2]);
            System.out.println("Quantity: " + billDetails[3]);
            System.out.println("Price per item: Rs " + billDetails[4]);
            System.out.println("Total before tax: Rs " + billDetails[5]);
            System.out.println("Tax: Rs " + billDetails[6]);
            System.out.println("Total Bill (with tax): Rs " + billDetails[7]);
            System.out.println("********************************");
            System.out.println("           THANK YOU!");
            System.out.println("********************************");
            System.out.println("-----------------------------------------");//between diferent two bills
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());//error messege during read file
    }
}//end

// ****************************************************************delete bill**************************************************************************//

public static void deleteBill() {//start
    System.out.println("\n------------ Delete Bill -----------");//header
    System.out.print("\nEnter Order Number to Delete: ");//enter order number have to delete
    String orderNumber = scanner.nextLine();

    File inputFile = new File(BILL_FILE);//define original file
    File tempFile = new File("temp_bills.txt");//define tempery file

    boolean found = false;
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));//open both
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {//read line by line
            String[] billDetails = line.split(",");
            if (!billDetails[0].equals(orderNumber)) {//order number and 1st data in billind details if not same
                writer.write(line);//store old data in tempery file
                writer.newLine();
            } else {
                found = true;//if order number same
                System.out.println("\nBill deleted successfully!");//print messege
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading/writing file: " + e.getMessage());//error messege
    }

    if (inputFile.delete() && tempFile.renameTo(inputFile) && !found) {//replase file with update details
        System.out.println("Order Number not found.");
    }
} //end  
  
//--------------------------------------------------------------------5.menu item----------------------------------------------------------------------//

    public static void menuItem(){
        int type;
        do{ System.out.println("\n---------------------------------------");
            System.out.println("          Cafe Mocha Menu ");
            System.out.println("---------------------------------------");
            System.out.println("1.Add New Item");
            System.out.println("2.Read Items");
            System.out.println("3.Update Item Details");
            System.out.println("4.Delete Item Details");
            System.out.println("5.Exit");
            System.out.println("Enter your choice: ");
            type=scanner.nextInt();
            scanner.nextLine();
            switch(type){
                case 1:
                    addItem();
                    break;
                    
                case 2:  
                    readItem();
                    break;
                    
                case 3:
                    updateItem();
                    break;
                    
                case 4:
                    deleteItem();
                    break;
                           
                case 5:
                    System.out.println("\nExiting Menu Section");
                    return;
                    
                default:
                    System.out.println("\nInvalid choice,please try again.");
            }
         
        }while(type !=5);
        scanner.close();
    }
    
//*******************************************************************add items***********************************************************************************************//
    
    public static void initializeItemIdCounter() {//starting auto increment id
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            int maxId = 0;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split(",");
                int itemId = Integer.parseInt(itemDetails[0]);
                if (itemId > maxId) {
                    maxId = itemId;
                }
            }
            itemIdCounter = maxId + 1; // Start from the next available ID
        } catch (IOException e) {
            System.out.println("Error initializing item ID counter: " + e.getMessage());
        }
    }
    
    
   public static void addItem() {
        System.out.println("\n----------- Add New Menu Item ------------");
        String itemId = String.valueOf(itemIdCounter++); // Automatically increment item ID
        System.out.print("\nEnter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE, true))) {
            writer.write(itemId + "," + itemName + "," + description + "," + price + "," + category);
            writer.newLine();
            System.out.println("Menu item added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
   
//******************************************************************************read item*********************************************************************************//
   
    public static void readItem() {
        System.out.println("\n------------ Menu Item List -------------");
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split(",");
                System.out.println("\nItem ID: " + itemDetails[0]);
                System.out.println("Item Name: " + itemDetails[1]);
                System.out.println("Description: " + itemDetails[2]);
                System.out.println("Price: Rs" + itemDetails[3]);
                System.out.println("Category: " + itemDetails[4]);
                System.out.println("----------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
//*****************************************************************************update item****************************************************************************************************//
    
    public static void updateItem() {
        System.out.println("\n----------- Update Menu Item -----------");
        System.out.print("\nEnter Item ID to Update: ");
        String itemId = scanner.nextLine();

        File inputFile = new File(MENU_FILE);
        File tempFile = new File("temp_menu.txt");

        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split(",");
                if (itemDetails[0].equals(itemId)) {
                    // Item found, prompt for new details
                    System.out.print("Enter New Item Name: ");
                    String newItemName = scanner.nextLine();
                    System.out.print("Enter New Description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter New Price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Category: ");
                    String newCategory = scanner.nextLine();

                    writer.write(itemId + "," + newItemName + "," + newDescription + "," + newPrice + "," + newCategory);
                    writer.newLine();
                    found = true;
                    System.out.println("Menu item updated successfully!");
                } else {
                    // Write old item details
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

        // Replace original file with the updated file
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
            if (!found) {
                System.out.println("Item ID not found.");
            }
        } else {
            System.out.println("Error updating the item.");
        }
    }
    
    //**************************************************************************delete item****************************************************************************************************//
    
    public static void deleteItem() {
        System.out.println("\n----------- Delete Menu Item ------------");
        System.out.print("\nEnter Item ID to Delete: ");
        String itemId = scanner.nextLine();

        File inputFile = new File(MENU_FILE);
        File tempFile = new File("temp_menu.txt");

        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split(",");
                if (!itemDetails[0].equals(itemId)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    found = true;
                    System.out.println("Menu item deleted successfully!");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }

        // Replace original file with the updated file
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
            if (!found) {
                System.out.println("Item ID not found.");
            }
        } else {
            System.out.println("Error deleting the item.");
        }
    }

//----------------------------------------------------------------------6.help-----------------------------------------------------------------------------------------------------------------------------------------------------//
    
    public static void displayHelp(){
        System.out.println("\n---------------------------------------");
        System.out.println("           User Guide Lines");
        System.out.println("---------------------------------------");
        System.out.println("\n Login ");
        System.out.println("1.	Enter valid username.");
        System.out.println("2.	Enter valid password");
        System.out.println("3.	If both the username and password match the stored values, then display login successfully message and proceed to main menu.");
        System.out.println("4.	If not match the stored values, then try log in again.");
        System.out.println("\n Main Menu");
        System.out.println("1.	Type the number of the option you want to choose.");
        System.out.println("2.	If you select option 7, the system will log you out and exit the program.");
        System.out.println("\n Customer Details");
        System.out.println("1.	There are 5 options in here. Such as add customer, read customer, update customer, delete customer, exit.");
        System.out.println("2.	When you select option 1 (\"Add New Customer\"), the system will prompt you to enter customer name, address, phone number and email.");
        System.out.println("3.	After entering all the required information, the system will automatically assign a unique Customer ID and store the data.");
        System.out.println("4.	Selecting option 2 (\"Read Customer\") will display a list of all stored customer records.");
        System.out.println("5.	To update a customer’s details, choose option 3 (\"Update Customer Details\").");
        System.out.println("6.	You will be prompted to enter the custom id of the customer you wish to update.");
        System.out.println("7.	If the Customer ID is found, you will be asked to provide updated information for the customer's name, address, phone number, and email. After submitting the new details, the system will update the record and display successfully message.");
        System.out.println("8.	If id not found then display error message.");
        System.out.println("9.	To delete a customer, choose option 4 (\"Delete Customer Details\").");
        System.out.println("10.	Enter the customer id of the customer you want to remove from the system.");
        System.out.println("\n Order Details");
        System.out.println("1.	To add a new order, choose option 1 (\"Add New Order\").");
        System.out.println("2.	You will be prompted to enter the following details. (Enter an existing customer ID, Customer name, customer phone number, item id (the item must already exist in the menu list.), quantity)");
        System.out.println("3.	Once all the required information is entered, the system will automatically assign a unique order number and store the data.");
        System.out.println("4.	If the customer id does not exist in the system, you will receive the message.");
        System.out.println("5.	To delete an existing order, choose option 2 (\"Delete Order Details\").");
        System.out.println("\n Display Bill");
        System.out.println("1.	Upon selecting the option to display order details, the system will prompt you to enter an order number.");
        System.out.println("2.	If the order is found, the system will display details, if not find order, then display error message.");
        System.out.println("\n Calculate Bill");
        System.out.println("1.	Once the bill calculation system is selected, the system will present more options.");
        System.out.println("2.	Upon selecting add bill (Option 1), the system will prompt for various inputs to create a new bill based on an order.");
        System.out.println("3.	Once all details are entered and verified, the system generates the total bill amount.");
        System.out.println("4.	Upon selecting display bill (Option 2), the system will prompt you to enter the order number for which you want to view the bill.");
        System.out.println("5.	Upon selecting delete bill (Option 3), the system will prompt you to enter the order number of the bill you wish to delete.");
        System.out.println("\n Item Details");
        System.out.println("	The process here also corresponds to the customer details.");
        
        
    }}
    
    
    
    