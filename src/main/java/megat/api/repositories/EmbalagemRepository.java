package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import megat.api.models.Embalagem;

public interface EmbalagemRepository extends JpaRepository<Embalagem, Long> {

    Page<Embalagem> findByTipoContaining(String busca, Pageable pageable);
    
    @Query("SELECT e FROM Embalagem e WHERE e.material = :material")
    Page<Embalagem> findEmbalagensByMaterial(String material, Pageable pageable);

    @Query("SELECT e FROM Embalagem e WHERE e.tipo LIKE %:busca% OR e.material LIKE %:busca%")
    Page<Embalagem> findEmbalagensByTipoOuMaterial(String busca, Pageable pageable);
}
