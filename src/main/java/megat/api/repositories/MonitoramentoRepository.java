package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Monitoramento;



public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {
    
    Page<Monitoramento> findByIdContaining(String busca, Pageable pageable);

}