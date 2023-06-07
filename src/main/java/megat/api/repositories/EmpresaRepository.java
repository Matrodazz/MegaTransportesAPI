package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import megat.api.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Page<Empresa> findByNomeContaining(String busca, Pageable pageable);
    
    @Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
    Page<Empresa> findEmpresasByCnpj(String cnpj, Pageable pageable);


}

