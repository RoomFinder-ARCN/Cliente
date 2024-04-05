package arcn.roomfinder.cliente.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty("cantidadCredito")
    private BigDecimal cantidadCredito;

    public CuentaBancaria(String numeroCuenta) {
        this(numeroCuenta,0);
    }

    public CuentaBancaria(String numeroCuenta, double cantidadCredito) {
        this.numeroCuenta = numeroCuenta;
        this.cantidadCredito = BigDecimal.valueOf(cantidadCredito);
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
