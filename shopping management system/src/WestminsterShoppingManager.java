
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    
    public static int oneTime=0;
    private static Boolean trueORfall=true;         //This booline variable is used to control the while loop .
    private static int checkId=0;                   // This variable is used to check if a product being entered has been previously entered().
    
    public static Scanner input =new Scanner(System.in);
     
    public static ArrayList<Product> productList=new ArrayList<>();        //ArryList that stores Electronics and clothing products.
    public static ArrayList<Product> alphabeticalList=new ArrayList<>();   //This ArrayList is used to arrange the product list in alphabetical order when printing.
    public static ArrayList<User> oldUsers=new ArrayList<>();            //The data of all users who log in to the system and buy and do not buy goods are stored here.
    
    //The Main method-----------------------------------
    public static void main(String[] args)
    {    
        while(trueORfall)
        {          
            seeMenu();
        }
    }
    //-------------------------------------------------- 
      
    //The consol menu--------------------------------------------------------------------------------
    public static void seeMenu()
    {                  
        System.out.println("---------------------------------------------------------");
        System.out.println("-------------------------MENU---------------------------");
        System.out.println("---------------------------------------------------------");
        System.out.println("Add a new product---------------------------- press[1] \n");
        System.out.println("Delete a product----------------------------- press[2]\n");
        System.out.println("Print the list1 of the products-------------- press[3]\n");
        System.out.println("Save in a file------------------------------- press[4]\n");
        System.out.println("Load data------------------------------------ press[5]\n");
        System.out.println("Lod gui-------------------------------------- press[6]\n");
        System.out.println("Exit----------------------------------------- press[7]\n");     
        System.out.print("Enter the option: ");
        String userInput1=input.next();
        System.out.println("");
                                
        WestminsterShoppingManager newManager=new WestminsterShoppingManager();

        switch(userInput1) 
        {
            case "1":
                newManager.addProduct();  
                break;
            case "2":
                System.out.print("Enter the ID number of the items to be deleted- ");
                String deletID=input.next();//Get the ID of the product to be deleted
                newManager.deleteProduct(deletID);
                break;
            case "3":
                newManager.printProducts();
                break;
            case "4":
                newManager.saveProduct();
                break;    
            case "5":
                newManager.loadProduct();      
                break;  
            case "6":
                System.out.println("enter username");
                String userName=input.next();
        
                System.out.println("enter pasword");
                String password=input.next();
                
                newManager.openGUI(new User(userName, password));
                break;
            case "7":
                trueORfall=false;
                break;
            default:
                System.out.println("Wrong input. This option is not available in the app. Check again\n");
                break;
        }       
    }
    //----------------------------------------------------------------------------------------------------------
   
    //In this method the product details are retrived---------------------------
    @Override
    public void addProduct() 
    {    checkId=0;    
        if(productList.size()<50) //check the product count in the system and if product count<50 the product will add to the system.
        {
            System.out.println("Electronis items or Clothing items'E or C'");
            String userInput2=input.next();//Getting the type of product from the user.
                
            System.out.println("enter product id");
            String ProductID=input.next();//Getting the product id from the user.
                
            //Checking if the entered product is already available in the system-------------------------------
            for(int i=0;i<productList.size();i++)
            { 
                if(ProductID.equals(productList.get(i).getProductID()))
                {
                    checkId=1;
                }
            } 
            //--------------------------------------------------------------------------------------------------
            
            switch(userInput2.toUpperCase())
            {
                case "E":
                    //If the Electronics product to be added is not available in the system,adding it to the system---------
                    if(checkId==0)
                    {   
                        try
                        {   //Get the details of the Electronic product---------
                            System.out.println("enter product name");
                            String eProductName=input.next();
                            System.out.println("enter the item quantity");
                            int eAvaliableItems=input.nextInt();
                            System.out.println("enter price");
                            double ePrice=input.nextDouble();
                            System.out.println("enter brand name");
                            String eBrandName=input.next();
                            System.out.println("enter warranty Period (Years)");
                            int eWarrantyPeriod=input.nextInt();
                            //--------------------------------------------------
                            
                            //Create Electronics product object-----------------
                            Product product=   new Electronics(ProductID,eProductName,eAvaliableItems,ePrice,eBrandName,eWarrantyPeriod);
                            addElectronic(product);// Add the Electronics product to system(Call to the method of entering the electronic product into the system)
                            System.out.println("Electronics product add");
                        }
                        catch(Exception e){}
                    }
                    //-----------------------------------------------------------------------------------------------------
                
                    else
                    {//If the product trying to add already exists in the system, the part that increase the quantity of the product in the system.----------   
                        System.out.println("this product already in system. You can increase the quantity here");
                            
                        for(int i=0;i<productList.size();i++)
                        {
                            if(ProductID.equals(productList.get(i).getProductID()))
                            {   
                                //Increasing the quantity of a electronic product already in the system------- 
                                if(productList.get(i) instanceof Electronics )
                                {
                                    System.out.println("enter the items quantity");
                                    int itemQuentity=input.nextInt();

                                    productList.get(i).setAvailableItems(productList.get(i).getAvailableItems()+itemQuentity);
                                }
                                //Informing if the electronic product to be added to the system already exists as a clothing product in the system-----
                                else{System.out.println("this items is clothing items");
                                checkId=0;}
                            }
                        }
                    //-------------------------------------------------------------------------------------------------------------------------------------------    
                    }
                break;
                
                case "C":
                    //If the Clothing product to be added is not available in the system,adding it to the system-------------
                    if(checkId==0)
                    {   
                        try
                        {   //Get the details of the Clothing product---------
                            System.out.println("enter product name");
                            String cProductName=input.next();
                            System.out.println("enter available items");
                            int cAvaliableItems=input.nextInt();
                            System.out.println("enter price");
                            double cPrice=input.nextDouble();
                            System.out.println("enter size (S,M,L,XL,XXL)");
                            String cSize=input.next();
                            System.out.println("enter colour");
                            String cColour=input.next();
                            //--------------------------------------------------
                            
                            //Create Clothing product object--------------------
                            Product product=new Clothings(ProductID, cProductName, cAvaliableItems, cPrice, cSize, cColour);
                            addClothing(product);// Add the Clothing product to system(Call to the method of entering the clothing product into the system) 
                            System.out.println("Clothing product add");
                        }
                        catch(Exception e)
                        {}
                    }
                    //---------------------------------------------------------------------------------------------------
                
                    else
                    {//If the product trying to add already exists in the system, the part that increase the quantity of the product in the system.----------
                        System.out.println("this product already in system.You can increase the quantity here");

                        for(int i=0;i<productList.size();i++)
                        {
                            if(ProductID.equals(productList.get(i).getProductID()))
                            { //Increasing the quantity of a clothing product already in the system------- 
                                if(productList.get(i) instanceof Clothings )
                                {
                                    System.out.println("enter the items quantity");
                                    int itemQuentity1=input.nextInt();
                                    
                                    productList.get(i).setAvailableItems(itemQuentity1+productList.get(i).getAvailableItems());
                                }
                                //Informing if the clothing product to be added to the system already exists as a electronic product in the system-----
                                else{System.out.println("this items is clothing items");
                                checkId=0;}
                            }
                        }   
                    }
                break;

                default:
                    System.out.println("Wrong input. plese try again");
                    break;
            }
        }
        
        //Displaying that the system is full----------------------
        else
        {
            System.out.println("System is allready full");
        }
        //--------------------------------------------------------
    }
    
    //Adding electronic product to the ProductList ArrayList------
    public String addElectronic(Product electronic)
    { 
        productList.add(electronic);        
        return "Add";//for use in junit testing
    }
    
     //Adding clothing product to the ProductList ArrayList------
    public String addClothing(Product clothings)
    { 
        productList.add(clothings);             
        return "Add";//for use in junit testing
    }
    
    
    //Deleting the desired product from the system----------------------------------------------------------------------------------
    @Override
    public String deleteProduct(String deletID) 
    {
        String testString="null";//for use in junit testing
        int check =0;
            for (int i = 0; i < productList.size(); i++)
            {                   
                if(deletID.equals(productList.get(i).getProductID()))//Identiying a product to deleted
                {//Print the details of the product being deleted--------------------------------------
                    
                    System.out.print("Product ID- "+productList.get(i).getProductID());
                    System.out.print("Product Name-"+productList.get(i).getProductName());
                    System.out.print("Available Items-"+productList.get(i).getAvailableItems());
                    System.out.print("Price-"+productList.get(i).getPrice());
                    
                    if(productList.get(i) instanceof Electronics )
                    {
                        System.out.print("Brand-"+((Electronics)productList.get(i)).getBrand());
                        System.out.print("Warranty Period-"+((Electronics)productList.get(i)).getWarrantyPeriod());
                    }
                    else
                    {  
                        System.out.print("Size-"+((Clothings)productList.get(i)).getSize());
                        System.out.println("Colour-"+((Clothings)productList.get(i)).getColour());
                    }
                    //-----------------------------------------------------------------------------------
                    
                    productList.remove(i);//Delete product
                    System.out.println(" product Deleted successfully ");
                    testString="deleted";//for use in junit testing
                    check=0;
                 //---------------------------------------------------------------------------------------------
                }
                else{check=1;}//This displayed if the product trying to delete is not available in the system
            }
            if(check==1){System.out.println("This product is not available in the system");}
            return testString;//for use in junit testing
    }//-----------------------------------------------------------------------------------------------------------------------------------
   
    //Displaying the products in the system in slphabetical order------------------------------------------------
    @Override
    public String printProducts() 
    {  
        String testString="null";//for use in junit testing
        
        //Adding products in the system to the alphabeticalList ArrayList------
        alphabeticalList.clear();
        for(int i=0;i<productList.size();i++)
        {
            alphabeticalList.add(productList.get(i));
        }
        //----------------------------------------------------------------------
        
        //Sorting the alphabeticalList(using Bubble Sort )----------------------
        for(int i=0;i<alphabeticalList.size();i++) 
        {
            for (int j = i + 1; j < alphabeticalList.size(); j++) 
            {
                if (alphabeticalList.get(i).getProductID().compareTo(alphabeticalList.get(j).getProductID()) > 0) 
                {
                        Product temporary = alphabeticalList.get(i);
                        alphabeticalList.set(i, alphabeticalList.get(j));
                        alphabeticalList.set(j, temporary);
                    }
                }
            }
        //----------------------------------------------------------------------
        
        //Displaying the details of the products in the sorted list-------------    
        for(int i=0;i<alphabeticalList.size();i++){
             
            System.out.print("Product ID- "+alphabeticalList.get(i).getProductID());
            System.out.print(",  Product Name-"+alphabeticalList.get(i).getProductName());
            System.out.print(",  Available Items-"+alphabeticalList.get(i).getAvailableItems());
            System.out.print(",  Price-"+alphabeticalList.get(i).getPrice());
                    
            if(alphabeticalList.get(i) instanceof Electronics )
            {  
                System.out.print(",  Type=Electronic");
                System.out.print(",  Brand-"+((Electronics)alphabeticalList.get(i)).getBrand());
                System.out.print(",  Warranty Period-"+((Electronics)alphabeticalList.get(i)).getWarrantyPeriod());
            }
            else
            {  
                System.out.print(",  Type=Clothing");
                System.out.print(",  Size-"+((Clothings)alphabeticalList.get(i)).getSize());
                System.out.print(",  Colour-"+((Clothings)alphabeticalList.get(i)).getColour());
            }
            System.out.println("\n");
                    
            testString="priented";//for use in junit testing
        } 
        //----------------------------------------------------------------------
        return testString;//for use in junit testing
    }
    //-----------------------------------------------------------------------------------------------------------

    //Saving all data in the system to text files for reuse---------------------
    @Override
    public String saveProduct() 
    {           
        String checkId1="null";//for use in junit testing
        String checkId2="null";//for use in junit testing
        
        //Saving the productList ArrayList--------------------------------------
         File file1= new File("product.txt");
        try{
            FileOutputStream fileOut= new FileOutputStream(file1);
            ObjectOutputStream objOut=new ObjectOutputStream(fileOut) ;        
                Iterator iterator= productList.iterator();
                while(iterator.hasNext())
                {
                    Product newProduct=(Product)iterator.next();
                    objOut.writeObject(newProduct);
                    checkId1="productSave";//for use in junit testing
                }
        } 
        catch(Exception e){}
        //----------------------------------------------------------------------
        
        //Saving datails of previous customer(items purchased and not purchased)
        File file2= new File("allUsers.txt");
        try{
            FileOutputStream fileOut= new FileOutputStream(file2);
            ObjectOutputStream objOut=new ObjectOutputStream(fileOut) ;
            Iterator iterator= oldUsers.iterator();
            while(iterator.hasNext())
            {
                User newUser=(User)iterator.next();
                objOut.writeObject(newUser);
                checkId2="userSave";
            }//for use in junit testing
        } 
        catch(Exception e){}
        //----------------------------------------------------------------------
        
        //Saving the key value in the HashMap where the shopinCart object is stored-----
        File file3= new File("key.txt");
        try{
            FileWriter write=new FileWriter(file3);
            BufferedWriter bWrite= new BufferedWriter(write);
            for(String key:CartGUI.map2.keySet())
            {
                bWrite.write(key);
                bWrite.newLine();
            }
            bWrite.close();        
        }
        catch(Exception e){}
        //----------------------------------------------------------------------------
        
        //Saving the object create related to the customer's shopinCart in the text file-----
        File file4= new File("value.txt");
        try{
            FileOutputStream fileOut=new FileOutputStream(file4);
            ObjectOutputStream objOut=new ObjectOutputStream(fileOut); 
            for(String key:CartGUI.map2.keySet())
            {
                ShoppingCart value=(ShoppingCart)CartGUI.map2.get(key);
                objOut.writeObject(value);
            }
        }
        catch(Exception e){}
        //----------------------------------------------------------------------
        
        //Saving the data of the users who bought the products------------------
        File file5= new File("buyers.txt");
        try{
            FileOutputStream fileOut= new FileOutputStream(file5);
            ObjectOutputStream objOut=new ObjectOutputStream(fileOut) ;
                Iterator iterator= DataBase.customerSave.iterator();
                while(iterator.hasNext())
                {
                    User buyer=(User)iterator.next();
                    objOut.writeObject(buyer);
                    checkId2="userSave";}
                } 
        catch(Exception e){}
        //----------------------------------------------------------------------
        
        System.out.println("-------------------------");
        System.out.println("Data saved successfull");
        System.out.println("--------------------------\n\n");
        
        
        return checkId1+checkId2;//for use in junit testing
    }
    //--------------------------------------------------------------------------
    
    //Loading all data saved to text file---------------------------------------
    @Override
    public String loadProduct() {
        String tesString1=null;//for use in junit testing
        String tesString2=null;//for use in junit testing
        
        //Reloading Product items----------------------------------------------
        try{
            productList.clear();
            FileInputStream fileIn = new FileInputStream("product.txt");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            
            while (true) 
            {
                try 
                {
                    Product Items = (Product) objIn.readObject();
                    productList.add(Items);
                    tesString1="loadProduct";
                }
                catch (EOFException e) 
                {
                    break; 
                }
            }
        }
        catch(Exception e){}
        //----------------------------------------------------------------------
        
       
        //Reloading all users details-------------------------------------------
        try{
            oldUsers.clear();
            FileInputStream fileIn = new FileInputStream("allUsers.txt");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            while (true) {
                try {
                    User user = (User) objIn.readObject();
                    oldUsers.add(user);
                    tesString2="loadUser";
                }
                catch (EOFException e) 
                {
                    break; 
                }
            }
        }
        catch(Exception e){}
        //----------------------------------------------------------------------
         
        //Reloading user's shopinCart  objects---------------------------------------------
        CartGUI.map2.clear();
        try{
            FileInputStream fileIn = new FileInputStream("value.txt");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
         
            while(true)
            {
                try
                {
                    File file=new File("key.txt");
                    FileReader reader= new FileReader(file);
                    BufferedReader bReader= new BufferedReader(reader);
                    ShoppingCart userObj = (ShoppingCart) objIn.readObject();
                    String key=null;
                    while((key=bReader.readLine())!=null)
                    {
                        CartGUI.map2.put(key, userObj);
                    }
                }
                catch(IOException e)
                {
                    break;
                }
            }
        }
          catch(Exception e){}
        //----------------------------------------------------------------------
        
        //Reloading the details of previously purchased individuals into the system---------
        try{
            DataBase.customerSave.clear();
            FileInputStream fileIn6 = new FileInputStream("buyers.txt");
            ObjectInputStream objIn6 = new ObjectInputStream(fileIn6);

            while (true) {
                try 
                {
                    User c = (User) objIn6.readObject();
                    DataBase.customerSave.add(c);    
                    tesString2="loadUser";
                }
                catch (EOFException e) 
                {
                    break; 
                }
            }
        }catch(Exception e){}
        //--------------------------------------------------------------------------------

                System.out.println("-------------------------");
                System.out.println("Data load successfull");
                System.out.println("--------------------------\n\n");
                
            return tesString1+tesString2;//for use in junit testing
    }
    //--------------------------------------------------------------------------

    //Open gui and Saving user details ------------------------------------------------------------------
    @Override
    public int openGUI(User user) {
        
        MainPageGUI.shopinCartArray.clear(); 
        oldUsers.add(0,user);
        oneTime=1;
        MainPageGUI fram=new MainPageGUI();
        fram.setTitle("new gui");
        fram.setSize(500, 700);
        fram.getContentPane().setBackground(Color.WHITE);
        fram.setVisible(true);
        
        return oldUsers.size();//for use in junit testing
        //----------------------------------------------------------------------
    }   
}
