package megat.api.config;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import megat.api.models.Alimento;
import megat.api.models.Usuario;
import megat.api.repositories.AlimentoRepository;
import megat.api.repositories.UsuarioRepository;



@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    AlimentoRepository alimentoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

   

   

    @Override
    public void run(String... args) throws Exception {

        alimentoRepository.saveAll(List.of(
            Alimento.builder().nome("Sorvete de morango").temperatura_minima(-18).temperatura_maxima(-12).umidade_minima(20).umidade_maxima(40).build()
           
        ));

        usuarioRepository.save(Usuario.builder()
        .nome("Joao")
        .email("joaov@fiap.com.br")
        .senha("$2a$10$A/PNtSaBPefnglio9g9Qw.0NLNmnGXEDVXVbvtf0yiAH4dmyvY7i2")
        .build()
    );
    
    }
}