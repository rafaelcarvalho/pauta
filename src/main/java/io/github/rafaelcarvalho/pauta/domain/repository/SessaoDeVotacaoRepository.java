package io.github.rafaelcarvalho.pauta.domain.repository;

import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.SessaoDeVotacao;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;

public interface SessaoDeVotacaoRepository {

  SessaoDeVotacao recuperarPelaPauta(UUID pauta);

  UUID salvar(UUID id, PautaStatus status, SessaoDeVotacao sessao);

}
