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
import megat.api.models.Monitoramento;
import megat.api.repositories.MonitoramentoRepository;


@RestController
@Slf4j
@RequestMapping("/api/monitoramento")
public class MonitoramentoController {

    Logger log = LoggerFactory.getLogger(MonitoramentoController.class);

    @Autowired
    MonitoramentoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "monitoramento")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Monitoramento> monitoramentos = repository.findAll(pageable);
        
        return assembler.toModel(monitoramentos.map(Monitoramento::toEntityModel));
    }





    @PostMapping("/api/monitoramento")
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Monitoramento cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Monitoramento monitoramento){
        log.info("Cadastrando monitoramento" + monitoramento);
        repository.save(monitoramento);
        return ResponseEntity
        .created(monitoramento.toEntityModel().getRequiredLink("self").toUri())
        .body(monitoramento.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Monitoramento> show(@PathVariable Long id) {
        log.info("Detalhando monitoramento" + id);
        var monitoramento = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("monitoramento não encontrado"));

        return ResponseEntity.ok(monitoramento);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Monitoramento> destroy(@PathVariable Long id) {
        log.info("Apagando monitoramento" + id);
        var monitoramento = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, monitoramento não encontrado"));

        repository.delete(monitoramento);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Monitoramento> update(@PathVariable Long id, @RequestBody @Valid Monitoramento monitoramento){
        log.info("Atualizando monitoramento" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, monitoramento não encontrado"));

        monitoramento.setId(id);
        repository.save(monitoramento);

        return ResponseEntity.ok(monitoramento);
        
    }
}

