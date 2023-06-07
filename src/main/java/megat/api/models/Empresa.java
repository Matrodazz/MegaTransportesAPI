package megat.api.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

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
import megat.api.controllers.EmpresaController;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="T_MT_EMPRESA")

public class Empresa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cnpj;
    
    @NotNull
    private String razaoSocial;

    @NotNull
    private String nome;

    @NotNull
    private String statusAtividade;

    @NotNull
    private LocalDate dataInclusao;

    private LocalDate dataEncerramento;


    
    public EntityModel<Empresa> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(EmpresaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(EmpresaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(EmpresaController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}
