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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megat.api.controllers.ViagemController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Viagem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dthr_partida;
    
    @NotNull
    private LocalDateTime dthr_chegada;
    
    public EntityModel<Viagem> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(ViagemController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ViagemController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ViagemController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}
