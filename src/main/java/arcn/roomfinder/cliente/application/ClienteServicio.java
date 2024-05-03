package arcn.roomfinder.cliente.application;

import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;

import java.util.List;

import arcn.roomfinder.cliente.domain.exception.RoomFinderException;

public interface ClienteServicio {

    Cliente crearCliente(Cliente cliente) throws RoomFinderException;
    List<Cliente> obtenerTodosLosClientes();
    Cliente consultarClientePorCorreo(String correo) throws RoomFinderException;
    CuentaBancaria crearCuentaBancaria(CuentaBancaria cuentaBancaria, String correo) throws RoomFinderException;
    void eliminarClientePorCorreo(String correo) throws RoomFinderException;
    
    
} 
