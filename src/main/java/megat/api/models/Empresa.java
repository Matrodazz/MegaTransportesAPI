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

public class Empresa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cnpj;
    
    @NotNull
    private String razao_social;

    @NotNull
    private String nome;

    @NotNull
    private String status_atividade;

    @NotNull
    private LocalDate data_inclusao;

    private LocalDate data_encerramento;


    
    public EntityModel<Empresa> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(EmpresaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(EmpresaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(EmpresaController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}
