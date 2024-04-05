package arcn.roomfinder.cliente.infrastructure.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arcn.roomfinder.cliente.domain.model.Cliente;
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
            return ResponseEntity.status(403).body(e.getMessage());

        }

    }



    
}