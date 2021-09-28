package io.github.rafaelcarvalho.pauta.domain.repository;

import java.util.Collection;
import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;

public interface PautaRepository {

  UUID salvar(Pauta pauta);

  Collection<Pauta> list(PautaStatus status);

}
