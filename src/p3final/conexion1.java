/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p3final;

import javax.swing.JOptionPane;
import org.neo4j.driver.internal.spi.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Samuel GR
 */
public class conexion1 {
    
       
       private final String USER = "neo4j";
       private final String PASSWORD = "55431393";
        
           public Connection conexion () throws SQLException 
           {
    
             Connection  c = null;
              try{
                      Class.forName("org.neo4j.jdbc.Driver");
                      c = (Connection) DriverManager.getConnection("jdbc:neo4j:http://localhost:7474",USER,PASSWORD);
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,e);
    }
    return c;
}
                     
}
