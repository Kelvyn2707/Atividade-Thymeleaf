package com.finan.orcamento.controller;

import com.finan.orcamento.model.ProdutoModel;
import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.service.ProdutoService;
import com.finan.orcamento.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping("/salvar")
    public String salvarProduto(
            @RequestParam Long fornecedorId,
            @RequestParam String nomeProduto,
            @RequestParam String codigoProduto,
            @RequestParam Double valorProduto,
            @RequestParam Double valorVendaProduto,
            @RequestParam Integer qtdProduto
    ) {
        FornecedorModel fornecedor = fornecedorService.buscarPorId(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        ProdutoModel produto = new ProdutoModel();
        produto.setNomeProduto(nomeProduto);
        produto.setCodigoProduto(codigoProduto);
        produto.setValorProduto(valorProduto);
        produto.setValorVendaProduto(valorVendaProduto);
        produto.setQtdProduto(qtdProduto);
        produto.setFornecedor(fornecedor);

        produtoService.cadastrarProduto(produto, fornecedorId);

        return "redirect:/fornecedores";
    }
}
