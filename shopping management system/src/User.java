import java.io.Serializable;

public class User implements Serializable{
    //User details----------------------
    private String userName;
    private String password;
    //----------------------------------

    //User class Constructor------------------------------------------------
    public User(String userName, String password) 
    {
        this.userName = userName;
        this.password = password;
    }
    //----------------------------------------------------------------------
    
    //User class getters and setters----------------------------------------
    public String getUserName() 
    {
        return userName;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    //-----------------------------------------------------------------------
}
