
package com.lucodeveloper.dao;

import com.lucodeveloper.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucodeveloper
 */


public class PersonaDAO extends DAO {
    
    public void registrar (Persona per) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO Persona(nombre, sexo) values(?,?)");
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    public List<Persona> listar() throws Exception{
        List<Persona> lista;
        ResultSet rs;
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareCall("SELECT codigo, nombre, sexo FROM Persona");
            rs = st.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                Persona per = new Persona();
                per.setCodigo(rs.getInt("codigo"));
                per.setNombre(rs.getString("nombre"));
                per.setSexo(rs.getString("sexo"));
                
                lista.add(per);
            }
            
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
        return lista;
    }
    
    public Persona leerID(Persona per) throws Exception{
        Persona pers = null;
        ResultSet rs;
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, sexo FROM Persona WHERE codigo = ? ");
            st.setInt(1, per.getCodigo());
            rs = st.executeQuery();
            while(rs.next()){
                pers = new Persona();
                pers.setCodigo(rs.getInt("codigo"));
                pers.setNombre(rs.getString("nombre"));
                pers.setSexo(rs.getString("sexo"));
            }
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
        return pers;
    }
    
    public void modificar(Persona per) throws Exception {
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE Persona SET nombre = ?, sexo = ? WHERE codigo = ? ");
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.setInt(3, per.getCodigo());
            st.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
    }
    
    public void eliminar(Persona per) throws Exception {
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE Persona WHERE codigo = ? ");
            st.setInt(1, per.getCodigo());
            st.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally{
            this.Cerrar();
        }
    }    
}
