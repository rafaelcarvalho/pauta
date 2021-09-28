package io.github.rafaelcarvalho.pauta.facade.impl;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.SimNao;
import io.github.rafaelcarvalho.pauta.domain.service.CriarPauta;
import io.github.rafaelcarvalho.pauta.domain.service.RecuperarPautas;
import io.github.rafaelcarvalho.pauta.domain.service.Sessao;
import io.github.rafaelcarvalho.pauta.facade.PautaFacade;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
class PautaFacadeImpl implements PautaFacade {

  private final CriarPauta criarPauta;
  private final RecuperarPautas recuperarPautas;
  private final Sessao sessao;

  @Transactional
  @Override
  public UUID criar(Pauta pauta) {
    return criarPauta.criar(pauta);
  }

  @Transactional
  @Override
  public void encerrarPauta(UUID pauta) {
    sessao.fechar(pauta);
  }

  @Override
  public Collection<Pauta> listarPautas(PautaStatus status) {
    return recuperarPautas.listar(status);
  }

  @Override
  public UUID abrirSessao(UUID pauta, Long duracao) {
    return sessao.abrir(pauta, duracao);
  }

  @Override
  @Transactional
  public UUID votar(UUID id, String cpf, SimNao voto) {
    return sessao.votar(id, voto, cpf);
  }

}
