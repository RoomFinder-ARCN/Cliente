package arcn.roomfinder.cliente.domain.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import arcn.roomfinder.cliente.domain.model.CuentaBancaria;
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

    public CuentaBancariaEntidad(CuentaBancaria cuentaBancaria) {
        this.numeroCuenta = cuentaBancaria.getNumeroCuenta();
        this.cantidadCredito = cuentaBancaria.getCantidadCredito();
    }

    @Id
    private String numeroCuenta;
    private BigDecimal cantidadCredito;
    
}
