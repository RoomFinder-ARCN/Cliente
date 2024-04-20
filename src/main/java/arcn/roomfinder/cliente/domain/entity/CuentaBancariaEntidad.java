package arcn.roomfinder.cliente.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancariaEntidad {

    @Id
    @Column
    private String numeroCuenta;

    @Column
    private double cantidadCredito;

    public CuentaBancariaEntidad(CuentaBancaria cuentaBancaria) {
        this.numeroCuenta = cuentaBancaria.getNumeroCuenta();
        this.cantidadCredito = cuentaBancaria.getCantidadCredito().doubleValue();
    }


    
}
