package io.github.rafaelcarvalho.pauta.domain.service.impl;

import java.util.Collection;
import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.repository.PautaRepository;
import io.github.rafaelcarvalho.pauta.domain.service.CriarPauta;
import io.github.rafaelcarvalho.pauta.domain.service.RecuperarPautas;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PautaService implements CriarPauta, RecuperarPautas {

  private final PautaRepository pautaRepository;

  @Override
  public UUID criar(Pauta pauta) {
    return pautaRepository.salvar(pauta);
  }

  @Override
  public Collection<Pauta> listar(PautaStatus status) {
    return pautaRepository.list(status);
  }

}