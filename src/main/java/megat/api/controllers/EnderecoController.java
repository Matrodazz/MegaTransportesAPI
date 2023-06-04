package megat.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import megat.api.exceptions.RestNotFoundException;
import megat.api.models.Endereco;
import megat.api.repositories.EnderecoRepository;


@RestController
@Slf4j
@RequestMapping("/api/endereco")
public class EnderecoController {

    Logger log = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    EnderecoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "endereco")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Endereco> enderecos = (busca == null)?
        
        repository.findAll(pageable):
        repository.findByLogradouroContaining(busca, pageable);
        return assembler.toModel(enderecos.map(Endereco::toEntityModel));
    }





    @PostMapping
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Endereco cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Endereco endereco){
        log.info("Cadastrando endereco" + endereco);
        repository.save(endereco);
        return ResponseEntity
        .created(endereco.toEntityModel().getRequiredLink("self").toUri())
        .body(endereco.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Endereco> show(@PathVariable Long id) {
        log.info("Detalhando endereco" + id);
        var endereco = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("endereco não encontrado"));

        return ResponseEntity.ok(endereco);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Endereco> destroy(@PathVariable Long id) {
        log.info("Apagando endereco" + id);
        var endereco = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, endereco não encontrado"));

        repository.delete(endereco);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody @Valid Endereco endereco){
        log.info("Atualizando endereco" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, endereco não encontrado"));

        endereco.setId(id);
        repository.save(endereco);

        return ResponseEntity.ok(endereco);
        
    }
}

