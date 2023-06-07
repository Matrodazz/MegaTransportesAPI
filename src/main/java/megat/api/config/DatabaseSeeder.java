package megat.api.config;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import megat.api.models.Alimento;
import megat.api.models.Monitoramento;
import megat.api.models.Usuario;
import megat.api.models.Veiculo;
import megat.api.models.Viagem;
import megat.api.repositories.AlimentoRepository;
import megat.api.repositories.MonitoramentoRepository;
import megat.api.repositories.UsuarioRepository;
import megat.api.repositories.VeiculoRepository;
import megat.api.repositories.ViagemRepository;



@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    AlimentoRepository alimentoRepository;

    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ViagemRepository viagemRepository;

    @Autowired
    MonitoramentoRepository monitoramentoRepository;

   

   

    @Override
    public void run(String... args) throws Exception {

        alimentoRepository.saveAll(List.of(
            Alimento.builder().nome("Morango").temperaturaMinima(-18).temperaturaMaxima(-12).umidadeMinima(20).umidadeMaxima(40).build()
        ));

        veiculoRepository.saveAll(List.of(
            Veiculo.builder().modelo("Atego").marca("Mercedes-Benz").placa("DCG6B67").capacidade("5000").build()
        ));

        LocalDateTime now = LocalDateTime.now();

        Viagem viagem1 = Viagem.builder()
            .dthrPartida(now)
            .dthrChegada(now)
            .build();

        viagemRepository.saveAll(List.of(viagem1));


        Monitoramento monitoramento1 = Monitoramento.builder()
            .temperatura(34)
            .umidade(45)
            .latitude("23°33'01''")
            .longitude("46°38'02''")
            .dthrMonitoramento(now)
            .build();

        monitoramentoRepository.saveAll(List.of(monitoramento1));


        usuarioRepository.save(Usuario.builder()
        .nome("Joao")
        .email("joaov@fiap.com.br")
        .senha("$2a$10$A/PNtSaBPefnglio9g9Qw.0NLNmnGXEDVXVbvtf0yiAH4dmyvY7i2")
        .build()
    );
    
    }
}