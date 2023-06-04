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
import megat.api.models.Embalagem;
import megat.api.repositories.EmbalagemRepository;


@RestController
@Slf4j
@RequestMapping("/api/embalagem")
public class EmbalagemController {

    Logger log = LoggerFactory.getLogger(EmbalagemController.class);

    @Autowired
    EmbalagemRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "embalagem")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Embalagem> embalagens = (busca == null)?
        
        repository.findAll(pageable):
        repository.findByTipoContaining(busca, pageable);
        return assembler.toModel(embalagens.map(Embalagem::toEntityModel));
    }





    @PostMapping
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Embalagem cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Embalagem embalagem){
        log.info("Cadastrando embalagem" + embalagem);
        repository.save(embalagem);
        return ResponseEntity
        .created(embalagem.toEntityModel().getRequiredLink("self").toUri())
        .body(embalagem.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Embalagem> show(@PathVariable Long id) {
        log.info("Detalhando embalagem" + id);
        var embalagem = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("embalagem não encontrada"));

        return ResponseEntity.ok(embalagem);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Embalagem> destroy(@PathVariable Long id) {
        log.info("Apagando embalagem" + id);
        var embalagem = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, embalagem não encontrada"));

        repository.delete(embalagem);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Embalagem> update(@PathVariable Long id, @RequestBody @Valid Embalagem embalagem){
        log.info("Atualizando embalagem" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, embalagem não encontrada"));

        embalagem.setId(id);
        repository.save(embalagem);

        return ResponseEntity.ok(embalagem);
        
    }
}

