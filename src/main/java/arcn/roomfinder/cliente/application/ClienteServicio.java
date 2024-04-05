package arcn.roomfinder.cliente.application;

import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.exception.RoomFinderException;

public interface ClienteServicio {

    Cliente crearCliente(Cliente cliente) throws RoomFinderException;


    
} 
