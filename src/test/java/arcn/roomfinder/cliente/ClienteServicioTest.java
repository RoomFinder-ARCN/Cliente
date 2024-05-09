package arcn.roomfinder.cliente;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import arcn.roomfinder.cliente.application.ClienteServicioImpl;
import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import arcn.roomfinder.cliente.domain.repository.ClienteRepositorio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

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
        try{
            cuentaBancariaCorrecta = new CuentaBancaria("123456789", 1000000);

            clienteCorrecto = new Cliente(
                "JuanPuentes@gmail.com",
                "Juan Puentes", 
                TipoDocumento.valueOf("CC"), 
                "123456789",             
                cuentaBancariaCorrecta
            );

        }catch(RoomFinderException e){
            e.printStackTrace();
        }        
    }

    @Test
    public void deberiaCrearCliente() throws RoomFinderException{
        doReturn(clienteCorrecto).when(clienteRepositorio).crearCliente(any(Cliente.class));
        Cliente clienteRespuesta = clienteServicio.crearCliente(clienteCorrecto);

        assertNotNull(clienteRespuesta);
        assertEquals(clienteCorrecto, clienteRespuesta);
    }

    @Test
    public void noDeberiaCrearClienteSiNoTieneInfoUsuario() throws RoomFinderException{
        assertThrows(RoomFinderException.class, () -> clienteServicio.crearCliente(null));
        assertThrows(NullPointerException.class, () -> new Cliente(null, null, null, null, null));
    }

    @Test
    public void noDeberiaPermitirTipoDocumentoCuandoElValorNoEsValido() {
        assertThrows( IllegalArgumentException.class, ()-> TipoDocumento.valueOf("LT"));
    }

    @Test
    public void noDeberiaPermitirTipoDocumentoCuandoElValorEsVacio() {
        assertThrows( IllegalArgumentException.class, ()-> TipoDocumento.valueOf(""));
    }

    @Test
    public void oDeberiaPermitirTipoDocumentoCuandoElValorEsNulo() {
        assertThrows( NullPointerException.class, ()-> TipoDocumento.valueOf(null));
    }

    @Test
    public void deberiaConsultarTodosLosCliente() throws RoomFinderException{
        CuentaBancaria cuentaBancariaPrueba = new CuentaBancaria("123456", 1000000);

        Cliente clientePrueba = new Cliente(
            "estella.mur@gmail.com",
            "Estella Mur", 
            TipoDocumento.valueOf("CC"), 
            "40037789",             
            cuentaBancariaPrueba
        );
        
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(clientePrueba);
        listaClientes.add(clienteCorrecto);

        doReturn(listaClientes).when(clienteRepositorio).obtenerTodosLosClientes();
        assertEquals(listaClientes, clienteServicio.obtenerTodosLosClientes());
        
    }

    @Test
    public void deberiaConsultarClientePorCorreo() throws RoomFinderException{
        doReturn(clienteCorrecto).when(clienteRepositorio).consultarClientePorCorreo(anyString());
        var clienteRespuesta = clienteServicio.consultarClientePorCorreo("JuanPuentes@gmail.com");
        assertEquals(clienteCorrecto, clienteRespuesta);
    }

    @Test
    public void noDeberiaConsultarClientePorCorreoSiNoExiste() throws RoomFinderException{
        doThrow(RoomFinderException.class).when(clienteRepositorio).consultarClientePorCorreo(anyString());
        assertThrows(RoomFinderException.class, () -> clienteServicio.consultarClientePorCorreo("pollito@gmail.com"));
    }

    @Test
    public void noDeberiaConsultarClientePorCorreoCuandoEsNulo() throws RoomFinderException{
        assertThrows(RoomFinderException.class, () -> clienteServicio.consultarClientePorCorreo(null));
    }

    @Test
    public void noDeberiaConsultarClientePorCorreoCuandoEsVacio() throws RoomFinderException{
        assertThrows(RoomFinderException.class, () -> clienteServicio.consultarClientePorCorreo(""));
    }

    @Test
    public void deberiaCrearCuentaBancaria() throws RoomFinderException{
        CuentaBancaria cuentaBancariaPrueba = new CuentaBancaria("123456", 1000000);

        Cliente clientePrueba = new Cliente(
            "estella.mur@gmail.com",
            "Estella Mur", 
            TipoDocumento.valueOf("CC"), 
            "40037789",             
            null
        );

        doReturn(cuentaBancariaPrueba).when(clienteRepositorio).crearCuentaBancaria(any(CuentaBancaria.class),anyString());
        CuentaBancaria cuentaBancariaRespuesta = clienteServicio.crearCuentaBancaria(cuentaBancariaPrueba, clientePrueba.getCorreo());
        
        assertNotNull(cuentaBancariaRespuesta);
        assertEquals(cuentaBancariaPrueba, cuentaBancariaRespuesta);
    }

    @Test
    public void noDeberiaCrearCuentaBancariaSiLaInfoEsNula() throws RoomFinderException{
        assertThrows(RoomFinderException.class, () -> clienteServicio.crearCuentaBancaria(null, null));
    }

    @Test
    public void noDeberiaCrearCuentaBancariaSinLosDatosDeLaCuenta() throws RoomFinderException{

        Cliente clientePrueba = new Cliente(
            "estella.mur@gmail.com",
            "Estella Mur", 
            TipoDocumento.valueOf("CC"), 
            "40037789",             
            null
        );

        assertThrows(RoomFinderException.class, () -> clienteServicio.crearCuentaBancaria(null, clientePrueba.getCorreo()));
    }

    @Test
    public void noDeberiaCrearCuentaBancariaSiElCorreoEsNulo() throws RoomFinderException{
        CuentaBancaria cuentaBancariaPrueba = new CuentaBancaria("123456", 1000000);

        assertThrows(RoomFinderException.class, () -> clienteServicio.crearCuentaBancaria(cuentaBancariaPrueba, null));
    }

    @Test
    public void noDeberiaCrearCuentaBancariaSiElCorreoEsVacio() throws RoomFinderException{
        CuentaBancaria cuentaBancariaPrueba = new CuentaBancaria("123456", 1000000);
        assertThrows(RoomFinderException.class, () -> clienteServicio.crearCuentaBancaria(cuentaBancariaPrueba, ""));
    }

    @Test
    public void noDeberiaCrearCuentaBancariaSiElNumeroDeLaCuentaEsNulo() throws RoomFinderException{    
        assertThrows(RoomFinderException.class, () -> new CuentaBancaria(null,0));
    }

    @Test
    public void deberiaEliminarUnClientePorCorreo() throws RoomFinderException{
        clienteServicio.eliminarClientePorCorreo(clienteCorrecto.getCorreo());
        assertNull(clienteServicio.consultarClientePorCorreo(clienteCorrecto.getCorreo())); 
    }

    @Test
    public void noDeberiaEliminarClienteSiElCorreoNoExiste() throws RoomFinderException{
        doThrow(RoomFinderException.class).when(clienteRepositorio).eliminarClientePorCorreo(anyString());
        assertThrows(RoomFinderException.class, ()-> clienteServicio.eliminarClientePorCorreo("pollito@gmail.com"));
    }

    @Test
    public void noDeberiaEliminarUnClientePorCorreoCuandoEsNulo() throws RoomFinderException{        
        assertThrows(RoomFinderException.class, ()-> clienteServicio.eliminarClientePorCorreo(null));      
    }

    @Test
    public void noDeberiaEliminarUnClientePorCorreoCuandoEsVacio() throws RoomFinderException{        
        assertThrows(RoomFinderException.class, ()-> clienteServicio.eliminarClientePorCorreo(""));      
    }

    
}
