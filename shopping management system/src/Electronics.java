
public class Electronics extends Product {

  //Variable belonging to Electronics products only-----------------------------
    private String brand;
    private int warrantyPeriod;

 // Electronics Product Constructor---------------------------------------------
    public Electronics(String productID, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productID, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
 
    // Electronics class getters and setters-------------------------------------
    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    //--------------------------------------------------------------------------
    
    // The abstract methods in the Product class override in Electronics class-------------
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
    //-----------------------------------------------------------------------------------
}