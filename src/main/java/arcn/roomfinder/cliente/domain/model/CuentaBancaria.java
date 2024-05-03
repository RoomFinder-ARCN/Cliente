package arcn.roomfinder.cliente.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import arcn.roomfinder.cliente.domain.entity.CuentaBancariaEntidad;
import arcn.roomfinder.cliente.domain.exception.RoomFinderException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class CuentaBancaria {
    
    @NonNull
    @Getter
    @JsonProperty("numeroCuenta")
    private String numeroCuenta;

    @NonNull
    @Getter
    @JsonProperty("cantidadCredito")
    private BigDecimal cantidadCredito;

    public CuentaBancaria(String numeroCuenta) throws RoomFinderException {
        this(numeroCuenta,0);
    }

    public CuentaBancaria(String numeroCuenta, double cantidadCredito) throws RoomFinderException {
        if (numeroCuenta == null || numeroCuenta.equals("")) throw new RoomFinderException("El número de cuenta no puede ser nulo o vacío");
        
        this.numeroCuenta = numeroCuenta;
        this.cantidadCredito = BigDecimal.valueOf(cantidadCredito);
    }

    public CuentaBancaria(CuentaBancariaEntidad cuentaBancariaEntidad) {
        this.numeroCuenta = cuentaBancariaEntidad.getNumeroCuenta();
        this.cantidadCredito = BigDecimal.valueOf(cuentaBancariaEntidad.getCantidadCredito());
    }

    public BigDecimal agregarFondos(BigDecimal cantidad){
        this.cantidadCredito = this.cantidadCredito.add(cantidad);
        return cantidadCredito;
    }

    public BigDecimal quitarFondos(BigDecimal cantidad){
        this.cantidadCredito = this.cantidadCredito.subtract(cantidad);
        return cantidadCredito;
    }

    public BigDecimal quitarFondos(double cantidad){
        return this.quitarFondos(BigDecimal.valueOf(cantidad));
    }

    public BigDecimal consultarSaldo(){
        return this.cantidadCredito;
    }
    
}
