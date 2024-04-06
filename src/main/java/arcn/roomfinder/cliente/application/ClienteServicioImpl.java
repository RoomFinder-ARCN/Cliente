package arcn.roomfinder.cliente.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.repository.ClienteRepositorio;


@Service
public class ClienteServicioImpl implements ClienteServicio {
    
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public ClienteServicioImpl(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) throws RoomFinderException {
        return clienteRepositorio.crearCliente(cliente);
    }
    
}
