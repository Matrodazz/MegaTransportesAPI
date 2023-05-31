package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Embalagem;



public interface EmbalagemRepository extends JpaRepository<Embalagem, Long> {
    
    Page<Embalagem> findByTipoContaining(String busca, Pageable pageable);

}