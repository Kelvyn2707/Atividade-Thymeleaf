package com.finan.orcamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class ProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_produto")
    private String nomeProduto;

    @NotBlank
    @Column(name = "codigo_produto", unique = true)
    private String codigoProduto;

    @Column(name = "valor_produto")
    private Double valorProduto;

    @Column(name = "valor_venda_produto")
    private Double valorVendaProduto;

    @Column(name = "qtd_produto")
    private Integer qtdProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorModel fornecedor;

    public ProdutoModel() {}

    public ProdutoModel(Long id, String nomeProduto, String codigoProduto, Double valorProduto, Double valorVendaProduto, Integer qtdProduto, FornecedorModel fornecedor) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.codigoProduto = codigoProduto;
        this.valorProduto = valorProduto;
        this.valorVendaProduto = valorVendaProduto;
        this.qtdProduto = qtdProduto;
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }
    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Double getValorVendaProduto() {
        return valorVendaProduto;
    }
    public void setValorVendaProduto(Double valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }
    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdutoModel that = (ProdutoModel) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
