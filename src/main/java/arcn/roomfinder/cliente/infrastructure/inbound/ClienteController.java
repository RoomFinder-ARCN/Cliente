package arcn.roomfinder.cliente.infrastructure.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arcn.roomfinder.cliente.domain.model.Cliente;
import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.application.ClienteServicio;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    private ClienteServicio clienteServicio;

    @Autowired
    public ClienteController(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente){
        try{
            var clienteCreado= clienteServicio.crearCliente(cliente);
            return ResponseEntity.status(201).body("Cliente :" + clienteCreado.getNombre() +" creado");

        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());

        }

    }

    @GetMapping(value = "")
    public ResponseEntity<?> obtenerTodosLosClientes(){
        try{
            var clientes= clienteServicio.obtenerTodosLosClientes();
            return ResponseEntity.status(200).body(clientes);
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());

        }

    }

    @GetMapping(value = "/{correo}")
    public ResponseEntity<?> consultarClientePorCorreo(@PathVariable("correo")  String correo){
        try{
            var cliente= clienteServicio.consultarClientePorCorreo(correo);
            return ResponseEntity.status(200).body(cliente);
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{correo}")
    public ResponseEntity<?> eliminarClientePorCorreo(@PathVariable("correo")  String correo){
        try{
            clienteServicio.eliminarClientePorCorreo(correo);
            return ResponseEntity.status(200).body("Cliente " + correo + "fue eliminado");
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value ="/{correo}/cuenta")
    public ResponseEntity<?> crearCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria, @PathVariable("correo")  String correo){
        try{
            clienteServicio.crearCuentaBancaria(cuentaBancaria, correo);
            return ResponseEntity.status(201).body("Cuenta bancaria No" + cuentaBancaria.getNumeroCuenta()  +" creada");

        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());

        }

    }


    
}