package arcn.roomfinder.cliente.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import arcn.roomfinder.cliente.domain.repository.ClienteRepositorio;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    public ClienteServicioImpl(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) throws RoomFinderException {
       var tipoDocumento = TipoDocumento.valueOf(cliente.get("tipoDocumento"));
       var nuevoCliente = new Cliente(
           cliente.get("nombre"),
           tipoDocumento,
           cliente.get("numeroDocumento"),
           cliente.get("correo"),
           new CuentaBancaria(cliente.get("numeroCuenta"), cliente.get("cantidadCredito"))
       );
       return clienteRepositorio.crearCliente(nuevoCliente);
    }
    
}
