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
import megat.api.models.Motorista;
import megat.api.repositories.MotoristaRepository;


@RestController
@Slf4j
@RequestMapping("/api/motorista")
public class MotoristaController {

    Logger log = LoggerFactory.getLogger(MotoristaController.class);

    @Autowired
    MotoristaRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "motorista")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Motorista> motoristas = (busca == null)?
        
        repository.findAll(pageable):
        repository.findByNomeContaining(busca, pageable);
        return assembler.toModel(motoristas.map(Motorista::toEntityModel));
    }





    @PostMapping
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Motorista cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Motorista motorista){
        log.info("Cadastrando motorista" + motorista);
        repository.save(motorista);
        return ResponseEntity
        .created(motorista.toEntityModel().getRequiredLink("self").toUri())
        .body(motorista.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Motorista> show(@PathVariable Long id) {
        log.info("Detalhando motorista" + id);
        var motorista = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("motorista não encontrado"));

        return ResponseEntity.ok(motorista);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Motorista> destroy(@PathVariable Long id) {
        log.info("Apagando motorista" + id);
        var motorista = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, motorista não encontrado"));

        repository.delete(motorista);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Motorista> update(@PathVariable Long id, @RequestBody @Valid Motorista motorista){
        log.info("Atualizando motorista" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, motorista não encontrado"));

        motorista.setId(id);
        repository.save(motorista);

        return ResponseEntity.ok(motorista);
        
    }
}
