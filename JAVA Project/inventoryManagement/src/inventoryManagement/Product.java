package inventoryManagement;
public class Product {
    private int productid;
    private String productname;
    private int quantityinstock;
    private double price;
    private String category;

    public int getProductid() {
        return productid;
    }

    public String getProductname() {
        return productname;
    }

    public int getQuantityinstock() {
        return quantityinstock;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setQuantityinstock(int quantityinstock) {
        this.quantityinstock = quantityinstock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // this method adds a given quantity to the quantity in stock of the product
    public void addQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity to add must be non-negative");
        }
        this.quantityinstock += quantity;
    }

    // this method removes a given quantity to the quantity in stock of the product
    // (if quantity is greater than the current stock, it throws an exception)
    public void removeQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity to remove must be non-negative");
        }

        if (quantity > quantityinstock) {
            // Handle the situation where the requested quantity is greater than the stock
            throw new IllegalStateException("Not enough quantity in stock");
        }

        quantityinstock -= quantity;
    }
    
    // this method increases the price by a given percentage 
    public void increasePriceByPercentage(int addedpercentage) {
        if (addedpercentage < 0) {
            throw new IllegalArgumentException("Percentage to add must be non-negative");
        }
        this.price *=1+0.01*addedpercentage ;
    }

    // this method decreases the price by a given percentage
    // (if percentage is greater or equal than 100%, it throws an exception)
    public void decreasePriceByPercentage(int removedpercentage) {
        if (removedpercentage < 0) {
            throw new IllegalArgumentException("percentage to remove must be non-negative");
        }

        if (removedpercentage >= 100) {
            throw new IllegalStateException("Price must be non-negative");
        }

        price *= 1-0.01*removedpercentage;
    }

    @Override
    public String toString() {
        return "Product [productid=" + productid + ", productname=" + productname + ", quantityinstock=" + quantityinstock
                + ", price=" + price + ", category=" + category + "]";
    }

    public Product(int productid, String productname, int quantityinstock, double price, String category) {
        this.productid = productid;
        this.productname = productname;
        this.quantityinstock = quantityinstock;
        this.price = price;
        this.category = category;
    }
 // this method adds a given price to the price 
    public void increasePrice(double addedprice) {
        if (addedprice < 0) {
            throw new IllegalArgumentException("price to add must be non-negative");
        }
        this.price += addedprice;
    }

    // this method removes a given price from the price
    // (if the given price is greater than the current price, it throws an exception)
    public void decreasePrice(double removedprice) {
        if (removedprice < 0) {
            throw new IllegalArgumentException("Quantity to remove must be non-negative");
        }

        if (removedprice > price) {
            throw new IllegalStateException("Price must be non-negative");
        }

        price -= removedprice;
    }
    //this method displays product details
    public void displayProductAttributes(Product p)
    {System.out.println( p.toString());}
}