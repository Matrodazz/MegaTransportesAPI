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
import megat.api.models.Empresa;
import megat.api.repositories.EmpresaRepository;


@RestController
@Slf4j
@RequestMapping("/api/empresa")
public class EmpresaController {

    Logger log = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    EmpresaRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "empresa")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Empresa> empresas = (busca == null)?
        
        repository.findAll(pageable):
        repository.findByNomeContaining(busca, pageable);
        return assembler.toModel(empresas.map(Empresa::toEntityModel));
    }





    @PostMapping("/api/empresa")
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Empresa cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Empresa empresa){
        log.info("Cadastrando empresa" + empresa);
        repository.save(empresa);
        return ResponseEntity
        .created(empresa.toEntityModel().getRequiredLink("self").toUri())
        .body(empresa.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Empresa> show(@PathVariable Long id) {
        log.info("Detalhando empresa" + id);
        var empresa = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("empresa não encontrada"));

        return ResponseEntity.ok(empresa);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Empresa> destroy(@PathVariable Long id) {
        log.info("Apagando empresa" + id);
        var empresa = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, empresa não encontrada"));

        repository.delete(empresa);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestBody @Valid Empresa empresa){
        log.info("Atualizando empresa" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, empresa não encontrada"));

        empresa.setId(id);
        repository.save(empresa);

        return ResponseEntity.ok(empresa);
        
    }
}

