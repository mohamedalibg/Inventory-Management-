package inventoryManagement;
import java.util.*;
public class Sales {
	private int saleId;
    private int productId;
    private String productName;
    private Date date;
    private double revenue;
    private int quantitySold;

    public Sales(int saleId, int productId, String productName, int quantitySold,double revenue, Date date) {
        this.saleId = saleId;
    	this.productId = productId;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.revenue=calculateRevenue();
        this.date = date;
    }

    private double calculateRevenue() {
        Product product = getProductById(productId);
        if (product != null) {
            double price = product.getPrice();
            this.revenue = price * quantitySold;
        }
        return this.revenue;
    }

    private Product getProductById(int productId) {
        inventoryManager manager = new inventoryManager(); 
        List<Product> inventory = manager.loadInventoryFromFile();

        for (Product product : inventory) {
            if (product.getProductid() == productId) {
                return product;
            }
        }
        return null;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Date getDate() {
        return date;
    }

    public double getRevenue() {
        return revenue;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
}

