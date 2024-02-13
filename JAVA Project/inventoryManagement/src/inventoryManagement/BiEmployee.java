package inventoryManagement;
import java.io.*;
import java.util.*;
import java.text.*;
public class BiEmployee extends User{
	private String salesFilePath = "C:\\Users\\MOHAMED\\Desktop\\sales.txt";
	private String purchasesFilePath = "C:\\Users\\MOHAMED\\Desktop\\purchases.txt";
	// Load transaction data from the text file into a list of sales
	public List<Sales> loadSalesFromFile() {
	    List<Sales> sales = new ArrayList<>();
	    try (Scanner scanner = new Scanner(new File(salesFilePath))) {
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] productData = line.split(",");
	            int saleId = Integer.parseInt(productData[0]);
	            int productId = Integer.parseInt(productData[1]);
	            String productName = productData[2];
	            int quantitySold = Integer.parseInt(productData[3]);
	            String revenueString = productData[5].replaceAll("[^\\d.]", "");
	            double revenue = Double.parseDouble(revenueString);

	            Date date = null;
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
	                date = dateFormat.parse(productData[4]);
	            } catch (ParseException e) {
	                System.err.println("Error parsing date: " + productData[4]);
	                e.printStackTrace();
	                // Handle the parsing exception
	            }

	            Sales sale = new Sales(saleId, productId, productName, quantitySold, revenue, date);
	            sales.add(sale);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return sales;
	}
	public List<Purchases> loadPurchasesFromFile() {
	    List<Purchases> purchases = new ArrayList<>();
	    try (Scanner scanner = new Scanner(new File(purchasesFilePath))) {
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] productData = line.split(",");
	            int purchaseId = Integer.parseInt(productData[0]);
	            int productId = Integer.parseInt(productData[1]);
	            String productName = productData[2];
	            int quantityPurchased = Integer.parseInt(productData[3]);
	            double unitCost = Double.parseDouble(productData[4]);
	            String costString = productData[5].replaceAll("[^\\d.]", "");
	            double cost = Double.parseDouble(costString);

	            Date date = null;
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
	                date = dateFormat.parse(productData[6]);
	            } catch (ParseException e) {
	                System.err.println("Error parsing date: " + productData[6]);
	                e.printStackTrace();
	                // Handle the parsing exception
	            }

	            String supplier = productData[7]; // Assuming the supplier field is at index 7 in the array

	            Purchases purchase = new Purchases(purchaseId, productId, productName, quantityPurchased, unitCost, cost, date, supplier);
	            purchases.add(purchase);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return purchases;
	}


	
	public void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\nSales Data Reports Menu:");
			System.out.println("1. View Total Revenue");			
			System.out.println("2. View Popular Products");
			System.out.println("3. View Total Product's Quantity Sold");
			System.out.println("4. Generate a complete Monthly Sales Report");
			System.out.println("5. Generate a complete Yearly Sales Report");
			System.out.println("6. View Total purchases");
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
				int choice1;
				do {
					System.out.println("\nTotal Revenue Menu:");
					System.out.println("1. Daily");
					System.out.println("2. Monthly");
					System.out.println("3. Yearly");
					System.out.println("4. Return");
					System.out.println("0. Exit");
					System.out.print("Enter your choice: ");
					try {
						choice1 = scanner.nextInt();
					} catch (NoSuchElementException e) {
						System.out.println("Invalid input. Please enter a number.");
						scanner.nextLine();
						choice1 = -1;
					}
					switch (choice1) {
					case 1:calculateDailyTotalRevenue();
					// Logic for daily total revenue
					break;
					case 2:
						Scanner scanner1 = new Scanner(System.in);
					    System.out.print("Enter the month (MM/YYYY): ");
					    String monthString = scanner1.next();						
						calculateMonthlyTotalRevenue(monthString);
						System.out.println("Total revenue for " + monthString + ": " + calculateMonthlyTotalRevenue(monthString) + " TND");
					// Logic for monthly total revenue
					break;
					case 3: 
						Scanner scanner11 = new Scanner(System.in);
						System.out.print("Enter the year (YYYY): ");
						int year = scanner11.nextInt();
						calculateYearlyTotalRevenue(year);
						System.out.println("Total revenue for " + year + ": is  " + calculateYearlyTotalRevenue(year) + " TND");
					// Logic for yearly total revenue
					break;
					case 4: choice =-1;
					choice1 =0;
					break; // Return to the previous menu
					case 0:
						System.out.println("Exiting the Program...");
						choice=0;
						
						break;// Exit the program
					default:
						System.out.println("Invalid choice. Please enter a valid option.");
					}
				} while (choice1 != 0);
				break;
			case 2:
				int choice2;
				do {
					System.out.println("\nPopular Products Menu:");
					System.out.println("1. Top 1");
					System.out.println("2. Top 3");
					System.out.println("3. Return");
					System.out.println("0. Exit");
					System.out.print("Enter your choice: ");
					try {
						choice2 = scanner.nextInt();
					} catch (NoSuchElementException e) {
						System.out.println("Invalid input. Please enter a number.");
						scanner.nextLine();
						choice2 = -1;
					}
					switch (choice2) {
					case 1:findTopProduct();
					// Logic for top 1 popular product
					break;
					case 2:findTop3Products();
					// Logic for top 3 popular products
					break;
					case 3: choice=-1;
					choice2=0;
					break; // Return to the previous menu
					case 0:
						System.out.println("Exiting the Program...");
						choice=0; 
						break;// Exit the program
					default:
						System.out.println("Invalid choice. Please enter a valid option.");
					}
				} while (choice2 != 0);
				break;
			case 3:int choice3; { do {
				System.out.println("\nTotal Product's Quantity Sold Menu:");
				System.out.println("1. Daily");
				System.out.println("2. Monthly");
				System.out.println("3. Yearly");
				System.out.println("4. Return");
				System.out.println("0. Exit");
				System.out.print("Enter your choice: ");
				try {
					choice3 = scanner.nextInt();
				} catch (NoSuchElementException e) {
					System.out.println("Invalid input. Please enter a number.");
					scanner.nextLine();
					choice3 =-1;
				}
				switch (choice3) {
				case 1:
					viewQuantitySoldByDay();
					break;
				case 2:
					viewQuantitySoldByMonth();
					break;
				case 3:
					viewQuantitySoldByYear();
					break;
				case 4: choice=-1;
				choice3=0;
				break;
				case 0: 
					System.out.println("Exiting the Program...");
					choice=0;
				break;
				}
			} while (choice3 != 0);
			
			}
			// Logic for viewing total product's quantity sold
			break;
			case 4:
				Scanner scanner1 = new Scanner(System.in);
			    System.out.println("Enter the month");
			    int month =scanner1.nextInt();
			    System.out.println("Enter the year");
			    int year =scanner1.nextInt();
				generateMonthlyReportToFile(month, year);				
			break;
			case 5:
				Scanner scanner11 = new Scanner(System.in);
			    System.out.println("Enter the year");
			    int year1 =scanner11.nextInt();			   
				generateYearlyReportToFile(year1);
				
			break;
			case 6:
				int choice11;
				do {
					System.out.println("\nTotal Purchase Menu:");
					System.out.println("1. Daily");
					System.out.println("2. Monthly");
					System.out.println("3. Yearly");
					System.out.println("4. Return");
					System.out.println("0. Exit");
					System.out.print("Enter your choice: ");
					try {
						choice11 = scanner.nextInt();
					} catch (NoSuchElementException e) {
						System.out.println("Invalid input. Please enter a number.");
						scanner.nextLine();
						choice11 = -1;
					}
					switch (choice11) {
					case 1:
						calculateDailyTotalPurchases();
					// Logic for daily total revenue
					break;
					case 2:
						Scanner scanner1112 = new Scanner(System.in);
					    System.out.print("Enter the month (MM/YYYY): ");
					    String monthString = scanner1112.next();						
						calculateMonthlyTotalRevenue(monthString);
						System.out.println("Total revenue for " + monthString + ": " + calculateMonthlyTotalPurchases(monthString) + " TND");
					// Logic for monthly total revenue
					break;
					case 3: 
						Scanner scanner1111 = new Scanner(System.in);
						System.out.print("Enter the year (YYYY): ");
						int year11 = scanner1111.nextInt();
						calculateYearlyTotalRevenue(year11);
						System.out.println("Total revenue for " + year11 + ": is  " + calculateYearlyTotalPurchases(year11) + " TND");
					// Logic for yearly total revenue
					break;
					case 4: choice =-1;
					choice11 =0;
					break; // Return to the previous menu
					case 0:
						System.out.println("Exiting the Program...");
						choice=0;
						
						break;// Exit the program
					default:
						System.out.println("Invalid choice. Please enter a valid option.");
					}
				} while (choice11 != 0);
				break;
			case 0:
				choice=0; // Exit the program
				System.out.println("Exiting the Program...");
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}
		} while (choice != 0);
	}
	private void calculateDailyTotalRevenue() {		
		    Scanner scanner = new Scanner(System.in);
		    System.out.print("Enter the date (dd/MM/YYYY): ");
		    String dateString = scanner.next();
		    try {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        Date date = dateFormat.parse(dateString);
		        double totalRevenue = 0;

		        // Retrieve the sales records
		        List<Sales> sales = loadSalesFromFile();

		        // Iterate through sales and accumulate revenue for the specified date
		        for (Sales sale : sales) {
		            if (isSameDay(sale.getDate(), date)) {
		                totalRevenue += sale.getRevenue();
		            }
		        }

		        System.out.println("Total revenue for " + dateString + ": " + totalRevenue + " TND");
		    } catch (ParseException e) {
		        System.out.println("Invalid date format. Please enter the date in the format dd/MM/YYYY.");
		    }
		}

	// Helper method to check if two dates are on the same day
	private boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
				cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
	}

	private double calculateMonthlyTotalRevenue(String monthString) {  	 	    

	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
	        Date month = null;
			try {
				month = dateFormat.parse(monthString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        double totalRevenue = 0;
	        
	        // Retrieve the sales records
	        List<Sales> sales = loadSalesFromFile();

	        // Iterate through sales and accumulate revenue for the specified month
	        for (Sales sale : sales) {
	            if (isSameMonth(sale.getDate(), month)) {
	                totalRevenue += sale.getRevenue();
	            }
	        }	        
	    return totalRevenue;
	}


	

	// Helper method to check if two dates are in the same month
	private boolean isSameMonth(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	}
	private double calculateYearlyTotalRevenue(int year) {
		
		double totalRevenue = 0;
		// Iterate through sales and accumulate revenue for the specified year
		for (Sales sale : loadSalesFromFile()) {
			if (isSameYear(sale.getDate(), year)) {
				totalRevenue += sale.getRevenue();
			}
		}
		
		return totalRevenue;
		
	}
	// Helper method to check if a date is in the same year
	private boolean isSameYear(Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) == year;
	}
	private void findTopProduct() {
		// Create a map to store the total quantity sold for each product
		Map<Integer, Integer> quantitySoldMap = new HashMap<>();
		// Iterate through sales and accumulate quantity sold for each product
		for (Sales sale : loadSalesFromFile()) {
			int productId = sale.getProductId();
			int quantitySold = sale.getQuantitySold();
			// Update the total quantity sold for the product
			quantitySoldMap.put(productId, quantitySoldMap.getOrDefault(productId, 0) +
					quantitySold);
		}
		// Find the product with the highest quantity sold
		int topProductId = -1;
		int maxQuantitySold = -1;
		for (Map.Entry<Integer, Integer> entry : quantitySoldMap.entrySet()) {
			if (entry.getValue() > maxQuantitySold) {
				topProductId = entry.getKey();
				maxQuantitySold = entry.getValue();
			}
		}
		// Display the top product information
		if (topProductId != -1) {
			System.out.println("Top Product (Based on Highest Quantity Sold):");
			System.out.println("Product ID: " + topProductId);
			System.out.println("Quantity Sold: " + maxQuantitySold);
		} else {
			System.out.println("No sales data available.");
		}
	}
	private void findTop3Products() {
		// Create a map to store the total quantity sold for each product
		Map<Integer, Integer> quantitySoldMap = new HashMap<>();
		// Iterate through sales and accumulate quantity sold for each product
		for (Sales sale : loadSalesFromFile()) {
			int productId = sale.getProductId();
			int quantitySold = sale.getQuantitySold();
			// Update the total quantity sold for the product
			quantitySoldMap.put(productId, quantitySoldMap.getOrDefault(productId, 0) + quantitySold);
		}
		// Create a list of map entries and sort by quantity sold in descending order
		List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(quantitySoldMap.entrySet());
		sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		// Display the top 3 popular products
		System.out.println("Top 3 Popular Products (Based on Highest Quantity Sold):");
		int count = 0;
		for (Map.Entry<Integer, Integer> entry : sortedEntries) {
			if (count < 3) {
				System.out.println("Product ID: " + entry.getKey() + ", Quantity Sold: " + entry.getValue());
				count++;
			} else {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No sales data available.");
		}
	}
	private void viewQuantitySoldByDay() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the date (DD/MM/YYYY): ");
	    String dateString = scanner.next();
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Date date = dateFormat.parse(dateString);
	        
	        // Create a map to store the total quantity sold for each product in the specified month
	        Map<Integer, Integer> quantitySoldMap = new HashMap<>();
	        
	        // Accumulate quantity sold for each product on the specified date
	        for (Sales sale : loadSalesFromFile()) {
	            if (isSameDay(sale.getDate(), date)) {
	                int productId = sale.getProductId();
	                int quantitySold = sale.getQuantitySold();
	                // Update the total quantity sold for the product
	                quantitySoldMap.put(productId, quantitySoldMap.getOrDefault(productId, 0) + quantitySold);
	            }
	        }
	        
	        // Display the quantity sold for each product in the specified month
	        System.out.println("\nQuantity Sold on " + dateString);
	        boolean foundSales = false;
	        for (Map.Entry<Integer, Integer> entry : quantitySoldMap.entrySet()) {
	            int productId = entry.getKey();
	            int totalQuantitySold = entry.getValue();
	            System.out.println("Product ID: " + productId + ", Total Quantity Sold: " + totalQuantitySold);
	            foundSales = true;
	        }
	        
	        if (!foundSales) {
	            System.out.println("No sales data available for the specified date.");
	        }
	    } catch (ParseException e) {
	        System.out.println("Invalid date format. Please enter the date in the format DD/MM/YYYY.");
	    }
	}
	private void viewQuantitySoldByMonth() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the date (MM/YYYY): ");
	    String dateString = scanner.next();
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
	        Date date = dateFormat.parse(dateString);
	        
	        // Create a map to store the total quantity sold for each product in the specified month
	        Map<Integer, Integer> quantitySoldMap = new HashMap<>();
	        
	        // Accumulate quantity sold for each product on the specified date
	        for (Sales sale : loadSalesFromFile()) {
	            if (isSameMonth(sale.getDate(), date)) {
	                int productId = sale.getProductId();
	                int quantitySold = sale.getQuantitySold();
	                // Update the total quantity sold for the product
	                quantitySoldMap.put(productId, quantitySoldMap.getOrDefault(productId, 0) + quantitySold);
	            }
	        }
	        
	        // Display the quantity sold for each product in the specified month
	        System.out.println("\nQuantity Sold on " + dateString );
	        boolean foundSales = false;
	        for (Map.Entry<Integer, Integer> entry : quantitySoldMap.entrySet()) {
	            int productId = entry.getKey();
	            int totalQuantitySold = entry.getValue();
	            System.out.println("Product ID: " + productId + ", Total Quantity Sold: " + totalQuantitySold);
	            foundSales = true;
	        }
	        
	        if (!foundSales) {
	            System.out.println("No sales data available for the specified date.");
	        }
	    } catch (ParseException e) {
	        System.out.println("Invalid date format. Please enter the date in the format MM/YYYY.");
	    }
	}

	private void viewQuantitySoldByYear() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the date (YYYY): ");
		String dateString = scanner.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		int date = Integer.parseInt(dateString);
		// Iterate through sales and display quantity sold for each product on the specified date
		System.out.println("\nQuantity Sold on " + dateString );
		boolean foundSales = false;
		for (Sales sale : loadSalesFromFile()) {
			if (isSameYear(sale.getDate(), date)) {
				System.out.println("Product ID: " + sale.getProductId() + ", Quantity Sold: " +
						sale.getQuantitySold());
				foundSales = true;
			}
		}
		if (!foundSales) {
			System.out.println("No sales data available for the specified date.");
		}
		
	}
	private void generateMonthlyReportToFile(int month, int year) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\MOHAMED\\Desktop\\SalesReport.txt", true))) {
	        writer.write("Monthly Report for " + month + "/" + year + "\n");
	        writer.write("-----------------------\n\n");

	        // Logic to find the best seller of the month
	        Sales bestSeller = findBestSellerOfMonth(month, year);
	        if (bestSeller != null) {
	            writer.write("Best Seller of the Month:\n");
	            writer.write("Product ID: " + bestSeller.getProductId() + "\n");
	            writer.write("Product Name: " + bestSeller.getProductName() + "\n");
	            writer.write("Quantity Sold: " + bestSeller.getQuantitySold() + "\n\n");
	        } else {
	            writer.write("No sales data available for the month.\n\n");
	        }

	        // Logic to calculate total revenue and number of transactions for the month
	        //double totalRevenue = calculateMonthlyTotalRevenue(month, year);
	        int numTransactions = countTransactionsForMonth(month, year);
            String date= String.valueOf(month)+"/"+String.valueOf(year);
            writer.write("Total Number of Transactions: " + numTransactions + "\n");
	        writer.write("Total Revenue of the Month: "+ month+"/"+ year+ " is  "  + calculateMonthlyTotalRevenue(date)+" TND" + "\n");        
	        writer.write("Total Purchases of the Month: "+ month+"/"+ year+ " is  "  + calculateMonthlyTotalPurchases(date)+" TND" + "\n");        
	        writer.write("Net Profit of the Month: "+ month+"/"+ year+ " is  "  + (calculateMonthlyTotalRevenue(date)-calculateMonthlyTotalPurchases(date))+" TND" + "\n");        
	        writer.write("======================\n\n\n\n");
	        System.out.println("Monthly report appended to Sales Report File");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void generateYearlyReportToFile(int year) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\MOHAMED\\Desktop\\SalesReport.txt", true))) {
	        writer.write("Yearly Report for " + year + "\n");
	        writer.write("-----------------------\n\n");

	        // Logic to find the best seller of the year
	        Sales bestSeller = findBestSellerOfYear(year);
	        if (bestSeller != null) {
	            writer.write("Best Seller of the Year:\n");
	            writer.write("Product ID: " + bestSeller.getProductId() + "\n");
	            writer.write("Product Name: " + bestSeller.getProductName() + "\n");
	            writer.write("Quantity Sold: " + bestSeller.getQuantitySold() + "\n\n");
	        } else {
	            writer.write("No sales data available for the year.\n\n");
	        }

	        // Logic to calculate total revenue and number of transactions for the year
	        
	        int numTransactions = countTransactionsForYear(year);
	        //writer.write("Total Revenue: " + totalRevenue + " TND\n");
	        writer.write("Total Number of Transactions: " + numTransactions + "\n");
	        writer.write("Total Revenue of the Year: "+ year+" is  "  + calculateYearlyTotalRevenue(year)+" TND"  + "\n");
	        writer.write("Total Purchases of the year: "+ year+ " is  "  + calculateYearlyTotalPurchases(year)+" TND" + "\n");        
	        writer.write("Net Profit of the year: "+ year+ " is  "  + (calculateYearlyTotalRevenue(year)-calculateYearlyTotalPurchases(year))+" TND" + "\n");        
	        writer.write("======================\n\n\n\n");
	        writer.write("======================\n\n\n\n");
	        System.out.println("Yearly report appended to Sales Report File");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private Sales findBestSellerOfYear(int year) {
	    Map<Integer, Integer> quantitySoldMap = new HashMap<>();

	    // Iterate through sales and accumulate quantity sold for each product in the given year
	    for (Sales sale : loadSalesFromFile()) {
	        if (isSameYear(sale.getDate(), year)) {
	            int productId = sale.getProductId();
	            int quantitySold = sale.getQuantitySold();
	            quantitySoldMap.put(productId, quantitySoldMap.getOrDefault(productId, 0) + quantitySold);
	        }
	    }

	    // Find the product with the highest quantity sold for the year
	    int topProductId = -1;
	    int maxQuantitySold = -1;
	    for (Map.Entry<Integer, Integer> entry : quantitySoldMap.entrySet()) {
	        if (entry.getValue() > maxQuantitySold) {
	            topProductId = entry.getKey();
	            maxQuantitySold = entry.getValue();
	        }
	    }

	    // Return the sales details of the best seller for the year
	    if (topProductId != -1) {
	        for (Sales sale : loadSalesFromFile()) {
	            if (sale.getProductId() == topProductId && isSameYear(sale.getDate(), year)) {
	                return sale;
	            }
	        }
	    }
	    return null;
	}
	private Sales findBestSellerOfMonth(int month, int year) {
	    Map<Integer, Integer> quantitySoldMap = new HashMap<>();

	    // Iterate through sales and accumulate quantity sold for each product in the given month and year
	    for (Sales sale : loadSalesFromFile()) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(sale.getDate());
	        int saleMonth = cal.get(Calendar.MONTH) + 1; // Adding 1 because Calendar.MONTH is zero-based
	        int saleYear = cal.get(Calendar.YEAR);
	        if (saleMonth == month && saleYear == year) {
	            int productId = sale.getProductId();
	            int quantitySold = sale.getQuantitySold();
	            quantitySoldMap.put(productId, quantitySoldMap.getOrDefault(productId, 0) + quantitySold);
	        }
	    }

	    // Find the product with the highest quantity sold for the month and year
	    int topProductId = -1;
	    int maxQuantitySold = -1;
	    for (Map.Entry<Integer, Integer> entry : quantitySoldMap.entrySet()) {
	        if (entry.getValue() > maxQuantitySold) {
	            topProductId = entry.getKey();
	            maxQuantitySold = entry.getValue();
	        }
	    }

	    // Return the sales details of the best seller for the month and year
	    if (topProductId != -1) {
	        for (Sales sale : loadSalesFromFile()) {
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(sale.getDate());
	            int saleMonth = cal.get(Calendar.MONTH) + 1; // Adding 1 because Calendar.MONTH is zero-based
	            int saleYear = cal.get(Calendar.YEAR);
	            if (sale.getProductId() == topProductId && saleMonth == month && saleYear == year) {
	                return sale;
	            }
	        }
	    }
	    return null;
	}
	private int countTransactionsForYear(int year) {
	    int transactionCount = 0;

	    // Iterate through sales and count transactions for the given year
	    for (Sales sale : loadSalesFromFile()) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(sale.getDate());
	        int saleYear = cal.get(Calendar.YEAR);
	        if (saleYear == year) {
	            transactionCount++;
	        }
	    }

	    return transactionCount;
	}
	private int countTransactionsForMonth(int month, int year) {
	    int transactionCount = 0;

	    // Iterate through sales and count transactions for the given month and year
	    for (Sales sale : loadSalesFromFile()) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(sale.getDate());
	        int saleMonth = cal.get(Calendar.MONTH) + 1; // Adding 1 because Calendar.MONTH is zero-based
	        int saleYear = cal.get(Calendar.YEAR);
	        if (saleMonth == month && saleYear == year) {
	            transactionCount++;
	        }
	    }

	    return transactionCount;
	}
    public void calculateDailyTotalPurchases() {
    	Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the date (dd/MM/YYYY): ");
	    String dateString = scanner.next();
    	
    	  try {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        Date date = dateFormat.parse(dateString);
		        double totalPurchases = 0;
        // Retrieve the purchases records
        List<Purchases> purchases = loadPurchasesFromFile();

        // Iterate through purchases and accumulate total purchases for the specified date
        for (Purchases purchase : purchases) {
            if (isSameDay(purchase.getDate(), date)) {
                totalPurchases += purchase.getCost();
            }
        }
        System.out.println("Total revenue for " + dateString + ": " + totalPurchases + " TND");
		    } catch (ParseException e) {
		        System.out.println("Invalid date format. Please enter the date in the format dd/MM/YYYY.");
		    }
		}

    public double calculateMonthlyTotalPurchases(String monthString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        Date month = null;
		try {
			month = dateFormat.parse(monthString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	double totalPurchases = 0;

        // Retrieve the purchases records
        List<Purchases> purchases = loadPurchasesFromFile();

        // Iterate through purchases and accumulate total purchases for the specified month and year
        for (Purchases purchase : purchases) {
            if (isSameMonth(purchase.getDate(), month)) {
                totalPurchases += purchase.getCost();
            }
        }

        return totalPurchases;
    }

    public double calculateYearlyTotalPurchases(int year) {
        double totalPurchases = 0;

        // Retrieve the purchases records
        List<Purchases> purchases = loadPurchasesFromFile();

        // Iterate through purchases and accumulate total purchases for the specified year
        for (Purchases purchase : purchases) {
            if (isSameYear(purchase.getDate(), year)) {
                totalPurchases += purchase.getCost();
            }
        }

        return totalPurchases;
    }

	}
