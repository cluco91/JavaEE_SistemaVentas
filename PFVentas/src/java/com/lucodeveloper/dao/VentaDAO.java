package com.lucodeveloper.dao;

import com.lucodeveloper.model.DetalleVenta;
import com.lucodeveloper.model.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author lucodeveloper
 */

public class VentaDAO extends DAO {

    public void registrar(Venta venta, List<DetalleVenta> lista) throws Exception {
        try {
            this.Conectar();
            this.getCn().setAutoCommit(false);
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO Venta(fecha, codPersona, monto) values(?,?,?)");
            st.setDate(1, venta.getFecha());
            st.setInt(2, venta.getPersona().getCodigo());
            st.setDouble(3, venta.getMonto());
            st.executeUpdate();
            st.close();
            
            PreparedStatement st2 = this.getCn().prepareStatement("SELECT LAST_INSERT_ID() FROM venta limit 1");
            ResultSet rs;
            int codVenta = 0;
            rs = st.executeQuery();
            while(rs.next()){
                codVenta = rs.getInt(1);
            }
            rs.close();
            
            for (DetalleVenta det : lista) {
                PreparedStatement st3 = this.getCn().prepareStatement("INSERT INTO DetalleVenta(codVenta, codProducto, cantidad) values(?,?,?)");
                st3.setInt(1, codVenta);
                st3.setInt(2, det.getProducto().getCodigo());
                st3.setInt(3, det.getCantidad());
                st3.executeUpdate();
                st3.close();
            }
            this.getCn().commit();
        } catch (Exception e) {
            this.getCn().rollback();
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
