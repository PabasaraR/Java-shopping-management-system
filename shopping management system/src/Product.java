
import java.io.Serializable;

public abstract class Product implements Serializable {
    //Common variable for electronic products and clothing products--------------------------------
    protected String  productID;                   
    protected String productName;
    protected int itemQuentity;
    protected double price;
    //----------------------------------------------------------------------------------------------
        
    // Product Constructor--------------------------------------------------------------------------
    public Product(String productID, String productName, int availableItems, double price) { 
        this.productID = productID;
        this.productName = productName;
        this.itemQuentity = availableItems;
        this.price = price;
    }
    //----------------------------------------------------------------------------------------------
     
    //abstract getters and setters------------------------------------------------------------------
    
    public abstract String getProductID();

    public abstract String getProductName();

    public abstract int getAvailableItems(); 

    public abstract double getPrice();

    public abstract void setProductID(String productID) ;

    public abstract void setProductName(String productName);

    public abstract void setAvailableItems(int availableItems);

    public abstract void setPrice(double price) ;
    //----------------------------------------------------------------------------------------------
    
}
