package arcn.roomfinder.cliente.domain.repository.postgresRepositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import arcn.roomfinder.cliente.domain.entity.ClienteEntidad;

public interface PostgresClienteInterface extends JpaRepository<ClienteEntidad, String> {
    
}
