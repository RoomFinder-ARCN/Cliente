package arcn.roomfinder.cliente.domain.repository.postgresRepositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arcn.roomfinder.cliente.domain.entity.ClienteEntidad;
import arcn.roomfinder.cliente.domain.entity.CuentaBancariaEntidad;
import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.repository.ClienteRepositorio;
import jakarta.transaction.Transactional;

@Repository
public class PostgresClienteRepositorio implements ClienteRepositorio {
    private PostgresClienteInterface clienteInterface;
    private PostgresCuentaInterface cuentaInterface;
    private static final String MENSAJE_CORREO ="No existe el cliente con el correo: ";
    private static final String MENSAJE_CUENTABANCARIA ="La cuenta bancaria o el correo no pueden ser null";

    @Autowired
    public PostgresClienteRepositorio(PostgresClienteInterface clienteInterface, PostgresCuentaInterface cuentaInterface) {
        this.clienteInterface = clienteInterface;
        this.cuentaInterface = cuentaInterface;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) throws RoomFinderException {
        ClienteEntidad clienteEntidad = new ClienteEntidad();

        clienteEntidad.setCorreo(cliente.getCorreo());
        clienteEntidad.setNombre(cliente.getNombre());
        clienteEntidad.setTipoDocumento(cliente.getTipoDocumento());
        clienteEntidad.setNumeroDocumento(cliente.getNumeroDocumento());           
        
        ClienteEntidad clientResult = clienteInterface.save(clienteEntidad);

        return new Cliente(
            clientResult.getCorreo(), 
            clientResult.getNombre(), 
            clientResult.getTipoDocumento(), 
            clientResult.getNumeroDocumento(),           
            null
        );
      
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteInterface.findAll().parallelStream().map(clienteEntidad ->
            new Cliente(
                clienteEntidad.getCorreo(),
                clienteEntidad.getNombre(),
                clienteEntidad.getTipoDocumento(),
                clienteEntidad.getNumeroDocumento(),
                clienteEntidad.getCuentaBancariaEntidad()==null? null:new CuentaBancaria(clienteEntidad.getCuentaBancariaEntidad())
            )

        ).toList();
    }

    @Override
    public Cliente consultarClientePorCorreo(String correo) throws RoomFinderException {
        ClienteEntidad clienteEntidad = clienteInterface.findById(correo)
                                        .orElseThrow(() -> new RoomFinderException(MENSAJE_CORREO + correo));
        
        return new Cliente(
            clienteEntidad.getCorreo(),
            clienteEntidad.getNombre(),
            clienteEntidad.getTipoDocumento(),
            clienteEntidad.getNumeroDocumento(),
            clienteEntidad.getCuentaBancariaEntidad()==null? null:new CuentaBancaria(clienteEntidad.getCuentaBancariaEntidad())
        );
        
    }
  
    @Override
    @Transactional
    public void eliminarClientePorCorreo(String correo) throws RoomFinderException {
        if (correo == null) throw new RoomFinderException(MENSAJE_CUENTABANCARIA);

        ClienteEntidad clienteEntidad = clienteInterface.findById(correo)
                                        .orElseThrow(() -> new RoomFinderException(MENSAJE_CORREO + correo));
        
        clienteInterface.delete(clienteEntidad);

    }

    @Override
    public CuentaBancaria crearCuentaBancaria(CuentaBancaria cuentaBancaria, String correo) throws RoomFinderException {
        if (cuentaBancaria== null || correo == null) throw new RoomFinderException(MENSAJE_CUENTABANCARIA);            
        
       ClienteEntidad clienteEntidad = clienteInterface.findById(correo)
                                        .orElseThrow(() -> new RoomFinderException(MENSAJE_CORREO + correo));

        CuentaBancariaEntidad cuentaBancariaEntidad = new CuentaBancariaEntidad();
        cuentaBancariaEntidad.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
        cuentaBancariaEntidad.setCantidadCredito(cuentaBancaria.getCantidadCredito().doubleValue());
        cuentaBancariaEntidad.setClienteEntidad(clienteEntidad);

        CuentaBancariaEntidad cuentaResult = cuentaInterface.save(cuentaBancariaEntidad);

        return new CuentaBancaria(
            cuentaResult.getNumeroCuenta(),
            cuentaResult.getCantidadCredito()
        );
    }
    
}
