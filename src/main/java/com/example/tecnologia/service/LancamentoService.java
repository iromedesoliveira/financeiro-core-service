package com.example.tecnologia.service;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.domain.Usuario; // Adicione este import
import com.example.tecnologia.dto.LancamentoDTO;
import com.example.tecnologia.repository.LancamentoRepository;
import com.example.tecnologia.repository.UsuarioRepository; // Adicione este import
import com.example.tecnologia.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal; // Necessário para o cálculo financeiro
import java.time.LocalDate; // Necessário para a data do lançamento
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Injeção do repositório de Usuário

    // Converte Entidade para DTO
    private LancamentoDTO toDTO(Lancamento entity) {
        return LancamentoDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .dataVencimento(entity.getDataVencimento())
                .valor(entity.getValor())
                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .build();
    }

    // Converte DTO para Entidade (Agora com associação de Usuario)
    private Lancamento toEntity(LancamentoDTO dto) {
        // Busca o usuário pelo ID vindo do DTO
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        return Lancamento.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .dataVencimento(dto.getDataVencimento())
                .valor(dto.getValor())
                .usuario(usuario) // Define o objeto Usuario na Entidade
                .build();
    }

    public LancamentoDTO salvar(LancamentoDTO dto) {
        Lancamento entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    // ... (restante dos métodos: listarTodosDTO, buscarPorIdDTO, atualizar,
    // remover)
    // Dica: No método atualizar, lembre-se de também buscar o novo usuário se o
    // usuarioId mudar!

    public List<LancamentoDTO> listarTodosDTO() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public LancamentoDTO buscarPorIdDTO(Long id) {
        Lancamento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento ID " + id + " não encontrado!"));
        return toDTO(entity);
    }

    public LancamentoDTO atualizar(Long id, LancamentoDTO dto) {
        Lancamento salvo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível atualizar: ID " + id + " não encontrado!"));

        salvo.setDescricao(dto.getDescricao());
        salvo.setValor(dto.getValor());
        salvo.setDataVencimento(dto.getDataVencimento());

        // Se desejar atualizar o usuário também:
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
            salvo.setUsuario(usuario);
        }

        return toDTO(repository.save(salvo));
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível remover: ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }

    // Adicione este método ao seu LancamentoService
    public void realizarDistribuicaoInvestimento(Long usuarioId, BigDecimal valorTotal) {
        BigDecimal trintaPorcento = new BigDecimal("0.30");
        BigDecimal quarentaPorcento = new BigDecimal("0.40");

        // 1. Cálculos
        BigDecimal reserva = valorTotal.multiply(trintaPorcento);
        BigDecimal dividendos = valorTotal.multiply(trintaPorcento);
        BigDecimal dayTrade = valorTotal.multiply(quarentaPorcento);

        // 2. Criar os DTOs ou Entidades (Assumindo que você quer salvar como
        // Lancamentos)
        salvar(new LancamentoDTO(null, "Reserva de Emergência", LocalDate.now(), reserva, usuarioId));
        salvar(new LancamentoDTO(null, "Ações para Dividendos", LocalDate.now(), dividendos, usuarioId));
        salvar(new LancamentoDTO(null, "Capital para Day Trade", LocalDate.now(), dayTrade, usuarioId));

        System.out.println("Distribuição de R$ " + valorTotal + " realizada com sucesso!");
    }
}