package io.github.rafaelcarvalho.pauta.facade;

import java.util.Collection;
import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.SimNao;

public interface PautaFacade {

  UUID criar(Pauta pauta);

  void encerrarPauta(UUID pauta);

  Collection<Pauta> listarPautas(PautaStatus status);

  UUID abrirSessao(UUID pauta, Long duracaoEmMinutos);

  UUID votar(UUID id, String cpf, SimNao voto);

}
