package arcn.roomfinder.cliente.domain.repository.postgresRepositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arcn.roomfinder.cliente.domain.entity.ClienteEntidad;

@Repository
public interface PostgresClienteInterface extends JpaRepository<ClienteEntidad, String> {
    
}
