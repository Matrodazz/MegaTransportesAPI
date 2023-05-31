package megat.api.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megat.api.controllers.VeiculoController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String modelo;
    
    @NotNull
    private String marca;

    @NotNull
    private String placa;

    @NotNull
    private String capacidade;
   
    
    public EntityModel<Veiculo> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(VeiculoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(VeiculoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(VeiculoController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}
