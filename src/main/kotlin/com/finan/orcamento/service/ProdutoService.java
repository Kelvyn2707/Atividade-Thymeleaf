package com.finan.orcamento.service;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.model.ProdutoModel;
import com.finan.orcamento.repositories.FornecedorRepository;
import com.finan.orcamento.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public ProdutoModel cadastrarProduto(ProdutoModel produto, Long fornecedorId) {
        FornecedorModel fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        produto.setFornecedor(fornecedor);
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public List<ProdutoModel> listarProdutosPorFornecedor(Long fornecedorId) {
        return produtoRepository.findByFornecedorId(fornecedorId);
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoModel produtoAtualizado) {
        ProdutoModel produtoExistente = buscarProdutoPorId(id);

        produtoExistente.setNomeProduto(produtoAtualizado.getNomeProduto());
        produtoExistente.setCodigoProduto(produtoAtualizado.getCodigoProduto());
        produtoExistente.setValorProduto(produtoAtualizado.getValorProduto());
        produtoExistente.setValorVendaProduto(produtoAtualizado.getValorVendaProduto());
        produtoExistente.setQtdProduto(produtoAtualizado.getQtdProduto());

        return produtoRepository.save(produtoExistente);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}

