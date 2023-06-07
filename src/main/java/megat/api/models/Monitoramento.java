package megat.api.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megat.api.controllers.MonitoramentoController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="T_MT_MONITORAMENTO")

public class Monitoramento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int temperatura;

    @NotNull
    private int umidade;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

    @NotNull
    private LocalDateTime dthrMonitoramento;
   
    
    public EntityModel<Monitoramento> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(MonitoramentoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(MonitoramentoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(MonitoramentoController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}