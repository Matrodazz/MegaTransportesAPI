package megat.api.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import megat.api.controllers.EmbalagemController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="T_MT_EMBALAGEM")

public class Embalagem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double capacidade_embalagem;

    private String tipo;

    private String material;
   
    
    public EntityModel<Embalagem> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(EmbalagemController.class).show(id)).withSelfRel(),
            linkTo(methodOn(EmbalagemController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(EmbalagemController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}
