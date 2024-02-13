package inventoryManagement;
import java.io.*;
import java.util.*;

public class User {
    private String[] userDetailsList;

    public User() {
        userDetailsList = fileReader("C:\\Users\\MOHAMED\\Desktop\\users.txt");
    }
   
    public void authenticateUser() {
    	boolean authenticated = false;
    	do {	
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username or userid: ");
        String input = scanner.nextLine().trim();

        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();

        

        for (String userDetails : userDetailsList) {
            String[] userDetailsArr = userDetails.split(",");
            String userId = userDetailsArr[0].trim();
            String username = userDetailsArr[1].trim();
            String storedPassword = userDetailsArr[2].trim();
            String role = userDetailsArr[3].trim();

            // Compare input with stored credentials
            if ((input.equals(userId) || input.equals(username)) && password.equals(storedPassword)) {
                System.out.println("Authentication successful! Welcome, " + username);
                System.out.println("Role: " + role);
                authenticated = true;
                authorization(role);
                break; 
            }
        }

        if (!authenticated) {
            System.out.println("Invalid credentials. Access denied.");
        }
      }while(!authenticated);
        
    }

    private void authorization(String role) {
        switch (role.toLowerCase()) {
            case "inventorymanager":
                inventoryManager inventoryManager = new inventoryManager();
                inventoryManager.displayMenu();
                break;
           case "biemployee":
                BiEmployee biEmployee = new BiEmployee();
               biEmployee.displayMenu(); 
                break;
            case "admin":
               Admin admin = new Admin();
               admin.displayMenu();
               break;
            default:
              System.out.println("Role-based authorization not available.");
                break;
        }
    }

    private String[] fileReader(String filename) {
        List<String> content = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle file not found exception
        }

        return content.toArray(new String[0]);
    }
}

