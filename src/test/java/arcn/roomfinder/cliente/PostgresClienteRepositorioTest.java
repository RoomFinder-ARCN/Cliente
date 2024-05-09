package arcn.roomfinder.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.hibernate.mapping.Collection;
import org.hibernate.mapping.List;
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

import java.util.ArrayList;
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
    
            cuentaBancariaCorrectaEntidad = new CuentaBancariaEntidad("123456", 1000000, clienteCorrectoEntidad);

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

    /*@Test
    public void deberiaConsultarTodosLosCliente(){
        doReturn(Collections.singletonList(clienteCorrectoEntidad)).when(postgresClienteInterface).findAll();
        ArrayList<Cliente> resultado = (ArrayList<Cliente>)postgresClienteRepositorio.obtenerTodosLosClientes();
        assertEquals(1, resultado.size());
        assertNotEquals(clienteCorrecto.getCorreo(), resultado.get(0).getCorreo());

    }*/
    
}
