package io.github.rafaelcarvalho.pauta.domain.service;

import java.util.Collection;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;

public interface RecuperarPautas {

  Collection<Pauta> listar(PautaStatus status);

}
