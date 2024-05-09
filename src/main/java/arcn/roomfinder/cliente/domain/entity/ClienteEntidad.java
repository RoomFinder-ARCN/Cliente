package arcn.roomfinder.cliente.domain.entity;

import arcn.roomfinder.cliente.domain.model.TipoDocumento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import jakarta.persistence.Table;
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

    @Column(name = "tipo_documento")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;
    
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "clienteEntidad", cascade = CascadeType.ALL)   
    private CuentaBancariaEntidad cuentaBancariaEntidad;
    
}
