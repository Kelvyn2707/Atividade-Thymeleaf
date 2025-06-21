package com.finan.orcamento.controller;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.model.ProdutoModel;
import com.finan.orcamento.service.FornecedorService;
import com.finan.orcamento.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public String listar(Model model) {
        List<FornecedorModel> fornecedores = fornecedorService.listarTodos();
        model.addAttribute("fornecedores", fornecedores);
        return "usuarioPage.html";
    }

    @PostMapping("/salvarComProduto")
    public String salvarComProduto(
        @RequestParam String nomeFantasia,
        @RequestParam String razaoSocial,
        @RequestParam String cnpj,
        @RequestParam String instituicaoSocial,
        @RequestParam String nomeProduto,
        @RequestParam String codigoProduto,
        @RequestParam Double valorProduto,
        @RequestParam Double valorVendaProduto,
        @RequestParam Integer qtdProduto
    ) {
        FornecedorModel fornecedor = new FornecedorModel();
        fornecedor.setNomeFantasia(nomeFantasia);
        fornecedor.setRazaoSocial(razaoSocial);
        fornecedor.setCnpj(cnpj);
        fornecedor.setInstituicaoSocial(instituicaoSocial);

        FornecedorModel fornecedorSalvo = fornecedorService.salvarFornecedor(fornecedor);

        ProdutoModel produto = new ProdutoModel();
        produto.setNomeProduto(nomeProduto);
        produto.setCodigoProduto(codigoProduto);
        produto.setValorProduto(valorProduto);
        produto.setValorVendaProduto(valorVendaProduto);
        produto.setQtdProduto(qtdProduto);
        produto.setFornecedor(fornecedorSalvo);

        produtoService.cadastrarProduto(produto, fornecedorSalvo.getId());

        return "redirect:/usuarios";
    }
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute FornecedorModel fornecedor) {
        fornecedorService.salvarFornecedor(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FornecedorModel buscarPorId(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id).orElse(null);
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        fornecedorService.deletaFornecedor(id);
        return "redirect:/fornecedores";
    }
}
