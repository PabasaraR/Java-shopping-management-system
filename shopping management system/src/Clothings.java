
public class Clothings extends Product {
    //Variable belonging to Clothing products only------------------------------
    String size;                
    String colour;
    
    // Clothing Product Constructor---------------------------------------------
    public Clothings(String productID, String productName, int availableItems, double price,String size,String colour) 
    {
        super(productID, productName, availableItems, price); 
        this.size=size;
        this.colour=colour;
    }
 // Clothing class getters and setters----------------------------------------
    public String getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
 
// The abstract methods in the Product class override in Clothing class ------------ 
    @Override
    public String getProductID() {
        return productID;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public int getAvailableItems() {
        return itemQuentity;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setProductID(String productID) {
        this.productID=productID;
    }

    @Override
    public void setProductName(String productName) {
        this.productName=productName;
    }

    @Override
    public void setAvailableItems(int availableItems) {
        this.itemQuentity=availableItems;
    }

    @Override
    public void setPrice(double price) {
        this.price=price;
    }  
}
