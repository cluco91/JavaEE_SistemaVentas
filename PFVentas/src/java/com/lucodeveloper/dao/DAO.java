
package com.lucodeveloper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucodeveloper
 */


public class DAO {
    
    private Connection cn;
    
    public Connection getCn(){
        return cn;
    }
    
    public void setCn(Connection cn){
        this.cn = cn;
    }
    
    public void Conectar() throws Exception{
        
        try {
            Class.forName("com.mysql.jdbc.Drive");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas?user=root&password=123");            
        } catch (Exception e) {
            throw e;
        } 
    }
    
    public void Cerrar() throws Exception{
        try {
            if (cn != null){
                if (cn.isClosed() == false){
                    cn.close();
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }      
}
