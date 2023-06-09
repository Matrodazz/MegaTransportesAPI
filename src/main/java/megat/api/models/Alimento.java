package megat.api.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megat.api.controllers.AlimentoController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="T_MT_ALIMENTO")

public class Alimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private int temperaturaMinima;

    @NotNull
    private int temperaturaMaxima;

    @NotNull
    private int umidadeMinima;

    @NotNull
    private int umidadeMaxima;

    
    
    public EntityModel<Alimento> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(AlimentoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(AlimentoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(AlimentoController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}