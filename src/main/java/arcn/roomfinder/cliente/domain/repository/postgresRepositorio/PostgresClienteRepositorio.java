package arcn.roomfinder.cliente.domain.repository.postgresRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arcn.roomfinder.cliente.domain.entity.ClienteEntidad;
import arcn.roomfinder.cliente.domain.entity.CuentaBancariaEntidad;
import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import arcn.roomfinder.cliente.domain.repository.ClienteRepositorio;

@Repository
public class PostgresClienteRepositorio implements ClienteRepositorio {
    private PostgresClienteInterface clienteInterface;

    @Autowired
    public PostgresClienteRepositorio(PostgresClienteInterface clienteInterface) {
        this.clienteInterface = clienteInterface;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) throws RoomFinderException {
        if(cliente == null) throw new RoomFinderException("El cliente no puede ser null");
        ClienteEntidad clienteEntidad = new ClienteEntidad();
        clienteEntidad.setCorreo(cliente.getCorreo());
        clienteEntidad.setNombre(cliente.getNombre());
        clienteEntidad.setTipoDocumento(cliente.getTipoDocumento());
        clienteEntidad.setNumeroDocumento(cliente.getNumeroDocumento());        
        clienteEntidad.setCuentaBancariaEntidad(new CuentaBancariaEntidad(cliente.getCuentaBancaria()));

        ClienteEntidad clientResult = clienteInterface.save(clienteEntidad);

        return new Cliente(
            clientResult.getCorreo(), 
            clientResult.getNombre(), 
            clientResult.getTipoDocumento(), 
            clientResult.getNumeroDocumento(),           
            new CuentaBancaria(clientResult.getCuentaBancariaEntidad())
        );
      
    }
    
}
