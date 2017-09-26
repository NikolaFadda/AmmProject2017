/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.classi;
import static java.lang.System.out;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nikola
 */
public class Validate {
    //Metodo per verificare se Username e Password sono corretti
    public static boolean checkUser(String username, String password)
    {
    
    boolean st = false;
    try{
    
    //Carico i driver del db
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    
    String db = "jdbc:derby://localhost:1527/ammdb";
    Connection conn = DriverManager.getConnection(db,"amm","amm");
    if (conn != null){
        System.out.println("Connesso al DB per Check User e Password");
    }
    
    PreparedStatement ps = conn.prepareStatement("select * from utenti where userid=? and password=?");
    
    ps.setString(1, username);
    ps.setString(2, password);
    ResultSet rs = ps.executeQuery();
    st = rs.next();
    
    //Connetto al db
    
    } catch(Exception ex){
    
            ex.printStackTrace();
    }
    
     return st;
    
    }
    
    //Metodo per verificare se uno Username è già stato usato
    public static boolean checkFreeUserName(String username){
    
        boolean st = false;
    try{
    
    //Carico i driver del db
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    
    String db = "jdbc:derby://localhost:1527/ammdb";
    Connection conn = DriverManager.getConnection(db,"amm","amm");
    if (conn != null){
        System.out.println("Connesso al DB per check Username libero");
    }
    
    PreparedStatement ps = conn.prepareStatement("select * from utenti where userid=?");
    
    ps.setString(1, username);
    ResultSet rs = ps.executeQuery();
    st = rs.next();
    
    conn.close();
    
    //Connetto al db
    
    } catch(Exception ex){
    
            ex.printStackTrace();
    }
        st = !(st);
     return st;
    }
    
    public static boolean areFriends(String friend1, String friend2){
    
                boolean st = false;
    try{
    
    //Carico i driver del db
    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    
    String db = "jdbc:derby://localhost:1527/ammdb";
    Connection conn = DriverManager.getConnection(db,"amm","amm");
    if (conn != null){
        System.out.println("Connesso al DB per check Amicizie");
    }
    
    if(friend1 == null ? friend2 == null : friend1.equals(friend2)){return true;}
    
    
    PreparedStatement ps = conn.prepareStatement("select * from friendship where"+
                                                    " friend1= ?  AND friend2= ? ");
    
    ps.setString(1, friend1);
    ps.setString(2, friend2);
    ResultSet rs = ps.executeQuery();
    st = rs.next();
    
    conn.close();
    
    //Connetto al db
    
    } catch(Exception ex){
    
            ex.printStackTrace();
    }
     
     
    return st;
    }
}