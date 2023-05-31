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
import megat.api.models.Viagem;
import megat.api.repositories.ViagemRepository;


@RestController
@Slf4j
@RequestMapping("/api/viagem")
public class ViagemController {

    Logger log = LoggerFactory.getLogger(ViagemController.class);

    @Autowired
    ViagemRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "viagem")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Viagem> viagens = repository.findAll(pageable);
        
    
        return assembler.toModel(viagens.map(Viagem::toEntityModel));
    }





    @PostMapping("/api/viagem")
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Viagem cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Viagem viagem){
        log.info("Cadastrando viagem" + viagem);
        repository.save(viagem);
        return ResponseEntity
        .created(viagem.toEntityModel().getRequiredLink("self").toUri())
        .body(viagem.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Viagem> show(@PathVariable Long id) {
        log.info("Detalhando viagem" + id);
        var viagem = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("viagem não encontrada"));

        return ResponseEntity.ok(viagem);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Viagem> destroy(@PathVariable Long id) {
        log.info("Apagando viagem" + id);
        var viagem = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, viagem não encontrada"));

        repository.delete(viagem);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Viagem> update(@PathVariable Long id, @RequestBody @Valid Viagem viagem){
        log.info("Atualizando viagem" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, viagem não encontrada"));

        viagem.setId(id);
        repository.save(viagem);

        return ResponseEntity.ok(viagem);
        
    }
}
