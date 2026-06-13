package com.example.tecnologia.service;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.model.Lucro;
import com.example.tecnologia.dto.LancamentoDTO;
import com.example.tecnologia.repository.LancamentoRepository;
import com.example.tecnologia.repository.UsuarioRepository;
import com.example.tecnologia.repository.LucroRepository;
import com.example.tecnologia.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LucroRepository lucroRepository;

    // --- MÉTODOS DE CONVERSÃO ---

    private LancamentoDTO toDTO(Lancamento entity) {
        return LancamentoDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .dataVencimento(entity.getDataVencimento())
                .valor(entity.getValor())
                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .build();
    }

    private Lancamento toEntity(LancamentoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        return Lancamento.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .dataVencimento(dto.getDataVencimento())
                .valor(dto.getValor())
                .usuario(usuario)
                .build();
    }

    // --- CRUD ---

    @Transactional
    public LancamentoDTO salvar(LancamentoDTO dto) {
        Lancamento entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public List<LancamentoDTO> listarTodosDTO() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public LancamentoDTO buscarPorIdDTO(Long id) {
        Lancamento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento ID " + id + " não encontrado!"));
        return toDTO(entity);
    }

    @Transactional
    public LancamentoDTO atualizar(Long id, LancamentoDTO dto) {
        Lancamento salvo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível atualizar: ID " + id + " não encontrado!"));

        salvo.setDescricao(dto.getDescricao());
        salvo.setValor(dto.getValor());
        salvo.setDataVencimento(dto.getDataVencimento());

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
            salvo.setUsuario(usuario);
        }

        return toDTO(repository.save(salvo));
    }

    @Transactional
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível remover: ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }

    // --- LÓGICA DE NEGÓCIO ---

    @Transactional
    public void realizarDistribuicaoInvestimento(Long usuarioId, BigDecimal valorTotal) {
        BigDecimal trintaPorcento = new BigDecimal("0.30");
        BigDecimal quarentaPorcento = new BigDecimal("0.40");

        BigDecimal reserva = valorTotal.multiply(trintaPorcento);
        BigDecimal dividendos = valorTotal.multiply(trintaPorcento);
        BigDecimal dayTrade = valorTotal.multiply(quarentaPorcento);

        salvar(new LancamentoDTO(null, "Reserva de Emergência", LocalDate.now(), reserva, usuarioId));
        salvar(new LancamentoDTO(null, "Ações para Dividendos", LocalDate.now(), dividendos, usuarioId));
        salvar(new LancamentoDTO(null, "Capital para Day Trade", LocalDate.now(), dayTrade, usuarioId));

        Lucro novoLucro = new Lucro();
        novoLucro.setValorOriginal(valorTotal);
        novoLucro.setSavings(reserva);
        novoLucro.setDividendos(dividendos);
        novoLucro.setReinvestimento(dayTrade);
        novoLucro.setDataRegistro(LocalDateTime.now());

        lucroRepository.save(novoLucro); // Isso vai gerar o INSERT na tabela LUCROS
    }

}