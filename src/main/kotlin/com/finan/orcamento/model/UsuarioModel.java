package com.finan.orcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="usuario")
public class UsuarioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="nome_usuario")
    private String nomeUsuario;

    @NotBlank
    @Column(name="nome_mae")
    private String nomeMae;

    @NotBlank
    @Column(name="cpf", unique = true)
    private String cpf;

    @NotBlank
    @Column(name="rg", unique = true)
    private String rg;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrcamentoModel> orcamentos = new ArrayList<>();

    public UsuarioModel() {}

    public UsuarioModel(Long id, String nomeUsuario, String nomeMae, String cpf, String rg, List<OrcamentoModel> orcamentos) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.nomeMae = nomeMae;
        this.cpf = cpf;
        this.rg = rg;
        this.orcamentos = orcamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<OrcamentoModel> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<OrcamentoModel> orcamentos) {
        this.orcamentos = orcamentos;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
