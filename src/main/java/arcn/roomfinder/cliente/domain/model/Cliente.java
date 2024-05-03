package arcn.roomfinder.cliente.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cliente {

    @NonNull
    private String correo;

    @NonNull
    private String nombre;

    @NonNull
    private TipoDocumento tipoDocumento;

    @NonNull
    private String numeroDocumento;
    
    private CuentaBancaria cuentaBancaria;
    
}
