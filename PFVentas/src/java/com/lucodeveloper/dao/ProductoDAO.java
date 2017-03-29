
package com.lucodeveloper.dao;

import com.lucodeveloper.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucodeveloper
 */


public class ProductoDAO extends DAO {
    
    public void registrar (Producto pro) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO Producto(nombre, precio) values(?,?)");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    public List<Producto> listar() throws Exception{
        List<Producto> lista;
        ResultSet rs;
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareCall("SELECT codigo, nombre, precio FROM Producto");
            rs = st.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                Producto pro = new Producto();
                pro.setCodigo(rs.getInt("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(rs.getDouble("precio"));
                
                lista.add(pro);
            }
            
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
        return lista;
    }
    
    public Producto leerID(Producto per) throws Exception{
        Producto prods = null;
        ResultSet rs;
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, precio FROM Producto WHERE codigo = ? ");
            st.setInt(1, per.getCodigo());
            rs = st.executeQuery();
            while(rs.next()){
                prods = new Producto();
                prods.setCodigo(rs.getInt("codigo"));
                prods.setNombre(rs.getString("nombre"));
                prods.setPrecio(rs.getDouble("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
        return prods;
    }
    
    public void modificar(Producto pro) throws Exception {
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE Producto SET nombre = ?, precio = ? WHERE codigo = ? ");
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.setInt(3, pro.getCodigo());
            st.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
    }
    
    public void eliminar(Producto pro) throws Exception {
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE Producto WHERE codigo = ? ");
            st.setInt(1, pro.getCodigo());
            st.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
    }    
}
