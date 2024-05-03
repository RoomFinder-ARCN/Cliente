package arcn.roomfinder.cliente.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
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
        if(cliente == null) throw new RoomFinderException("El cliente no puede ser null");
        return clienteRepositorio.crearCliente(cliente);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.obtenerTodosLosClientes();
    }

    @Override
    public Cliente consultarClientePorCorreo(String correo) throws RoomFinderException {
        if (correo == null || correo.equals("")) throw new RoomFinderException("El correo no puede ser null o vacio");
        return clienteRepositorio.consultarClientePorCorreo(correo);
    }

    @Override
    public CuentaBancaria crearCuentaBancaria(CuentaBancaria cuentaBancaria, String correo) throws RoomFinderException {
        if (cuentaBancaria == null || correo == null || correo.equals("")) throw new RoomFinderException("Los datos no pueden ser nulos o vacios");
        return clienteRepositorio.crearCuentaBancaria(cuentaBancaria, correo);
    }

    @Override
    public void eliminarClientePorCorreo(String correo) throws RoomFinderException {
        if (correo == null || correo.equals("")) throw new RoomFinderException("El correo no puede ser null o vacio");
        clienteRepositorio.eliminarClientePorCorreo(correo);
    }
    
    
}
