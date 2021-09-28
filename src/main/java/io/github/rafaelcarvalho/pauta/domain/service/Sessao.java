package io.github.rafaelcarvalho.pauta.domain.service;

import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.enumeration.SimNao;

public interface Sessao {

  UUID abrir(UUID id, Long duracao);

  void fechar(UUID id);

  UUID votar(UUID id, SimNao voto, String cpf);

}
