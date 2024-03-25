
import org.junit.Test;
import static org.junit.Assert.*;

public class WestminsterShoppingManagerTest {
    
    
    @Test
    public void testAddE() {
        WestminsterShoppingManager obj=new WestminsterShoppingManager();
        String result= obj.addElectronic(new Electronics("1234", "akg", 5, 1200, "AKG", 2));
        assertEquals("Add", result);
    }
    
     @Test
    public void testAddC() {
        WestminsterShoppingManager obj=new WestminsterShoppingManager();
        String result= obj.addElectronic(new Clothings("12345", "Tshirt", 25, 1200, "M", "red"));
        assertEquals("Add", result);
    }

    
    @Test
    public void testDeleteProduct() {
        
        WestminsterShoppingManager obj=new WestminsterShoppingManager();
        obj.addElectronic(new Electronics("123467", "akg", 5, 1200, "AKG", 2));
        String result = obj.deleteProduct("123467");
        assertEquals("deleted", result);
    }

    
    @Test
    public void testPrintProducts() {
        WestminsterShoppingManager obj=new WestminsterShoppingManager();
        String result = obj.printProducts();
        assertEquals("priented", result);
    }

    
     @Test
    public void testOpenGUI() {
        WestminsterShoppingManager obj =new WestminsterShoppingManager();
        int result=obj.openGUI(new User("pabasara", "12345"));
         assertTrue( result>0);
    }
    
    @Test
    public void testSaveProduct() {
        WestminsterShoppingManager obj=new WestminsterShoppingManager();
        obj.addElectronic(new Electronics("123467", "akg", 5, 1200, "AKG", 2));
        obj.openGUI(new User("pabasara", "12345"));
        String result=obj.saveProduct();
        assertEquals("productSaveuserSave", result);
    }

    
    @Test
    public void testLoadProduct() {
        WestminsterShoppingManager obj=new WestminsterShoppingManager();
        String result=obj.loadProduct();
        assertEquals("loadProductloadUser", result);
    }

    
   
    
}
