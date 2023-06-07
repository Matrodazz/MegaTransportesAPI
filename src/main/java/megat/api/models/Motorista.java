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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megat.api.controllers.MotoristaController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="T_MT_MOTORISTA")

public class Motorista {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull @Min(value = 0, message = "Deve ser positivo") 
    private int idade;

    @NotNull
    private String cpf;

    @NotNull
    private String status_contrato;
   
    public EntityModel<Motorista> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(MotoristaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(MotoristaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(MotoristaController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}
