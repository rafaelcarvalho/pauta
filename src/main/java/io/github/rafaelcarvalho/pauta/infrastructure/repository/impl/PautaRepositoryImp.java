package io.github.rafaelcarvalho.pauta.infrastructure.repository.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.repository.PautaRepository;
import io.github.rafaelcarvalho.pauta.infrastructure.entity.PautaEntity;
import io.github.rafaelcarvalho.pauta.infrastructure.repository.PautaJpaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PautaRepositoryImp implements PautaRepository {

  private final PautaJpaRepository pautaJpaRepository;

  @Override
  public UUID salvar(Pauta pauta) {
    PautaEntity p = PautaEntity.builder().assunto(pauta.getAssunto()).status(PautaStatus.criada).build();

    pauta.getItens().forEach(p::adicionarItem);

    return pautaJpaRepository.save(p).getId();
  }

  @Override
  public Collection<Pauta> list(PautaStatus status) {
    return pautaJpaRepository.findByStatus(status).map(e -> {
      List<String> itens = e.getItens().stream().map(i -> i.getTexto())
          .collect(Collectors.toList());
      return new Pauta(e.getId(), e.getAssunto(), e.getStatus(), itens);
    }).collect(Collectors.toList());
  }

}