package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Motorista;



public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    
    Page<Motorista> findByNomeContaining(String busca, Pageable pageable);

}
