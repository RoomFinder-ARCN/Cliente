package arcn.roomfinder.cliente.domain.repository.postgresRepositorio;

import arcn.roomfinder.cliente.domain.entity.CuentaBancariaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresCuentaInterface extends JpaRepository<CuentaBancariaEntidad, String>{
    
}
