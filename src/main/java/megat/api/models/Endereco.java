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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import megat.api.controllers.EnderecoController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_MT_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String logradouro;

    @NotNull
    @Min(value = 0, message = "Deve ser positivo")
    private int numero;

    @NotNull
    private String cep;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 3)
    private String siglaEstado;

    @NotNull
    private String regiao;

    private String pontoReferencia;

    public EntityModel<Endereco> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(EnderecoController.class).show(id)).withSelfRel(),
                linkTo(methodOn(EnderecoController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(EnderecoController.class).index(null, Pageable.unpaged())).withRel("all")
        );
    }
}
