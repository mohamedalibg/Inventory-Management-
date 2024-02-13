package inventoryManagement;
import java.util.*;

public class Purchases {
    private int purchaseId;
    private int productId;
    private String productName;
    private Date date;
    private double unitCost;
    private double cost;
    private int quantityPurchased;
    private String supplier;
    

    public Purchases(int purchaseId, int productId, String productName, int quantityPurchased, double unitCost,double cost, Date date, String supplier) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.productName = productName;
        this.quantityPurchased = quantityPurchased;
        this.unitCost=unitCost;
        this.cost = calculateCost();
        this.date = date;
        this.supplier=supplier;
    }

    private double calculateCost() {      
            this.cost = unitCost * quantityPurchased;
        
        return this.cost;
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

    public double getCost() {
        return cost;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
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

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
}
