package com.finan.orcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fornecedor")
public class FornecedorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @NotBlank
    @Column(name = "razao_social")
    private String razaoSocial;

    @NotBlank
    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @NotBlank
    @Column(name = "instituicao_social")
    private String instituicaoSocial;

    @JsonIgnore
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProdutoModel> produtos = new ArrayList<>();

    public FornecedorModel() {}

    public FornecedorModel(Long id, String nomeFantasia, String razaoSocial, String cnpj, String instituicaoSocial, List<ProdutoModel> produtos) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.instituicaoSocial = instituicaoSocial;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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

    public String getInstituicaoSocial() {
        return instituicaoSocial;
    }
    public void setInstituicaoSocial(String instituicaoSocial) {
        this.instituicaoSocial = instituicaoSocial;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FornecedorModel that = (FornecedorModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
