package inventoryManagement;
import java.util.*;
import java.io.*;
import java.time.*;
import java.text.*;
public class Admin extends User {
	public void displayMenu()
	{ Scanner scanner = new Scanner(System.in);
	int choice;
	do {
		System.out.println("\nAdmin Menu:");
		System.out.println("1. Inventory Management");
		System.out.println("2. Sales Reporting");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
		try {
			choice = scanner.nextInt();
		} catch (NoSuchElementException e) {
			System.out.println("Invalid input. Please enter a number.");
			scanner.nextLine();
			choice = -1;
		}
		switch (choice) {
		case 1:
			inventoryManager adminAsInventoryManager=new inventoryManager();
			adminAsInventoryManager.displayMenu();
			break;
		case 2:
			BiEmployee adminAsBiEmployee= new BiEmployee();
			adminAsBiEmployee.displayMenu();
			break;
		case 0:
			System.out.println("Exiting system...");
			break;
		default: System.out.println("Invalid choice. Please write a valid option.");
		}
	}while(choice!=0);
	}
}