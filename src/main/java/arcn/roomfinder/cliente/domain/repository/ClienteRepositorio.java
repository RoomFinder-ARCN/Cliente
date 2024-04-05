package arcn.roomfinder.cliente.domain.repository;

import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;

public interface ClienteRepositorio {
    Cliente crearCliente(Cliente cliente) throws RoomFinderException;

    
} 
