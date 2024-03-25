
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CartGUI extends JFrame {
    
    public static HashMap<String, ShoppingCart> map2=new HashMap<>();//This is used to store the shoppingCart object related to the user.
    
    static ShoppingCart obj=null;//This represents the object created by the shoping cart.
    static int check =0;//Thsi is used to ensure that when an old customer returns, the items in their shoping cart have been reloaded into the shopingCart ArrayList.

    JButton b1,b2;          //buttons
    JTable table2;          //table
    JPanel p3,p1,p2;        //panels
    JLabel l1,l2,l3,l4,l5;  //labels

    public CartGUI()
    {
        //The system keeps the details of the currentlylogged in user here
        String newCustomer=WestminsterShoppingManager.oldUsers.get(0).getPassword()+WestminsterShoppingManager.oldUsers.get(0).getUserName();
                
        int checkOld=0;//Used to identify an old user
        
        if(WestminsterShoppingManager.oldUsers.size()>1)
        {   //This section checks if the user is and old user------------------------------------
            for(int i=1;i<WestminsterShoppingManager.oldUsers.size();i++)
            {
                String oldCustomer=WestminsterShoppingManager.oldUsers.get(i).getPassword()+WestminsterShoppingManager.oldUsers.get(i).getUserName();
                if (newCustomer.equals(oldCustomer))
                {
                    checkOld=i;
                    break;
                } 
            }
            //-------------------------------------------------------------------------------------
            
            //By this section, if the user is an old user, he will get the corresponding shoppingCart object.If he is a new usser, a new shopingCart object is created for him
            if(checkOld!=0)
            {
                //Retrieving the user's old shoppingCart object
                obj=map2.get(WestminsterShoppingManager.oldUsers.get(checkOld).getPassword()+WestminsterShoppingManager.oldUsers.get(checkOld).getUserName());
               
                //Loading old user's shopping cart back into shoppingCart arrayList-----------
                if(check==0)
                {
                    for(int i=0;i<obj.itemsSize();i++)
                    {
                        for(int j=0;j<obj.getQuantity(obj.getItem(i).getProductID());j++)
                        {
                             MainPageGUI.shopinCartArray.add(obj.getItem(i).getProductID());
                        }
                         check=1;
                    }
                }//------------------------------------------------------------------------------                              
            }
            else
            {
                obj= new ShoppingCart();//Creating a new shoppingCart object
                map2.put(newCustomer, obj);//Saving the created object in relation to the user
            }
        }
        else
        {
            obj= new ShoppingCart();//Creating a new shoppingCart object
            map2.put(newCustomer, obj);//Saving the created object in relation to the user
        }
         
        
        //Check how many is added to the shopping cart from one product------------------------
        for(int i=0;i<MainPageGUI.shopinCartArray.size();i++)
        { 
            int quantityOfItems=0;
            for(int j=0;j<MainPageGUI.shopinCartArray.size();j++)
            {
                if(MainPageGUI.shopinCartArray.get(i).equals(MainPageGUI.shopinCartArray.get(j)))
                {
                    quantityOfItems++;//count quantity
                }
            }                                                  
            obj.addQuantity(MainPageGUI.shopinCartArray.get(i),quantityOfItems);//Entering the quantity related to the product in the hashMap
        }
        //----------------------------------------------------------------------------------------
                             
            
       //All the items that are added to the cart in the MainPageGUI class are added to the shopinCartArray .This section adds all the items in the shopinCartArray to the shopping cart array
       for(int i=0;i<MainPageGUI.shopinCartArray.size();i++)
       {
            for(int j=0;j<WestminsterShoppingManager.productList.size();j++)
            {
                int check=0;//This is used to check whether the Electronic item to be added has already been added to the corresponding array list of the shopping cart class
                
                if(MainPageGUI.shopinCartArray.get(i).equals(WestminsterShoppingManager.productList.get(j).getProductID()))
                {
                    for(int k=0;k<obj.itemsSize();k++)
                    {   //Checking if an Electronic item trying to be added to the array has already benn added--------------------------------------------------------
                        if(WestminsterShoppingManager.productList.get(j).getProductID().equals(obj.getItem(k).getProductID()))
                        {
                            check=1;
                        }                    
                    }   //----------------------------------------------------------------------------------------------------------------------------------------------
                    
                    //If an Electronic items to be added to the shoping cart's electronic items adding array list has not been added yet, adding it---
                    if(check==0)
                    {
                        obj.addItemsInCart(WestminsterShoppingManager.productList.get(j));
                    }
                    //---------------------------------------------------------------------------------------------------------------------------------
                }
            }
        }
       
       
       //Createing shoping cart table----------------------------------------------------------------------------------------------------
        String[][] data=new String[obj.itemsSize()][3];
        
        
        int size=obj.itemsSize();
        String details;
        for (int i=0;i<size;i++)
        {   //Adding Electronic product details to the row----------------------------------------------------
            if(obj.getItem(i) instanceof Electronics)
            { 
                details=obj.getItem(i).getProductID()+","+obj.getItem(i).getProductName()+","+((Electronics)obj.getItem(i)).getBrand()+","+((Electronics)obj.getItem(i)).getWarrantyPeriod();
            }
            //Adding Clothing product details to the row----------------------------------------------------
            else
            { 
                details=obj.getItem(i).getProductID()+","+obj.getItem(i).getProductName()+","+((Clothings)obj.getItem(i)).getSize()+","+((Clothings)obj.getItem(i)).getColour();             
            }
           
            int qunan=obj.getQuantity(obj.getItem(i).getProductID());
            String quantity=Integer.toString(qunan);
            double price=obj.getItem(i).getPrice()*qunan;
            String priceString=Double.toString(price);
            data[i]=new String[]{details,quantity,priceString};//Createing row
            //-------------------------------------------------------------------------------------------------
        }
        
        String[] colummnName={"Product","Quantity","Price"};
        table2=new JTable(data,colummnName);//Createing table
        //-----------------------------------------------------------------------------------------------------------------------------
        
        //Creating panels------
        p1=new JPanel();
        p3=new JPanel();
        p2=new JPanel();
        
        //Creating label-------
        l1=new JLabel();
        l2=new JLabel();
        l3=new JLabel();
        l4=new JLabel();
        l5=new JLabel();
        
        //Panels adding fram----
        add(p1,BorderLayout.SOUTH);
        add(p2,BorderLayout.CENTER);
        
        //Set panels size------
        p1.setPreferredSize(new Dimension(300,200));
        p2.setPreferredSize(new Dimension(300,200));
        
        //p3 adding in p1----
        p1.add(p3);
        
        //Table adding p2 panel--------
        p2.add(new JScrollPane(table2));
        
        //Set p3 layout type------------
        p3.setLayout(new GridLayout(7,1));
        
        //Lables adding to p3 panel-----
        p3.add(l1);
        p3.add(l2);
        p3.add(l3);
        p3.add(l4);
        p3.add(l5);
        
        //Creating b1 butten----------
        b1=new JButton("delete");
        b2=new JButton("buy");
        
        //Butten adding to p3 label---
        p3.add(b1);
        p3.add(b2);
        
        //Calculate the total value,calculate the discounts and calculate the final total value-----------------------------------------------------
        double total = 0 ;
        
        //Calculate the tatal value---------------------------------------------
        for (int i = 0; i < obj.itemsSize(); i++) 
        {
            String stringValue = (String) table2.getValueAt(i, 2);
            double price=Double.parseDouble(stringValue);        
            total=total+price;  
        }
        String stringTotal=Double.toString(total);
        l1.setText("Total-                                                                 "+stringTotal);//Display total
        //----------------------------------------------------------------------
      
        //Calculating buys at least three products of same category discount----
        double discount1=0; 
        int eItems=0;
        int cItems=0;
        for(int i=0;i<obj.itemsSize();i++){
            
            
        if(obj.getItem(i) instanceof Electronics){
            
            eItems=eItems+obj.getQuantity(obj.getItem(i).getProductID());
        }
        else{cItems=cItems+obj.getQuantity(obj.getItem(i).getProductID());}
        }
        
        if((eItems>=3 || cItems>=3)||(eItems>=3 && cItems>=3)){
             discount1=(total/100)*20;
        }
        String stringDiscount1=Double.toString(discount1);
        l3.setText("Three Items in same Category Discount-    "+stringDiscount1);//Display discount
        //----------------------------------------------------------------------
           
        //Checking whether the customer who buys the items has bought the items from the system before---------------------
        int customerCheck=0;       
        for(int i=0;i<DataBase.customerSave.size();i++)
        {
            String oldCustomer=DataBase.customerSave.get(i).getPassword()+DataBase.customerSave.get(i).getUserName();
                if (newCustomer.equals(oldCustomer)) 
                {              
                   customerCheck=1;                                            
                }       
        }
        //--------------------------------------------------------------------------------------------------------------
        
        //Calculating first purchase discount--------------------------------------------------------------------------
        double discount2=0;
        if(customerCheck==0)
        {
            discount2=(total/100)*10;       
        }
        String stringDiscount2=Double.toString(discount2);
        l2.setText("First Purchsde Discount-                               "+stringDiscount2);
        //-------------------------------------------------------------------------------------------------------------
        
        ShoppingCart obj=new ShoppingCart();
        double finalTotal=obj.total(total, discount1, discount2);//Calculating final total
       
        String stringFianlTotal=Double.toString(finalTotal);
        l4.setText("Final Total-                                                       "+stringFianlTotal);
        //------------------------------------------------------------------------------------------------------------------------
        
        //Adding actions-----------------------------
        ControllerClass control=new ControllerClass();
        b1.addActionListener(control); 
        b2.addActionListener(control);
        table2.addMouseListener(control);  
    } 
    
     private class ControllerClass extends MouseAdapter implements ActionListener
     {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //The process after pressing the delet button-------------------------------------------------
            if(e.getSource()==b1){
                String result="null";
                int row = table2.getSelectedRow();//Identify the row to be deleted
                if(row>=0)
                {
                    result = (String) table2.getValueAt(row, 0);//Identifying the product ID in the row to be deleted
                }
                 
                //In this section, a quantity of the product that is deleted from the cart is added back to the system---
                for(int i=0;i<obj.itemsSize();i++)
                {
                    if(obj.getItem(i) instanceof Electronics)
                    { 
                        if(result.equals(obj.getItem(i).getProductID()+","+obj.getItem(i).getProductName()+","+((Electronics)obj.getItem(i)).getBrand()+","+((Electronics)obj.getItem(i)).getWarrantyPeriod()))
                        {   
                            for(int j=0;j<WestminsterShoppingManager.productList.size();j++)
                            {
                                if(obj.getItem(i).getProductID().equals(WestminsterShoppingManager.productList.get(j).productID)){
                                
                                int number1=WestminsterShoppingManager.productList.get(j).getAvailableItems();
                                int number2=obj.getQuantity(obj.getItem(i).getProductID());
                                WestminsterShoppingManager.productList.get(j).setAvailableItems(number1+number2);
                                }
                            }
                            
                            for(int j=0;j<MainPageGUI.shopinCartArray.size();j++)
                            {
                                if(obj.getItem(i).getProductID().equals(MainPageGUI.shopinCartArray.get(j)))
                                {
                                    MainPageGUI.shopinCartArray.remove(j);
                                    j=j-1;
                                }
                            }
                            obj.clearQuantityItems(obj.getItem(i).getProductID());
                            obj.removeItemsInCart(i);
                        }
                    }
                    else{
                         if(result.equals(obj.getItem(i).getProductID()+","+obj.getItem(i).getProductName()+","+((Clothings)obj.getItem(i)).getSize()+","+((Clothings)obj.getItem(i)).getColour()))
                        {
                            for(int j=0;j<WestminsterShoppingManager.productList.size();j++){
                                
                                if(obj.getItem(i).getProductID().equals(WestminsterShoppingManager.productList.get(j).productID)){
                                
                                int number=WestminsterShoppingManager.productList.get(j).getAvailableItems();
                                int number2=obj.getQuantity(obj.getItem(i).getProductID());
                                WestminsterShoppingManager.productList.get(j).setAvailableItems(number+number2);
                                }
                            
                            
                            }
                            
                             for(int j=0;j<MainPageGUI.shopinCartArray.size();j++){
                            
                            if(obj.getItem(i).getProductID().equals(MainPageGUI.shopinCartArray.get(j))){
                            
                            MainPageGUI.shopinCartArray.remove(j);
                            j=j-1;
                            
                            }
                            
                            
                            }
                             obj.clearQuantityItems(obj.getItem(i).getProductID());
                                     obj.removeItemsInCart(i);
                        }
                    }
                }
                //--------------------------------------------------------------------------------------------------
            p2.removeAll();// After adding the details to the system, the product is deleted from the cart   

             //Re Createing shoping cart table after deleting product----------------------------------------------------------------------------------------------------
            String[][] data=new String[obj.itemsSize()][3];
            int size=obj.itemsSize();
            String details;
            for (int i=0;i<size;i++)
            {   //Adding Electronic product details to the row----------------------------------------------------
                if(obj.getItem(i) instanceof Electronics)
                { 
                    details=obj.getItem(i).getProductID()+","+obj.getItem(i).getProductName()+","+((Electronics)obj.getItem(i)).getBrand()+","+((Electronics)obj.getItem(i)).getWarrantyPeriod();            
                }
                //Adding Clothing product details to the row----------------------------------------------------
                else
                { 
                    details=obj.getItem(i).getProductID()+","+obj.getItem(i).getProductName()+","+((Clothings)obj.getItem(i)).getSize()+","+((Clothings)obj.getItem(i)).getColour();             
                }

               int q=obj.getQuantity(obj.getItem(i).getProductID());
                String quantity=Integer.toString(q);
                double price=obj.getItem(i).getPrice()*q;
                String priceString=Double.toString(price);
                data[i]=new String[]{details,quantity,priceString};//Createing row
                //-------------------------------------------------------------------------------------------------
            }

                String[] colummnName={"Product","Quantity","Price"};
                table2=new JTable(data,colummnName);//Createing table
                p2.add(new JScrollPane(table2));   

                //Calculate the total value,calculate the discounts and calculate the final total value-----------------------------------------------------
                double total = 0 ;

                     //Calculate the tatal value---------------------------------------------
                    for (int i = 0; i < obj.itemsSize(); i++) 
                    {
                        String stringValue = (String) table2.getValueAt(i, 2);
                        double price=Double.parseDouble(stringValue);        
                        total=total+price;  
                    }
                    String stringTotal=Double.toString(total);
                    l1.setText("Total-                                                                 "+stringTotal);//Display total
                    //----------------------------------------------------------------------

                    //Calculating buys at least three products of same category discount----
                    double discount1=0; 
                    int eItems=0;
                    int cItems=0;
                    for(int i=0;i<obj.itemsSize();i++)
                    {                       
                        if(obj.getItem(i) instanceof Electronics)
                        {            
                            eItems=eItems+obj.getQuantity(obj.getItem(i).getProductID());
                        }
                        else
                        {
                            cItems=cItems+obj.getQuantity(obj.getItem(i).getProductID());
                        }
                    }

                    if((eItems>=3 || cItems>=3)||(eItems>=3 && cItems>=3))
                    {
                         discount1=(total/100)*20;
                    }
                    String stringDiscount1=Double.toString(discount1);
                    l3.setText("Three Items in same Category Discount-    "+stringDiscount1);//Display discount
                    //----------------------------------------------------------------------

                    //Checking whether the customer who buys the items has bought the items from the system before---------------------
                    int customerCheck=0;       
                    for(int i=0;i<DataBase.customerSave.size();i++)
                    {
                        String newCustomer=WestminsterShoppingManager.oldUsers.get(0).getPassword()+WestminsterShoppingManager.oldUsers.get(0).getUserName();
                        String oldCustomer=DataBase.customerSave.get(i).getPassword()+DataBase.customerSave.get(i).getUserName();
                        if (newCustomer.equals(oldCustomer)) 
                        {              
                           customerCheck=1;                                            
                        }       
                    }
                    //--------------------------------------------------------------------------------------------------------------

                    //Calculating first purchase discount--------------------------------------------------------------------------
                    double discount2=0;
                    if(customerCheck==0)
                    {
                        discount2=(total/100)*10;       
                    }
                    String stringDiscount2=Double.toString(discount2);
                    l2.setText("First Purchsde Discount-                               "+stringDiscount2);
                    //-------------------------------------------------------------------------------------------------------------

                    ShoppingCart obj=new ShoppingCart();
                    double finalTotal=obj.total(total, discount1, discount2);//Calculating final total

                    String stringFianlTotal=Double.toString(finalTotal);
                    l4.setText("Final Total-                                                       "+stringFianlTotal);
                    //------------------------------------------------------------------------------------------------------------------------
            }
            
            //Open shopinCart GUI---------------------------------------
            if(e.getSource()==b2)
            {               
                obj.clearShopingCart();
                obj.clearProductQuantity();
                MainPageGUI.shopinCartArray.clear();
                DataBase.customerSave.add(WestminsterShoppingManager.oldUsers.get(0));                
                dispose(); 
            }            
        }
    }    
}
