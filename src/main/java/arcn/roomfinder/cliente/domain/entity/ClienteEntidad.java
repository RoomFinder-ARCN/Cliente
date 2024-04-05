package arcn.roomfinder.cliente.domain.entity;

import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class ClienteEntidad {
    private String nombre;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

    @Id
    private String correo;
    private CuentaBancaria cuentaBancaria;
    
    
}
