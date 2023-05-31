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
import megat.api.models.Alimento;
import megat.api.repositories.AlimentoRepository;


@RestController
@Slf4j
@RequestMapping("/api/alimento")
public class AlimentoController {

    Logger log = LoggerFactory.getLogger(AlimentoController.class);

    @Autowired
    AlimentoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "alimento")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Alimento> alimentos = (busca == null)?
        
        repository.findAll(pageable):
        repository.findByNomeContaining(busca, pageable);
        return assembler.toModel(alimentos.map(Alimento::toEntityModel));
    }





    @PostMapping("/api/alimento")
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Alimento cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Alimento alimento){
        log.info("Cadastrando alimento" + alimento);
        repository.save(alimento);
        return ResponseEntity
        .created(alimento.toEntityModel().getRequiredLink("self").toUri())
        .body(alimento.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Alimento> show(@PathVariable Long id) {
        log.info("Detalhando alimento" + id);
        var alimento = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("alimento não encontrado"));

        return ResponseEntity.ok(alimento);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Alimento> destroy(@PathVariable Long id) {
        log.info("Apagando alimento" + id);
        var alimento = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, alimento não encontrado"));

        repository.delete(alimento);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Alimento> update(@PathVariable Long id, @RequestBody @Valid Alimento alimento){
        log.info("Atualizando alimento" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, alimento não encontrado"));

        alimento.setId(id);
        repository.save(alimento);

        return ResponseEntity.ok(alimento);
        
    }
}
