package arcn.roomfinder.cliente.domain.entity;

import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import jakarta.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class ClienteEntidad {
    private String nombre;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

    @Id
    private String correo;
    private CuentaBancariaEntidad cuentaBancariaEntidad;
    
    
}
