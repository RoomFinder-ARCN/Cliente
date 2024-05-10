package arcn.roomfinder.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import arcn.roomfinder.cliente.domain.entity.ClienteEntidad;
import arcn.roomfinder.cliente.domain.entity.CuentaBancariaEntidad;
import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import arcn.roomfinder.cliente.domain.repository.postgresRepositorio.PostgresClienteInterface;
import arcn.roomfinder.cliente.domain.repository.postgresRepositorio.PostgresClienteRepositorio;
import arcn.roomfinder.cliente.domain.repository.postgresRepositorio.PostgresCuentaInterface;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;
import java.util.Collections;



@RunWith(MockitoJUnitRunner.class)
public class PostgresClienteRepositorioTest {

    @Mock
    private PostgresClienteInterface postgresClienteInterface;

    @Mock
    private PostgresCuentaInterface postgresCuentaInterface;


    @InjectMocks
    private PostgresClienteRepositorio postgresClienteRepositorio;

    private Cliente clienteCorrecto;
    private CuentaBancaria cuentaBancariaCorrecta;
    private ClienteEntidad clienteCorrectoEntidad;
    private CuentaBancariaEntidad cuentaBancariaCorrectaEntidad;
    
    @Before
    public void setUp() {
        try{
            clienteCorrecto = new Cliente(
                "JuanPuentes@gmail.com",
                "Juan Puentes", 
                TipoDocumento.valueOf("CC"), 
                "123456789",             
                null
            );
    
            cuentaBancariaCorrecta = new CuentaBancaria("123456789", 1000000);
            
            clienteCorrectoEntidad = new ClienteEntidad(
                "JuanPuentes@gmail.com",
                "Juan Puentes", 
                TipoDocumento.valueOf("CC"), 
                "123456789",
                null
            );
    
            cuentaBancariaCorrectaEntidad = new CuentaBancariaEntidad("123456789", 1000000, clienteCorrectoEntidad);

        }catch(RoomFinderException e){
            e.printStackTrace();

        }

    }

    @Test
    public void deberiaCrearCliente() throws RoomFinderException {
        doReturn(clienteCorrectoEntidad).when(postgresClienteInterface).save(any(ClienteEntidad.class));
        Cliente resultado = postgresClienteRepositorio.crearCliente(clienteCorrecto);
        assertNotNull(resultado);
        assertEquals(clienteCorrecto.getCorreo(), resultado.getCorreo());
    }

    @Test
    public void deberiaConsultarTodosLosCliente(){
        doReturn(List.of(clienteCorrectoEntidad)).when(postgresClienteInterface).findAll();
        List<Cliente> resultado = postgresClienteRepositorio.obtenerTodosLosClientes();
        assertEquals(1, resultado.size());
        assertEquals(clienteCorrecto.getCorreo(), resultado.get(0).getCorreo());
    }

    @Test
    public void deberiaConsultarClientePorCorreo() throws RoomFinderException {
        doReturn(Optional.of(clienteCorrectoEntidad)).when(postgresClienteInterface).findById(any(String.class));
        Cliente resultado = postgresClienteRepositorio.consultarClientePorCorreo(clienteCorrecto.getCorreo());
        assertNotNull(resultado);
        assertEquals(clienteCorrecto.getCorreo(), resultado.getCorreo());
    }

    @Test
    public void noDeberiaConsultarClientePorCorreoCuandoNoExiste() throws RoomFinderException {
        assertThrows(RoomFinderException.class, () -> postgresClienteRepositorio.consultarClientePorCorreo("pollito@gmail.com"));
    }

    @Test
    public void deberiaEliminarClientePorCorreo()throws RoomFinderException{
        doReturn(Optional.of(clienteCorrectoEntidad))
            .doReturn(Optional.empty())
            .when(postgresClienteInterface).findById(any(String.class));
        doNothing().when(postgresClienteInterface).delete(any(ClienteEntidad.class));
        postgresClienteRepositorio.eliminarClientePorCorreo(clienteCorrecto.getCorreo());
        assertThrows( RoomFinderException.class,()-> postgresClienteRepositorio.consultarClientePorCorreo(clienteCorrecto.getCorreo()));
    }
    
    @Test
    public void noDeberiaEliminarClientePorCorreoCuandoNoExiste() throws RoomFinderException{
        assertThrows(RoomFinderException.class,()-> postgresClienteRepositorio.eliminarClientePorCorreo("pollito@gmail.com"));

    }

    @Test
    public void noDeneriaEliminarClientePorCorreoCuandoEsNulo() throws RoomFinderException{
        assertThrows(RoomFinderException.class,()-> postgresClienteRepositorio.eliminarClientePorCorreo(null));
    }

    @Test
    public void deberiaCrearCuentaBancaria() throws RoomFinderException {
        doReturn(Optional.of(clienteCorrectoEntidad)).when(postgresClienteInterface).findById(any(String.class));
        doReturn(cuentaBancariaCorrectaEntidad).when(postgresCuentaInterface).save(any(CuentaBancariaEntidad.class));
        CuentaBancaria resultado = postgresClienteRepositorio.crearCuentaBancaria(cuentaBancariaCorrecta, clienteCorrecto.getCorreo());
        assertEquals(cuentaBancariaCorrecta.getNumeroCuenta(), resultado.getNumeroCuenta());
    }

    @Test
    public void noDeberiaCrearCuentaBancariaCuandoLaCuentaBancariaEsNula() throws RoomFinderException {
      assertThrows(RoomFinderException.class,()-> postgresClienteRepositorio.crearCuentaBancaria(null, clienteCorrecto.getCorreo()));
    }

    @Test
    public void noDeberiaCrearCuentaBancariaCuandoElCorreoEsNulo() throws RoomFinderException {
      assertThrows(RoomFinderException.class,()-> postgresClienteRepositorio.crearCuentaBancaria(cuentaBancariaCorrecta, null));
    }

    @Test
    public void noDeberiaCrearCuentaBancariaCuandoLaInformacionEsNula() throws RoomFinderException {
      assertThrows(RoomFinderException.class,()-> postgresClienteRepositorio.crearCuentaBancaria(null, null));
    }

    @Test
    public void noDeberiaCrearCuentaBancariaCuandoElCorreoNoExiste() throws RoomFinderException {
      assertThrows(RoomFinderException.class,()-> postgresClienteRepositorio.crearCuentaBancaria(cuentaBancariaCorrecta, "pollito@gmail.com"));
    }

}
