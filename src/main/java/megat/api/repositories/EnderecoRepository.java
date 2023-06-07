package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import megat.api.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Page<Endereco> findByLogradouroContaining(String busca, Pageable pageable);
    
    @Query("SELECT e FROM Endereco e WHERE e.bairro = :bairro")
    Page<Endereco> findEnderecosByBairro(String bairro, Pageable pageable);

    @Query("SELECT e FROM Endereco e WHERE e.cidade = :cidade")
    Page<Endereco> findEnderecosByCidade(String cidade, Pageable pageable);
    
    @Query("SELECT e FROM Endereco e WHERE e.estado = :estado")
    Page<Endereco> findEnderecosByEstado(String estado, Pageable pageable);
}
