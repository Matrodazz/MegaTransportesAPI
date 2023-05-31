package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import megat.api.models.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
    Page<Endereco> findByLogradouroContaining(String busca, Pageable pageable);

}