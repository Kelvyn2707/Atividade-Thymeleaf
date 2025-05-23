package com.finan.orcamento.controller;

import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping
    public ResponseEntity<List<OrcamentoModel>> buscaTodosOrcamentos() {
        return ResponseEntity.ok(orcamentoService.buscarCadastro());
    }

    @GetMapping("/pesquisaid/{id}")
    public ResponseEntity<OrcamentoModel> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(orcamentoService.buscaId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrcamentoModel> cadastraOrcamento(@RequestBody OrcamentoModel orcamentoModel) {
        return ResponseEntity.ok(orcamentoService.cadastrarOrcamento(orcamentoModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrcamentoModel> atualizaOrcamento(@RequestBody OrcamentoModel orcamentoModel, @PathVariable Long id) {
        OrcamentoModel orcamentoNewObj = orcamentoService.atualizaCadastro(orcamentoModel, id);
        return ResponseEntity.ok().body(orcamentoNewObj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrcamento(@PathVariable Long id) {
        orcamentoService.deletaOrcamento(id);
        return ResponseEntity.noContent().build();
    }
}
