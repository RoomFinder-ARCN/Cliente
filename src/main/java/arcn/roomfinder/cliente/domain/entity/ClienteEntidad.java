package arcn.roomfinder.cliente.domain.entity;

import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

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

    @Id
    @Column
    private String correo;

    @Column
    private String nombre;

    @Column
    private String tipoDocumento;

    @Column
    private String numeroDocumento;

    
    @OneToOne
    private CuentaBancariaEntidad cuentaBancariaEntidad;
    
    
}
