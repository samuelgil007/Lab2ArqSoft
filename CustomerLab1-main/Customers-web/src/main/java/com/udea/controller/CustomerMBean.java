/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.entity.Transaccion;
import com.udea.session.TransaccionFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Usuario
 */
public class CustomerMBean implements Serializable {

    @EJB
    private TransaccionFacadeLocal transaccionFacade;


//Propiedades del modelo 

    private Transaccion transaccion; //para mostrar, actualizar o insertar en el formulario
    private List<Transaccion> transacciones; //para visualizar en la tabla

    public CustomerMBean() {
    }


    public List<Transaccion> getTransacciones() {
        if ((transacciones == null) || (transacciones.isEmpty())) {
            refresh();
        }
        return transacciones;
    }
//Retorna el detalle de cada cliente en el formulario. 

    /**
     * Action handler - llamado cuando en una fila de la tabla se haga click en
     * ID
     *
     * @param customer
     * @return
     */

    /**
     * Action handler - Actualiza el modelo Customer en la BD. Se llama cuando
     * se presiona el boton update del formulario
     *
     * @return
     */

    /**
     * Action handler - returno hacia la vista de la lista de clientes
     *
     * @return
     */
    public String list() {
        transacciones = transaccionFacade.findAll();
        return "LIST";
    }

    public String index() {
        return "index";
    }
    public String transac() {
        return "transaccion";
    }
    
    private void refresh() {
        transacciones = transaccionFacade.findAll();
    }

}
