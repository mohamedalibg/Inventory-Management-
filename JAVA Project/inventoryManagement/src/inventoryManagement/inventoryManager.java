package inventoryManagement;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class inventoryManager extends User {
    private String inventoryFilePath = "C:\\Users\\MOHAMED\\Desktop\\Inventory.txt";  
    // Load inventory data from the text file into a list of products
    public List<Product> loadInventoryFromFile() {
        List<Product> inventory = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(inventoryFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] productData = line.split(","); 
                int productId = Integer.parseInt(productData[0]);
                String productName = productData[1];
                int quantityInStock = Integer.parseInt(productData[2]);
                double price = Double.parseDouble(productData[3]);
                String category = productData[4];

                Product product = new Product(productId, productName, quantityInStock, price, category);
                inventory.add(product);
            }
        } catch (FileNotFoundException e) {
        	
        }
            
        

        return inventory;
    }

    // Save inventory data to the text file
    public void saveInventoryToFile(List<Product> inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(inventoryFilePath))) {
            for (Product product : inventory) {
                String productInfo = product.getProductid() + "," + product.getProductname() + ","
                        + product.getQuantityinstock() + "," + product.getPrice() + "," + product.getCategory();
                writer.println(productInfo);
            }
        } catch (IOException e) {
            
        }
    }
    
    public void displayMenu()  {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nInventory Management Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Modify Product");
            System.out.println("4. View Inventory");
            System.out.println("5. Add a Sale");
            System.out.println("6. View Sales");
            System.out.println("7. Add a Purchase");
            System.out.println("8. View Purchases");
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
            	addProduct();
            	
            	break;
            case 2:
            	 Scanner scanner1 = new Scanner(System.in);
            	 int idToDelete;
            	 System.out.println("Enter ID of the product to delete");
            	 idToDelete = scanner1.nextInt();
            	 deleteProduct(idToDelete);
            	break;
            case 3:
           	 Scanner scanner2 = new Scanner(System.in);
           	int idToModify;
           	 System.out.println("Enter ID of the Product to Modify");
           	 idToModify = scanner2.nextInt();
           	modifyProduct(idToModify); 
            	break;
            case 4:
            	viewInventory();
            	break;
            case 5:
            	double revenue = 0;
            	// Ask the user to input the sale details
                System.out.println("Enter Sale ID:");
                int saleId = scanner.nextInt();

                System.out.println("Enter Product ID:");
                int productId1 = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.println("Enter Product Name:");
                String productName = scanner.nextLine();

                System.out.println("Enter Quantity Sold:");
                int quantitySold = scanner.nextInt();
                scanner.nextLine(); // Consume newline	                 
                System.out.println("Enter a date (format: dd/MM/yyyy):");
                Date date = null;
                String userInput = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date = dateFormat.parse(userInput);
                    System.out.println("Entered date: " + dateFormat.format(date));
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter date in dd/MM/yyyy format.");
                }
            	addSale(saleId,productId1,productName,quantitySold,revenue, date);
            	break;
            case 6:
            	viewSales();
            	break;
            case 7:
            	double cost = 0;
            	// Ask the user to input the sale details
                System.out.println("Enter Purchase ID:");
                int purchaseId = scanner.nextInt();

                System.out.println("Enter Product ID:");
                int productId11 = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter Product Name:");
                String productName1 = scanner.nextLine();

                System.out.println("Enter Quantity Purchased:");
                int quantityPurchased = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter unit Cost:");
                double unitCost = scanner.nextDouble();	
                scanner.nextLine();
                System.out.println("Enter a date (format: dd/MM/yyyy):");
                Date date1 = null;
                String userInput1 = scanner.nextLine();
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date1 = dateFormat1.parse(userInput1);
                    System.out.println("Entered date: " + dateFormat1.format(date1));
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter date in dd/MM/yyyy format.");
                }
                System.out.println("Enter Supplier Name:");
                String supplier = scanner.nextLine();
            	addPurchase(purchaseId,productId11,productName1,quantityPurchased,unitCost, cost, date1, supplier);
            	break;
            case 8:
            	viewPurchases();
            	break;
            	
            case 0:
            	System.out.println("Exiting System... !");   
            	break;
           default: 
        	   System.out.println("Invalid input!");
            }
        } while (choice != 0);

        
    
}
 
    
    // Method to add a product from the inventory and update the text file
    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Product ID:");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Product Name:");
        String productName = scanner.nextLine();
        System.out.println("Enter Quantity in Stock:");
        int quantityInStock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Category:");
        String category = scanner.nextLine();
        
        Product newProduct = new Product(productId, productName, quantityInStock, price, category);
        List<Product> inventory = loadInventoryFromFile();
        inventory.add(newProduct);
        saveInventoryToFile(inventory);
        System.out.println("Product added successfully!");
    }
    
    
    
    // Method to delete a product from the inventory and update the text file
    public void deleteProduct(int productId) {
        List<Product> inventory = loadInventoryFromFile();
        Iterator<Product> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductid() == productId) {
                iterator.remove(); // Remove the current element
            }
        }

        saveInventoryToFile(inventory);
    }
 
    
    
   
    // Method to modify a product from the inventory and update the text file
    public void modifyProduct(int productId) {
        Scanner scanner = new Scanner(System.in);
        List<Product> inventory = loadInventoryFromFile();
        boolean continueModifying = true;

        while (continueModifying) {
            Product foundProduct = null;

            for (Product product : inventory) {
                if (product.getProductid() == productId) {
                    foundProduct = product;
                    break;
                }
            }

            if (foundProduct != null) {
                int choice;
                boolean modified = false;

                while (!modified) {
                    System.out.println("Modify Product Menu:");                   
    	            System.out.println("1. Set a new price");
    	            System.out.println("2. Increase price");
    	            System.out.println("3. Decrease price");
    	            System.out.println("4. Increase price by percentage");
    	            System.out.println("5. Decrease price by percentage");
    	            System.out.println("6. Change product name");
    	            System.out.println("7. Change quantity in stock");
    	            System.out.println("8. Add quantity in stock");
    	            System.out.println("9. Remove quantity in stock");
    	            System.out.println("10. Change category");
    	            System.out.println("0. Exit");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        
                    case 1:
	                    // Set a new price
	                    System.out.println("Enter the new price:");
	                    double newPrice = scanner.nextDouble();
	                    scanner.nextLine(); // Consume newline character
	                    foundProduct.setPrice(newPrice);
	                    System.out.println("Price has been set.");
	                    modified = true;
	                    break;
	                case 2:
	                    // Increase price
	                    System.out.println("Enter the value to add:");
	                    double addedPrice = scanner.nextDouble();
	                    scanner.nextLine();
	                    foundProduct.increasePrice(addedPrice);
	                    System.out.println("Price has been increased.");
	                    modified = true;
	                    break;
	                case 3:
	                	// Decrease price
	                    System.out.println("Enter the value to decrease:");
	                    double decreasedPrice = scanner.nextDouble();
	                    scanner.nextLine();
	                    foundProduct.decreasePrice(decreasedPrice);
	                    System.out.println("Price has been decreased.");
	                    modified = true;
	                    break;
	                case 4:
	                    // Increase price by percentage
	                    System.out.println("Enter the percentage to increase:");
	                    int addedPercentage = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline character
	                    foundProduct.increasePriceByPercentage(addedPercentage);
	                    System.out.println("Price has been increased.");
	                    modified = true;
	                    break;
	                case 5:
	                	// Decrease price by percentage
	                    System.out.println("Enter the percentage to decrease:");
	                    int decresedPercentage = scanner.nextInt();
	                    scanner.nextLine(); 
	                    foundProduct.decreasePriceByPercentage(decresedPercentage);
	                    System.out.println("Price has been decreased.");
	                    modified = true;
	                    break;
	                case 6:
	                	// Change product name
	                    System.out.println("Enter the new product name:");
	                    String newName = scanner.nextLine();
	                    foundProduct.setProductname(newName);
	                    System.out.println("Name has been changed.");
	                    modified = true;
	                    break;
	                case 7:
	                    // Change quantity in stock
	                    System.out.println("Enter the new quantity in stock:");
	                    int newQuantity = scanner.nextInt();
	                    scanner.nextLine();
	                    foundProduct.setQuantityinstock(newQuantity);
	                    System.out.println("Quantity has been changed.");
	                    modified = true;
	                    break;
	                case 8:
	                    // Add quantity in stock
	                    System.out.println("Enter the quantity to add in stock:");
	                    int addedQuantity = scanner.nextInt();
	                    scanner.nextLine(); 
	                    foundProduct.addQuantity(addedQuantity);
	                    System.out.println("Quantity has been increased.");
	                    modified = true;
	                    break;
	                case 9:
	                    // Remove quantity in stock
	                    System.out.println("Enter the quantity to remove in stock:");
	                    int removedQuantity = scanner.nextInt();
	                    scanner.nextLine();
	                    foundProduct.removeQuantity(removedQuantity);
	                    System.out.println("Quantity has been decreased.");
	                    modified = true;
	                    break;
	                case 10:
	                    // Change category
	                    System.out.println("Enter the new category :");
	                    String newCategory = scanner.nextLine();
	                    scanner.nextLine(); 
	                    foundProduct.setCategory(newCategory);
	                    System.out.println("Category has been changed.");
	                    modified = true;
	                    break; 
                        case 0:
                            System.out.println("Exiting Modify Product Menu.");
                            continueModifying = false;
                            modified = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }
                }
            } else {
                System.out.println("Product with ID " + productId + " not found.");
                continueModifying = false;
            }

            System.out.println("Enter another Product ID to modify or enter 0 to exit:");
            
            scanner.nextLine();
            if (productId == 0) {
                continueModifying = false;
            }
        }
        

        

        saveInventoryToFile(inventory);
        
    
        displayMenu();
    }

    	    
    // Method to view inventory	    
    public void viewInventory() {
        List<Product> inventory = loadInventoryFromFile();

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory Details:");
            System.out.println("--------------------------------------------------------------");
            System.out.printf("%-10s | %-20s | %-10s | %-10s | %-15s%n",
                    "Product ID", "Product Name", "Quantity", "Price", "Category");
            System.out.println("--------------------------------------------------------------");

            for (Product product : inventory) {
                System.out.printf("%-10d | %-20s | %-10d | TND%-9.2f | %-15s%n",
                        product.getProductid(), product.getProductname(),
                        product.getQuantityinstock(), product.getPrice(), product.getCategory());
            }
        }
    }
  	    
    public void addSale(int saleId, int productId, String productName, int quantitySold,double revenue, Date date) {
        Sales newSale = new Sales(saleId, productId, productName, quantitySold,revenue, date);

        try {
            // Open the sales.txt file in append mode
            FileWriter fileWriter = new FileWriter("C:\\Users\\MOHAMED\\Desktop\\sales.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Writing the sale details to the file
            printWriter.println(newSale.getSaleId() + "," + newSale.getProductId() + ","
                    + newSale.getProductName() + "," + newSale.getQuantitySold() + ","
                    + newSale.getDate() + "," + newSale.getRevenue()+ "TND") ;

            // Closing the file
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to sales.txt");
        }
        updateInventoryAfterSale(productId, quantitySold);
    }  
    	    
    private void updateInventoryAfterSale(int productId, int quantitySold) {
        List<Product> inventory = loadInventoryFromFile();

        for (Product product : inventory) {
            if (product.getProductid() == productId) {
                int currentQuantity = product.getQuantityinstock();
                if (currentQuantity >= quantitySold) {
                    product.removeQuantity(quantitySold);
                    break;
                } else {
                    
                    System.out.println("Insufficient quantity in stock for sale!");
                   
                }
            }
        }
        saveInventoryToFile(inventory);
    }
    public void viewSales() {
    	BiEmployee bi=new BiEmployee();
        List<Sales> sales = bi.loadSalesFromFile();

        if (sales.isEmpty()) {
            System.out.println("No Sales.");
        } else {
            System.out.println("Sales Details:");
            System.out.println("+-------------+-------------+---------+--------------+-------------------------------+----------+");
            System.out.println("| Sale ID     | Product ID  | Product | Quantity Sold| Date                          | Revenue  |");
            System.out.println("+-------------+-------------+---------+--------------+-------------------------------+----------+");                               
            for (Sales product : sales) {
            	System.out.printf("| %-11s| %-12s| %-9s| %-13s| %-30s| %-9s|%n",
                        product.getSaleId(), product.getProductId(),
                        product.getProductName(), product.getQuantitySold(), product.getDate(),product.getRevenue());
            }
            System.out.println("+-------------+-------------+---------+--------------+-------------------------------+----------+");
        }
    }
    private void updateInventoryAfterPurchase(int productId, int quantityPurchased) {
        List<Product> inventory = loadInventoryFromFile();

        for (Product product : inventory) {
            if (product.getProductid() == productId) {                             
                    product.addQuantity(quantityPurchased);                                     
                   
                }
            }
        
        saveInventoryToFile(inventory);
    }
    public void addPurchase(int purchaseId, int productId, String productName, int quantityPurchased, double unitCost,double cost, Date date, String supplier) {
        Purchases newPurchases = new Purchases(purchaseId, productId, productName, quantityPurchased,unitCost, cost,date,supplier);

        try {
            // Open the sales.txt file in append mode
            FileWriter fileWriter = new FileWriter("C:\\Users\\MOHAMED\\Desktop\\purchases.txt",true );
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Writing the sale details to the file
            printWriter.println(newPurchases.getPurchaseId() + "," + newPurchases.getProductId() + ","
                    + newPurchases.getProductName() + "," + newPurchases.getQuantityPurchased() + ","
                    +newPurchases.getUnitCost() + "," +newPurchases.getCost() + "," +newPurchases.getDate() + "," + newPurchases.getSupplier()) ;

            // Closing the file
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to purchases.txt");
        }
       updateInventoryAfterPurchase(productId, quantityPurchased);
    } 
    public void viewPurchases() {
        BiEmployee bi = new BiEmployee();
        List<Purchases> purchases = bi.loadPurchasesFromFile();

        if (purchases.isEmpty()) {
            System.out.println("No Purchases.");
        } else {
        	System.out.println("+---------------+--------------+---------+----------------+-------------------------------+----------+----------+-----------------------+");
        	System.out.println("| Purchase ID   | Product ID   | Product | Quantity       | Date                          | Unit Cost| Cost     | Supplier              |");
        	System.out.println("+---------------+--------------+---------+----------------+-------------------------------+----------+----------+-----------------------+");
        	for (Purchases purchase : purchases) {
        	    System.out.printf("| %-13s | %-13s| %-8s| %-15s| %-29s | %-9s| %-9s| %-21s |%n",
        	            purchase.getPurchaseId(), purchase.getProductId(),
        	            purchase.getProductName(), purchase.getQuantityPurchased(), 
        	            purchase.getDate(), purchase.getUnitCost(),purchase.getCost(), purchase.getSupplier());
        	}
        	System.out.println("+---------------+--------------+---------+----------------+-------------------------------+----------+----------+-----------------------+");




        }
    }
}


   
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	    
    	  

