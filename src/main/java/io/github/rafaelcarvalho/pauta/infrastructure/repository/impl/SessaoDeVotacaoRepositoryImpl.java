package io.github.rafaelcarvalho.pauta.infrastructure.repository.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.rafaelcarvalho.pauta.domain.model.SessaoDeVotacao;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.repository.SessaoDeVotacaoRepository;
import io.github.rafaelcarvalho.pauta.infrastructure.entity.PautaEntity;
import io.github.rafaelcarvalho.pauta.infrastructure.entity.SessaoDeVotacaoEntity;
import io.github.rafaelcarvalho.pauta.infrastructure.repository.PautaJpaRepository;
import io.github.rafaelcarvalho.pauta.infrastructure.repository.SessaoDeVotacaoJpaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SessaoDeVotacaoRepositoryImpl
    implements
      SessaoDeVotacaoRepository {

  private final SessaoDeVotacaoJpaRepository SessaoDeVotacaoJpaRepository;
  private final PautaJpaRepository pautaJpaRepository;

  @Override
  public UUID salvar(UUID id, PautaStatus status, SessaoDeVotacao sessao) {
    
    PautaEntity pauta = pautaJpaRepository.findById(id).get();
    pauta.alterarStatus(status);
    
    SessaoDeVotacaoEntity entity = SessaoDeVotacaoEntity.builder()
        .duracaoEmMinutos(sessao.getDuracaoDaSessao())
        .pauta(pauta)
        .build();

    return SessaoDeVotacaoJpaRepository.save(entity).getId();
  }

  @Override
  public SessaoDeVotacao recuperarPelaPauta(UUID pauta) {
    PautaEntity entity = PautaEntity.builder().id(pauta).build();
    return SessaoDeVotacaoJpaRepository.findByPauta(entity)
        .map(s -> s.toDomain()).get();

  }

}
