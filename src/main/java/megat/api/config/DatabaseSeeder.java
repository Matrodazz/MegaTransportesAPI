package megat.api.config;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import megat.api.models.Alimento;
import megat.api.repositories.AlimentoRepository;



@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    AlimentoRepository alimentoRepository;

   

    @Override
    public void run(String... args) throws Exception {

        alimentoRepository.saveAll(List.of(
            Alimento.builder().nome("Sorvete de morango").temperatura_minima(-18).temperatura_maxima(-12).umidade_minima("20%").umidade_maxima("40%").build()
           
        ));
    }
    
}