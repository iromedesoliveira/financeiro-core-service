package com.example.tecnologia.service;

import com.example.tecnologia.domain.Transacao;
import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.dto.TransacaoDTO;
import com.example.tecnologia.repository.TransacaoRepository;
import com.example.tecnologia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Transacao> listarTodas() {
		return repository.findAll();
	}

	// Novo: Busca por ID com tratamento de erro
	public Transacao buscarPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID: " + id));
	}

	// Novo: Deleção com verificação de existência
	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw new RuntimeException("Transação não encontrada para exclusão.");
		}
		repository.deleteById(id);
	}

	public Transacao salvar(TransacaoDTO dto) {
		Usuario usuario = usuarioRepository.findById(1L)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		Transacao transacao = Transacao.builder()
				.descricao(dto.descricao())
				.valor(dto.valor())
				.data(dto.data())
				.categoria(dto.categoria())
				.usuario(usuario)
				.build();

		return repository.save(transacao);
	}
}