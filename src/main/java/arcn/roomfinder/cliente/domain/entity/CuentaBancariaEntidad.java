package arcn.roomfinder.cliente.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "cantidad_credito")
    private double cantidadCredito;

    @OneToOne
    @JoinColumn(name = "cliente", referencedColumnName = "correo")
    private ClienteEntidad clienteEntidad;

    public CuentaBancariaEntidad(CuentaBancaria cuentaBancaria) {
        this.numeroCuenta = cuentaBancaria.getNumeroCuenta();
        this.cantidadCredito = cuentaBancaria.getCantidadCredito().doubleValue();
        
    }


    
}
