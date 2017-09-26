/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nikola
 * 
 * 
 */
package amm.classi;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;



public class UtenteFactory {
    
    private static UtenteFactory singleton;
    
    public static UtenteFactory getInstance(){
        if(singleton == null){
            singleton = new UtenteFactory();
        }
        return singleton;
    }
    
    //Database
    private String connString;
    private String db = "jdbc:derby://localhost:1527/ammdb";
    
    public void setConnString(String s){ this.connString = s;}
    public String getConnString(){return this.connString;}
    
    private UtenteFactory(){
    }
    
    public Utente getUtenteByUsername(String user) {
    try{
        
        
        Connection conn = DriverManager.getConnection(db,"amm","amm");
       
    
    String db = "jdbc:derby://localhost:1527/ammdb";
    conn = DriverManager.getConnection(db,"amm","amm");
    if (conn != null){
        System.out.println("Connesso al DB per GetUtenteByUsername");
    }
        
        String query = "select * from utenti " + 
                        "where userid = ?";
        
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user);
        ResultSet res = stmt.executeQuery();
        
        if (res.next()){
            Utente current = new Utente();
            current.setUsername(res.getString("userid"));
            current.setNome(res.getString("nome"));
            current.setCognome(res.getString("cognome"));
            current.setQuote(res.getString("quote"));
            current.setPropic(res.getString("propic"));
            
            stmt.close();
            conn.close();
            return current;
        }
    
        stmt.close();
        conn.close();
    } catch (SQLException e){
        e.printStackTrace();
    }
        return null;
    }
    
    public void addUtente(String username, String password, String nome,
            String cognome, String quote, String propic){
        
        try{
        
        
        Connection conn = DriverManager.getConnection(db,"amm","amm");
       
    
    String db = "jdbc:derby://localhost:1527/ammdb";
    conn = DriverManager.getConnection(db,"amm","amm");
    if (conn != null){
        System.out.println("Connesso al DB per aggiungere un utente");
    }
    
    String query = 
                      "insert into utenti (userid, password, nome, cognome, quote, propic) "
                    + "values ( ? , ? , ? , ? , ? , ? )";
    
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, username);
    stmt.setString(2, password);
    stmt.setString(3, nome);
    stmt.setString(4, cognome);
    stmt.setString(5, quote);
    stmt.setString(6, propic);
    
    stmt.executeUpdate();
    
    
    }catch(SQLException e){
            e.printStackTrace();
        }
        
        return;
    }
    
    public void alterPassword (String user, String newPassword){
            
                try{
        
        
        Connection conn = DriverManager.getConnection(db,"amm","amm");
       
    
    String db = "jdbc:derby://localhost:1527/ammdb";
    conn = DriverManager.getConnection(db,"amm","amm");
    if (conn != null){
        System.out.println("Connesso al DB per cambiare la password");
    }
    
    String query = 
                      "update utenti "+
                      "set password = ? "
                    + "where userid = ? ";
    
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, newPassword);
    stmt.setString(2, user);
    
    
    stmt.executeUpdate();
    
    
    }catch(SQLException e){
            e.printStackTrace();
        }
    
    
    }   
    
    public List getUtentiList (){
        List<Utente> listaUtenti = new ArrayList<Utente>();
        
        try {
            // path, username, password
            Connection conn = DriverManager.getConnection(db, "amm", "amm");
            

            String query = 
                      "select * from utenti";
            
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();

            // ciclo sulle righe restituite
            while (res.next()) {
                Utente current = new Utente();
                current.setUsername(res.getString("userid"));
                current.setNome(res.getString("nome"));
                current.setCognome(res.getString("cognome"));
                current.setQuote(res.getString("quote"));
                current.setPropic(res.getString("propic"));
                
                listaUtenti.add(current);
                
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaUtenti;
    
    
    }
    
    public void setFriendship (String user1, String user2){
    
                        try{
        
        
        Connection conn = DriverManager.getConnection(db,"amm","amm");
       
    
    String db = "jdbc:derby://localhost:1527/ammdb";
    conn = DriverManager.getConnection(db,"amm","amm");
    if (conn != null){
        System.out.println("Connesso al DB per stringere un'amicizia");
    }
    
    String query = 
                      "insert into friendship (friend1, friend2) "+
                      "values ( ? , ? )";
    
    PreparedStatement stmt = conn.prepareStatement(query);
    stmt.setString(1, user1);
    stmt.setString(2, user2);
    
    
    stmt.executeUpdate();
    
    
    }catch(SQLException e){
            e.printStackTrace();
        }
    
    }
}
