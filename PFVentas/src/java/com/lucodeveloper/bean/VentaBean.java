
package com.lucodeveloper.bean;

import com.lucodeveloper.dao.VentaDAO;
import com.lucodeveloper.model.DetalleVenta;
import com.lucodeveloper.model.Producto;
import com.lucodeveloper.model.Venta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lucodeveloper
 */


@ManagedBean
@ViewScoped
public class VentaBean {
    
    private Venta venta = new Venta();
    private Producto producto = new Producto();
    private int cantidad;
    private List<DetalleVenta> lista = new ArrayList();

    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public void agregar(){
        DetalleVenta det = new DetalleVenta();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        this.lista.add(det);
               
    }
    
    public void registrar() throws Exception{
        VentaDAO dao;
        double monto = 0;
        try {
            
            for(DetalleVenta det : lista){
                monto += det.getProducto().getPrecio();
            }
            
            dao = new VentaDAO();
            venta.setMonto(monto);
            dao.registrar(venta, lista);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Exito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Error"));
        }finally{
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        
    }
    
    
}
