package com.finan.orcamento.service;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.model.FornecedorProdutoDTO;
import com.finan.orcamento.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    public FornecedorRepository fornecedorRepository;

    public FornecedorProdutoDTO montarFornecedorProdutoDTO;

    public FornecedorModel salvarFornecedor(FornecedorModel fornecedorModel) {
        return fornecedorRepository.save(fornecedorModel);
    }

    public List<FornecedorModel> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<FornecedorModel> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public void deletaFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}

