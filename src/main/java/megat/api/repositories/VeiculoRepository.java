package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Veiculo;



public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    Page<Veiculo> findByPlacaContaining(String busca, Pageable pageable);

}

