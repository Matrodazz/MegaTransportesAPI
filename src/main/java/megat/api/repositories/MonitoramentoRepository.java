package megat.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import megat.api.models.Monitoramento;

public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {

    Page<Monitoramento> findByIdContaining(String busca, Pageable pageable);
    
    @Query("SELECT m FROM Monitoramento m WHERE m.temperatura = :temperatura")
    Page<Monitoramento> findMonitoramentosByTemperatura(double temperatura, Pageable pageable);

    @Query("SELECT m FROM Monitoramento m WHERE m.latitude = :latitude AND m.longitude = :longitude")
    Page<Monitoramento> findMonitoramentosByLatitudeAndLongitude(double latitude, double longitude, Pageable pageable);
}
