package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Alimento;



public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    
    Page<Alimento> findByNomeContaining(String busca, Pageable pageable);

}
