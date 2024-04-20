package arcn.roomfinder.cliente;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import arcn.roomfinder.cliente.application.ClienteServicio;
import arcn.roomfinder.cliente.application.ClienteServicioImpl;
import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import arcn.roomfinder.cliente.domain.repository.ClienteRepositorio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.junit.Before;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServicioTest {

    @Mock
    private ClienteRepositorio clienteRepositorio;

    @InjectMocks
    private ClienteServicioImpl clienteServicio;

    private Cliente clienteCorrecto;
    private CuentaBancaria cuentaBancariaCorrecta;

    public ClienteServicioTest(){
        init();
    }

    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void setUp() {
        cuentaBancariaCorrecta = new CuentaBancaria("123456789", 1000000);

        clienteCorrecto = new Cliente(
            "JuanPuentes@gmail.com",
            "Juan Puentes", 
            TipoDocumento.valueOf("CC"), 
            "123456789",             
            cuentaBancariaCorrecta
        );
        
    }

    @Test
    public void deberiaCrearCliente() throws RoomFinderException{
        doReturn(clienteCorrecto).when(clienteRepositorio).crearCliente(any(Cliente.class));
        Cliente clienteRespuesta = clienteServicio.crearCliente(clienteCorrecto);

        assertNotNull(clienteRespuesta);
        assertEquals(clienteCorrecto, clienteRespuesta);
    }

    @Test
    public void deberiaLanzarExcepcionSiNoTieneInfoUsuario() throws RoomFinderException{
        doThrow(RoomFinderException.class).when(clienteRepositorio).crearCliente(nullable(Cliente.class));
        assertThrows(RoomFinderException.class, () -> clienteServicio.crearCliente(null));
        assertThrows(NullPointerException.class, () -> clienteServicio.crearCliente(new Cliente(null, null, null, null, null)));
    }

    
}
