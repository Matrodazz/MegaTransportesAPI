package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import megat.api.models.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    Page<Motorista> findByNomeContaining(String busca, Pageable pageable);
    
    @Query("SELECT m FROM Motorista m WHERE m.cpf = :cpf")
    Page<Motorista> findMotoristasByCpf(String cpf, Pageable pageable);

    @Query("SELECT m FROM Motorista m WHERE m.idade >= :idade")
    Page<Motorista> findMotoristasByIdadeMaiorOuIgual(int idade, Pageable pageable);

    @Query("SELECT m FROM Motorista m WHERE m.nome LIKE %:busca% OR m.cpf LIKE %:busca%")
    Page<Motorista> findMotoristasByNomeOuCpf(String busca, Pageable pageable);
}
