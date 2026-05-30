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

	public Transacao salvar(TransacaoDTO dto) {
		// 1. Busca um usuário padrão para garantir a integridade (ForeignKey)
		Usuario usuario = usuarioRepository.findById(1L)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		// 2. Constrói a transação usando o padrão Builder (Lombok)
		Transacao transacao = Transacao.builder()
				.descricao(dto.descricao())
				.valor(dto.valor())
				.data(dto.data())
				.categoria(dto.categoria())
				.usuario(usuario) // Vinculando a entidade Usuario
				.build();

		return repository.save(transacao);
	}
}