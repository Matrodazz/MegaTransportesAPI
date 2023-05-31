package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Viagem;



public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    
    Page<Viagem> findByIdContaining(String busca, Pageable pageable);

}
