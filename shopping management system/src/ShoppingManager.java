public interface ShoppingManager {
  
    //Abstract methods that are overridden in the WestminsterShoppingManager class--------
    void addProduct();
    String deleteProduct(String id);
    String printProducts();
    String saveProduct();
    String loadProduct();
    int openGUI(User user);
    //-------------------------------------------------------------------------------------
      
}
