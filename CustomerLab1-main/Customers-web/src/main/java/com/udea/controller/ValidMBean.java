/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.entity.Transaccion;
import com.udea.session.TransaccionFacadeLocal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 *
 * @author Usuario
 */
public class ValidMBean implements Serializable {

    /**
     * @return the errorExpiracion
     */
    

    @EJB
    private TransaccionFacadeLocal transaccionFacade;



//Propiedades del modelo 
    private Transaccion transaccion; //para mostrar, actualizar o insertar en el formulario
    private List<Transaccion> transacciones; //para visualizar en la tabla

  

//retorna una lista de clientes para mostrar en un datatable de JSF

//Retorna el detalle de cada cliente en el formulario. 


    private String imagen;
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Size(min = 2, max = 10)
    private String id_cliente;

    @Size(min = 2, max = 80)
    private String nom_cliente;
    
    @Pattern(regexp="^(.+)@(.+)$")
    @Size(min = 7, max = 60) 
    private String email_cliente;

    @Size(min = 5, max = 16)
    private String num_tarjeta;

    @Size(min = 3, max = 3)
    private String csv_tarjeta;

    @Size(min = 4, max = 30)
    private String tipo_tarjeta;
    @Pattern(regexp="(0[1-9]|10|11|12)/20[0-9]{2}$")
    @Size(min = 7, max = 7)
    private String expiracion_tarj;

    @Min(500)
    @Max(10000)
    @NotNull
    private Double valor_pago;
    
    private String fecha_pago;
    private String ref_pago;
    
    private String errorExpiracion = "hola";

     public Transaccion getTransaccion() {
        return transaccion;
    }

    public String getErrorExpiracion() {
        return errorExpiracion;
    }

    /**
     * @param errorExpiracion the errorExpiracion to set
     */
    public void setErrorExpiracion(String errorExpiracion) {
        this.errorExpiracion = errorExpiracion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

   
    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    
    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public String getCsv_tarjeta() {
        return csv_tarjeta;
    }

    public void setCsv_tarjeta(String csv_tarjeta) {
        this.csv_tarjeta = csv_tarjeta;
    }

    public String getTipo_tarjeta() {
        return tipo_tarjeta;
    }

    public void setTipo_tarjeta(String tipo_tarjeta) {
        this.tipo_tarjeta = tipo_tarjeta;
    }

    public String getExpiracion_tarj() {
        return expiracion_tarj;
    }

    public void setExpiracion_tarj(String expiracion_tarj) {
        this.expiracion_tarj = expiracion_tarj;
    }

    public Double getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(Double valor_pago) {
        this.valor_pago = valor_pago;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }
    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }
 
    
    //retorna una lista de clientes para mostrar en un datatable de JSF

     public String crearTransaccion(){
     /*se crea fecha actual*/
     
        LocalDateTime locaDate = LocalDateTime.now();
        int day= locaDate.getDayOfMonth();
        int month = locaDate.getMonthValue();
        int year=locaDate.getYear();
        int hours  = locaDate.getHour();
        int minutes = locaDate.getMinute();
        int seconds = locaDate.getSecond();
        /*Se crea Referencia de pago a partir de la fecha y tiempo actual mas id del cliente*/
     ref_pago=day+""+month+""+year+""+hours+""+minutes+""+seconds+id_cliente;
     fecha_pago=day+"-"+month+"-"+year;
     Transaccion trans = new Transaccion();

     trans.setIdUsuario(id_cliente);
     trans.setNomUsuario(nom_cliente);
     trans.setEmailUsuario(email_cliente);
     trans.setNumeroTarjeta(num_tarjeta);
     trans.setCsvTarjeta(csv_tarjeta);
     trans.setExpiracionTarj(expiracion_tarj);
     trans.setTipoTarjeta(tipo_tarjeta);
     trans.setValorPago(valor_pago);
     trans.setRefPago(ref_pago);
     trans.setFechaPago(fecha_pago);
     
     transaccionFacade.create(trans);
     transacciones = transaccionFacade.findAll();
     this.transaccion=trans;
        return "SAVED";
     }
 
     public void handleKeyEvent() {
         
         if (num_tarjeta.length()>=5){
             
             /*obtenemos los primeros 5 digtos de la tarjeta*/
             String str = num_tarjeta.substring(0,5);
             int valor;
             valor = Integer.parseInt(str);
             if (valor >= 11111 && valor<=22222){
                   tipo_tarjeta="AMERICAN EXPRESS";
                   this.imagen="img/american.png";
                   str="";
             }else if(valor >= 33333 && valor<=44444){
                   tipo_tarjeta="DINERS";
                   this.imagen="img/diners.png";
                   str="";
             }else if(valor >= 55555 && valor<=66666){
                   tipo_tarjeta="VISA";
                   this.imagen="img/visa.png";
                   str="";
             }else if(valor >= 77777 && valor<=88888){
                   tipo_tarjeta="MASTERCARD";
                   this.imagen="img/mastercard.png";
                   str="";
             }else{
                    tipo_tarjeta="OTRA";
                   this.imagen="img/otra.png";
                   str="";
             }
             
         }
        
        
     }
     public List<Transaccion> getTransacciones() {
        if ((transacciones == null) || (transacciones.isEmpty())) {
            refresh();
        }
        return transacciones;
    }
   private void refresh() {
        transacciones = transaccionFacade.findAll();
    }
    public String index() {
        transacciones = transaccionFacade.findAll();
        return "index";
    }
     public String list() {
        transacciones = transaccionFacade.findAll();
        return "LIST";
    }
     public String borrar(Transaccion i) {
        transaccionFacade.remove(i);
        transacciones = transaccionFacade.findAll();
       
        return "SAVED";
    }
     

    public ValidMBean() {
    }
       
       
}
