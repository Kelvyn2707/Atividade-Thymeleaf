package com.finan.orcamento.controller;

import com.finan.orcamento.model.FornecedorModel;
import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.model.UsuarioOrcamentoDTO;
import com.finan.orcamento.model.enums.IcmsEstados;
import com.finan.orcamento.repositories.OrcamentoRepository;
import com.finan.orcamento.repositories.UsuarioRepository;
import com.finan.orcamento.service.FornecedorService;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.finan.orcamento.model.OrcamentoModel;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public String getUsuarioPage(Model model) {

        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios);

        List<FornecedorModel> fornecedores = fornecedorService.listarTodos();

        fornecedores.forEach(fornecedor -> fornecedor.getProdutos().size());
        model.addAttribute("fornecedores", fornecedores);

        model.addAttribute("usuarioModel", new UsuarioModel());
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        return "usuarioPage";
    }

    @PostMapping("/salvarOrcamentoExistente/{usuarioId}")
    @ResponseBody
    public ResponseEntity<UsuarioModel> adicionarOrcamentoAoUsuarioExistente(
            @PathVariable Long usuarioId,
            @RequestBody OrcamentoModel orcamentoModel
    ) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        orcamentoModel.setUsuario(usuario);
        orcamentoModel.calcularIcms();

        usuario.getOrcamentos().add(orcamentoModel);
        UsuarioModel salvo = usuarioRepository.save(usuario); // salva em cascata

        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/salvarOrcamentoExistente")
    public String adicionarOrcamentoAoUsuarioExistente(
            @RequestParam Long usuarioId,
            @RequestParam BigDecimal valorOrcamento,
            @RequestParam IcmsEstados icmsEstados,
            RedirectAttributes redirectAttributes
    ) {
        UsuarioModel usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        OrcamentoModel orcamentoModel = new OrcamentoModel();
        orcamentoModel.setValorOrcamento(valorOrcamento);
        orcamentoModel.setIcmsEstados(icmsEstados);
        orcamentoModel.setUsuario(usuario);
        orcamentoModel.calcularIcms();

        usuario.getOrcamentos().add(orcamentoModel);
        UsuarioModel salvo = usuarioRepository.save(usuario);

        redirectAttributes.addFlashAttribute("mensagem", "Orçamento salvo com sucesso!");

        return "redirect:/usuarios";
    }
    @PostMapping("/salvarOrcamento")
    @ResponseBody
    public ResponseEntity<UsuarioModel> salvarUsuarioComOrcamento(
            @RequestBody UsuarioOrcamentoDTO usuarioOrcamentoDTO
    ) {
        // Cria novo usuário
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNomeUsuario(usuarioOrcamentoDTO.getNomeUsuario());
        usuario.setCpf(usuarioOrcamentoDTO.getCpf());
        usuario.setRg(usuarioOrcamentoDTO.getRg());
        usuario.setNomeMae(usuarioOrcamentoDTO.getNomeMae());

        // Cria novo orçamento
        OrcamentoModel orcamento = new OrcamentoModel();
        orcamento.setValorOrcamento(usuarioOrcamentoDTO.getValorOrcamento());
        orcamento.setIcmsEstados(usuarioOrcamentoDTO.getIcmsEstados());
        orcamento.setUsuario(usuario);
        orcamento.calcularIcms();

        // Adiciona o orçamento ao usuário
        usuario.getOrcamentos().add(orcamento);

        // Salva o usuário (em cascata salva o orçamento)
        UsuarioModel salvo = usuarioRepository.save(usuario);

        return ResponseEntity.ok(salvo);
    }


    @GetMapping("pesquisa")
    public String listarUsuarios(Model model) {
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario(); // <- Deve incluir os orçamentos
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioModel", new UsuarioModel());
        model.addAttribute("orcamentoModel", new OrcamentoModel());
        return "usuarioPage";
    }
}