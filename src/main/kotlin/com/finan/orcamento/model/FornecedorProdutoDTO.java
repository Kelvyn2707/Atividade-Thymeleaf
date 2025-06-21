package com.finan.orcamento.model;

import java.util.List;

public class FornecedorProdutoDTO {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private List<ProdutoModel> produtos;

    public String getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
