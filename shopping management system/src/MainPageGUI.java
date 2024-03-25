
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MainPageGUI extends JFrame{

    public static ArrayList<String>shopinCartArray=new ArrayList(); //Used to store product ID of  products to be added to cart  
    public static ArrayList<String>lowQuantityProductID=new ArrayList();//Used to store the ID of products less than size three

    static int All_or_E_or_C=0; //This is used to store whether the display should be a table containing all products, a table containing only electronics, or a table containing clothing products
   
    JPanel p1,p2,p3,p4,p5;                //panels
    JComboBox box;                        //comboBox
    JTable table1;                        //tables
    JButton b1,b2,b3,b4,b5;                  //buttons
    JLabel l1, l2,l3,l4,l5,l6,l7,l8,l9;   //labels
    
    
    public MainPageGUI (){
        //Creating tha comboBox-------------------------------------------------        
        String[] type={"All","Electronics","Clothing"};
        box=new JComboBox<>(type);
               
        //Creating the  all Product table------------------------------------------------------------------------------------------------------------------------------             
        int quantity=WestminsterShoppingManager.productList.size();//The number of products in the arraylist in which the products are stored        
   
        Object[][] data=new Object[quantity][5];  //Create table rows count and coloum count(Creating the array associated with the table)
        
        //Loading table row details--------------------------------------------------------------------------
        for(int i=0;i<quantity;i++)
        {
             if(WestminsterShoppingManager.productList.get(i) instanceof Electronics )
             {
                data[i] = new Object[]{WestminsterShoppingManager.productList.get(i).getProductID(), WestminsterShoppingManager.productList.get(i).getProductName(), "Electronic",WestminsterShoppingManager.productList.get(i).getPrice(),((Electronics) WestminsterShoppingManager.productList.get(i)).getBrand()+","+((Electronics)WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod()};
             }
             else
             {
                 data[i] = new Object[]{WestminsterShoppingManager.productList.get(i).getProductID(), WestminsterShoppingManager.productList.get(i).getProductName(), "Clothing",WestminsterShoppingManager.productList.get(i).getPrice(),((Clothings) WestminsterShoppingManager.productList.get(i)).getSize()+","+((Clothings)WestminsterShoppingManager.productList.get(i)).getColour()};
             }
        }      
        //------------------------------------------------------------------------------------------------------
        
        String[] colummnName={"Product ID","Name","Category","Price(RS)","Info"};
        
        table1=new JTable(data,colummnName);//Creating the table
        
       //The part shown in red in the product table where the quantity is less than three-----------------------
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() 
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //Quntity is less than three product search---------------------
                lowQuantityProductID.clear();
                for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                {        
                    if(WestminsterShoppingManager.productList.get(i).getAvailableItems()<3)
                    {
                        lowQuantityProductID.add(WestminsterShoppingManager.productList.get(i).getProductID());            
                    }       
                }
                //--------------------------------------------------------------

               String quantityValue = table.getValueAt(row, 0).toString();
               //Set row colour-------------------------------------------------
                if (lowQuantityProductID.contains(quantityValue)) {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE); 
                } else {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
                    return c;
            }
            });
        //------------------------------------------------------------------------------------------------------------------
        
        table1.setAutoCreateRowSorter(true);//Sorting the table
        //---------------------------------------------------------------------------------------------------------------------------------------------------------
            
            //Creatin labels----------------------
            l1=new JLabel();
            l2=new JLabel();
            l3=new JLabel();
            l4=new JLabel();
            l5=new JLabel();
            l6=new JLabel();
            l7=new JLabel();
            l8=new JLabel();
            l9=new JLabel();
        
            //Creating buttons----------------------
            b1=new JButton("add to Shopping cart");
            b2=new JButton("Shopping cart");
            b3=new JButton("Sort aiphabetical order");
            b4=new JButton("Sort as before");
	    b5=new JButton("Exit");
            
            //Creating panels-----------------------
            p1=new JPanel();
            p2=new JPanel();
            p3=new JPanel();
            p4=new JPanel();
            p5=new JPanel();
            
         
            //Seting panels size------------------------
            p1.setPreferredSize(new Dimension(200,50));
            p2.setPreferredSize(new Dimension(200,80));
            p3.setPreferredSize(new Dimension(200,350));
            
            //Adding panels in fram---------------------
            add(p1,BorderLayout.NORTH);
            add(p2,BorderLayout.CENTER);
            add(p3,BorderLayout.SOUTH);
            
            //Seting panels lay out type----------------
            p1.setLayout(new FlowLayout());
            p3.setLayout(new FlowLayout());   
            p4.setLayout(new GridLayout(9,1));
           
            //Adding things to the p1 panel-------------
            p1.add(box);
            p1.add(b2);
            p1.add(l8);
            p1.add(l9);

            //Adding things to the p2 panel-------------
            p2.add(new JScrollPane(table1));
            
            //Adding things to the p3 panel-------------
            p3.add(p4,BorderLayout.CENTER);
            
            //Adding things to the p4 panel-------------
            p4.add(l1);
            p4.add(l2);
            p4.add(l3);
            p4.add(l4);
            p4.add(l5);
            p4.add(l6);
            p4.add(l7);
            p4.add(p5);
	    p4.add(b5);
  
            //Adding things to the p5 panel-------------
            p5.add(b1);
            
            //Adding actions-----------------------------
            ControllerClass control=new ControllerClass();
            table1.addMouseListener(control);
            b1.addActionListener(control);
            b2.addActionListener(control);
            b3.addActionListener(control);
            b4.addActionListener(control);
	    b5.addActionListener(control);
            box.addActionListener(control);
    }       
    
    private class ControllerClass extends MouseAdapter implements ActionListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //Section to make row's data appear below when clicked on row------------------------------------------------------------------------------
            if(e.getSource()==table1)
            {
                int row = table1.getSelectedRow();//Identifing the selected row
                String selectedProductID = (String) table1.getValueAt(row, 0);//Identifying the id of the product in the selected row
                
                //Displaying select product details-------------------------------------------
                for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                {
                    if(selectedProductID.equals(WestminsterShoppingManager.productList.get(i).getProductID()))//Searching in the product list for a product that matches the select product
                    {       //Displaying product details-------------------------------------------------------------------------------                
                            l1.setText("**Selected product details");
                            l2.setText("          Product ID : "+WestminsterShoppingManager.productList.get(i).getProductID());
                            
                            l4.setText("          Name : "+WestminsterShoppingManager.productList.get(i).getProductName());
                            
                            if(WestminsterShoppingManager.productList.get(i) instanceof Electronics )
                            {
                                l3.setText("          Category : Electronic");
                                l5.setText("          Brand : "+((Electronics)WestminsterShoppingManager.productList.get(i)).getBrand());
                                String convertString1=Integer.toString(((Electronics)WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod());
                                l6.setText("          Warranty period : "+convertString1);
                            }
                            else
                            {
                                l3.setText("          Category : Clothing");
                                l5.setText("          Size : "+((Clothings)WestminsterShoppingManager.productList.get(i)).getSize());
                                l6.setText("          Colour : "+((Clothings)WestminsterShoppingManager.productList.get(i)).getColour());
                            }
                            
                            String convertString2=Integer.toString(WestminsterShoppingManager.productList.get(i).getAvailableItems());
                            l7.setText("          Items Available : "+convertString2);
                    }       //-------------------------------------------------------------------------------------------------------------------
                }
            }   //------------------------------------------------------------------------------
        }   //----------------------------------------------------------------------------------------------------------------------------------------
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //By this section changes the displayed product in the table(All items or Electronics items or Clothing items)------------------------------------------------
            if(e.getSource()==box)
            {                
                //The part that identifies what to display-----------------------
                All_or_E_or_C=box.getSelectedIndex();
                String type=Integer.toString(All_or_E_or_C);
                l8.setText(type);
                //---------------------------------------------------------------

                int quantity=WestminsterShoppingManager.productList.size();//The size of the arrayList in which the product are stored
               
                
                //The section that displays all product------------------------------------------------------------------
                if(All_or_E_or_C==0)
                {
                    p2.removeAll();//Remove curent table
                                        
                    Object[][] data=new Object[quantity][5];//Create table rows count and coloum count(Creating the array associated with the table)
                    for(int i=0;i<quantity;i++)
                    {
                        if(WestminsterShoppingManager.productList.get(i) instanceof Electronics )
                        {
                            data[i] = new Object[]{WestminsterShoppingManager.productList.get(i).getProductID(), WestminsterShoppingManager.productList.get(i).getProductName(), "Electronic",WestminsterShoppingManager.productList.get(i).getPrice(),((Electronics) WestminsterShoppingManager.productList.get(i)).getBrand()+","+((Electronics)WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod()};
                        }
                         else
                        {
                            data[i] = new Object[]{WestminsterShoppingManager.productList.get(i).getProductID(), WestminsterShoppingManager.productList.get(i).getProductName(), "Clothing",WestminsterShoppingManager.productList.get(i).getPrice(),((Clothings) WestminsterShoppingManager.productList.get(i)).getSize()+","+((Clothings)WestminsterShoppingManager.productList.get(i)).getColour()};
                        }
                    }
                   
                    String[] colummnName={"Product ID","Name","Category","Price","Infor"};

                    table1=new JTable(data,colummnName);//Creat new table
                    //The part shown in red in the product table where the quantity is less than three-----------------------
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() 
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //Quntity is less than three product search---------------------
                lowQuantityProductID.clear();
                for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                {        
                    if(WestminsterShoppingManager.productList.get(i).getAvailableItems()<3)
                    {
                        lowQuantityProductID.add(WestminsterShoppingManager.productList.get(i).getProductID());            
                    }       
                }
                //--------------------------------------------------------------

               String quantityValue = table.getValueAt(row, 0).toString();
               //Set row colour-------------------------------------------------
                if (lowQuantityProductID.contains(quantityValue)) {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE); 
                } else {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
                    return c;
            }
            });
                    table1.setAutoCreateRowSorter(true);//Sorting the table
                    p2.add(new JScrollPane(table1));//Set new table
                }
                //-----------------------------------------------------------------------------------------------------
                
                //The section that displays Electronics items----------------------------------------------------------
                else if(All_or_E_or_C==1)
                {
                    int quantity1=0;
                    for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                    {
                        if(WestminsterShoppingManager.productList.get(i) instanceof Electronics )
                        {
                            quantity1=quantity1+1;
                        }
                    }
                    p2.removeAll();//Remove curent table
                    int place=0;//This variable is used to add the product to the specified position in the arrayList corresponding to the table
                    
                    Object[][] data=new Object[quantity1][5];
                    for(int i=0;i<quantity;i++)
                    {
                        if(WestminsterShoppingManager.productList.get(i) instanceof Electronics )
                        {
                            place=place+1;
                            data[place-1] = new Object[]{WestminsterShoppingManager.productList.get(i).getProductID(), WestminsterShoppingManager.productList.get(i).getProductName(), "Electronic",WestminsterShoppingManager.productList.get(i).getPrice(),((Electronics) WestminsterShoppingManager.productList.get(i)).getBrand()+","+((Electronics)WestminsterShoppingManager.productList.get(i)).getWarrantyPeriod()};
                        }
                    }
                    String[] colummnName={"Product ID","Name","Category","Price","Infor"};

                    table1=new JTable(data,colummnName);//Creat new table
                    //The part shown in red in the product table where the quantity is less than three-----------------------
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() 
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //Quntity is less than three product search---------------------
                lowQuantityProductID.clear();
                for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                {        
                    if(WestminsterShoppingManager.productList.get(i).getAvailableItems()<3)
                    {
                        lowQuantityProductID.add(WestminsterShoppingManager.productList.get(i).getProductID());            
                    }       
                }
                //--------------------------------------------------------------

               String quantityValue = table.getValueAt(row, 0).toString();
               //Set row colour-------------------------------------------------
                if (lowQuantityProductID.contains(quantityValue)) {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE); 
                } else {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
                    return c;
            }
            });
                    table1.setAutoCreateRowSorter(true);//Sorting the table
                    p2.add(new JScrollPane(table1));//Set new table              
                }
                //-------------------------------------------------------------------------------------------------------
                
                //The section that displays Clothing items---------------------------------------------------------------
                else if(All_or_E_or_C==2)
                {  
                    int quantity1=0;
                    for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                    {
                        if(WestminsterShoppingManager.productList.get(i) instanceof Clothings )
                        {
                            quantity1=quantity1+1;
                        }
                    }
                    p2.removeAll();//Remove curent table
                    int place=0;//This variable is used to add the product to the specified position in the array corresponding to the table
                    
                    Object[][] data=new Object[quantity1][5];
                    for(int i=0;i<quantity;i++)
                    {
                        if(WestminsterShoppingManager.productList.get(i) instanceof Clothings )
                        {
                            place=place+1;
                            data[place-1] = new Object[]{WestminsterShoppingManager.productList.get(i).getProductID(), WestminsterShoppingManager.productList.get(i).getProductName(), "Clothing",WestminsterShoppingManager.productList.get(i).getPrice(),((Clothings) WestminsterShoppingManager.productList.get(i)).getSize()+","+((Clothings)WestminsterShoppingManager.productList.get(i)).getColour()};
                        }
                    }
                    String[] colummnName={"Product ID","Name","Category","Price","Infor"};

                    table1=new JTable(data,colummnName);//Creat new table
                    //The part shown in red in the product table where the quantity is less than three-----------------------
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() 
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //Quntity is less than three product search---------------------
                lowQuantityProductID.clear();
                for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                {        
                    if(WestminsterShoppingManager.productList.get(i).getAvailableItems()<3)
                    {
                        lowQuantityProductID.add(WestminsterShoppingManager.productList.get(i).getProductID());            
                    }       
                }
                //--------------------------------------------------------------

               String quantityValue = table.getValueAt(row, 0).toString();
               //Set row colour-------------------------------------------------
                if (lowQuantityProductID.contains(quantityValue)) {
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE); 
                } else {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
                    return c;
            }
            });
                    table1.setAutoCreateRowSorter(true);//Sorting the table
                    p2.add(new JScrollPane(table1));//Set new table
                }
                //--------------------------------------------------------------------------------------------------------

                table1.addMouseListener(this); //Adding MouseListener to the newly created table          
            }
            
            //The section where items are added to the shoping cart-------------------------------------------------------
            if(e.getSource()==b1)
            {
                l9.setText("");

                    //Calculates the availabil items of the item added to the cart----------------------------------------------
                    for(int i=0;i<WestminsterShoppingManager.productList.size();i++)
                    {
                        if(l2.getText().substring(23).equals(WestminsterShoppingManager.productList.get(i).productID))
                        {   
                            int quntaty=WestminsterShoppingManager.productList.get(i).getAvailableItems();                            
                            
                            if(WestminsterShoppingManager.productList.get(i).getAvailableItems()>0)
                            {
                                shopinCartArray.add(l2.getText().substring(23));//Adding products to cart
                                //When one quantity of productis added to the cart,the value is subtracted from that value system---
                                WestminsterShoppingManager.productList.get(i).setAvailableItems(quntaty-1);
                            }
                            else{l9.setText("Now quantity of this item is '0'.Therefore it cannot be added to the cart");}   
                        } 
                    }
            }

	   if(e.getSource()==b5){dispose();}
                    
            //Opening Cart GUI-------------------------------------------------
            if (e.getSource()==b2)
            {
                CartGUI fram=new CartGUI();
                fram.setTitle("new gui2");
                fram.setSize(400, 400);
                fram.getContentPane().setBackground(Color.white);
                fram.setVisible(true);
            }
            //------------------------------------------------------------------
        }
    }
}
