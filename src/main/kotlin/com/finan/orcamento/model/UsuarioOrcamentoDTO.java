package com.finan.orcamento.model;

import com.finan.orcamento.model.enums.IcmsEstados;
import java.math.BigDecimal;

public class UsuarioOrcamentoDTO {

    private String nomeUsuario;
    private String cpf;
    private String rg;
    private String nomeMae;
    private BigDecimal valorOrcamento;
    private IcmsEstados icmsEstados;

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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

    public String getNomeMae() {
        return nomeMae;
    }
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public BigDecimal getValorOrcamento() {
        return valorOrcamento;
    }
    public void setValorOrcamento(BigDecimal valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public IcmsEstados getIcmsEstados() {
        return icmsEstados;
    }
    public void setIcmsEstados(IcmsEstados icmsEstados) {
        this.icmsEstados = icmsEstados;
    }
}
