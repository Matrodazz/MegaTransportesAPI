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
import megat.api.models.Veiculo;
import megat.api.repositories.VeiculoRepository;


@RestController
@Slf4j
@RequestMapping("/api/veiculo")
public class VeiculoController {

    Logger log = LoggerFactory.getLogger(VeiculoController.class);

    @Autowired
    VeiculoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")
    @Tag(name = "veiculo")


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<Veiculo> veiculos = (busca == null)?
        
        repository.findAll(pageable):
        repository.findByPlacaContaining(busca, pageable);
        return assembler.toModel(veiculos.map(Veiculo::toEntityModel));
    }





    @PostMapping
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Veiculo cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Veiculo veiculo){
        log.info("Cadastrando veiculo" + veiculo);
        repository.save(veiculo);
        return ResponseEntity
        .created(veiculo.toEntityModel().getRequiredLink("self").toUri())
        .body(veiculo.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Veiculo> show(@PathVariable Long id) {
        log.info("Detalhando veiculo" + id);
        var veiculo = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("veiculo não encontrado"));

        return ResponseEntity.ok(veiculo);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Veiculo> destroy(@PathVariable Long id) {
        log.info("Apagando veiculo" + id);
        var veiculo = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, veiculo não encontrado"));

        repository.delete(veiculo);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Veiculo> update(@PathVariable Long id, @RequestBody @Valid Veiculo veiculo){
        log.info("Atualizando veiculo" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, veiculo não encontrado"));

        veiculo.setId(id);
        repository.save(veiculo);

        return ResponseEntity.ok(veiculo);
        
    }
}
