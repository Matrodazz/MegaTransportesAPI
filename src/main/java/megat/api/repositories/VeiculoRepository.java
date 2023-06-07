package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import megat.api.models.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Page<Veiculo> findByPlacaContaining(String busca, Pageable pageable);
    
    @Query("SELECT v FROM Veiculo v WHERE v.modelo = :modelo")
    Page<Veiculo> findVeiculosByModelo(String modelo, Pageable pageable);

    
    @Query("SELECT v FROM Veiculo v WHERE v.marca = :marca")
    Page<Veiculo> findVeiculosByMarca(String marca, Pageable pageable);
    

    @Query("SELECT v FROM Veiculo v WHERE v.capacidade >= :capacidade")
    Page<Veiculo> findVeiculosByCapacidadeMaiorOuIgual(double capacidade, Pageable pageable);
}


