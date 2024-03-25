
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart implements Serializable  {
    
    private ArrayList<Product> shoppingCart=new ArrayList();        //The arraylist that stores the products items that the customer adds to the shopping cart.
    private HashMap<String,Integer>productQuantity=new HashMap();   //The hashmap that stores the quantity of product being added to the cart.

    //The method that adds product item to the shoppingCart ArrayList---------------
    public void addItemsInCart(Product e)
    {
        shoppingCart.add(e);
    }

    //The method that retrieves the product item from the shoppingCart ArrayList---
    public Product getItem(int i)
    {
        return shoppingCart.get(i);
    }

    //Return the size of the shoppingCart Arraylist---------------------------------
    public int itemsSize()
    {
        return shoppingCart.size();
    }

    //The method to remove a desired product item from the shoppingCart ArrayList---
    public void removeItemsInCart(int i)
    {
        shoppingCart.remove(i);
    }

    //The method to get the total amount of all the items added to the cart---------
    public double total(double amount,double discount1 ,double discount2)
    {
        double totalAmount=amount-(discount1+discount2);
        return totalAmount;
    }    

    //The method that adds the quantity to the productQuantity HashMap--------------
    public  void addQuantity(String a,int b)
    {
        productQuantity.put(a, b);
    }

    //The method that returns the quantity in the productQuantity HashMap-----------
    public int getQuantity(String a)
    {
        return productQuantity.get(a);
    }

    //The method that returns the productQuantity HashMap---------------------------
    public HashMap getProductQuantityHashMap()
    {
        return productQuantity;
    }

    //The method that clears the shoppingCart ArrayList-----------------------------
    public void clearShopingCart()
    {
        shoppingCart.clear();
    }

    //This method that clears the productQuantity HashMap---------------------------
    public void clearProductQuantity()
    {
        productQuantity.clear();
    }

    //This method can delete the quantity of the required product in the productQuantity HashMap----
    public void clearQuantityItems(String a)
    {
        productQuantity.remove(a);
    }
}
