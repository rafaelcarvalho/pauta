package io.github.rafaelcarvalho.pauta.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.github.rafaelcarvalho.pauta.infrastructure.entity.PautaEntity;
import io.github.rafaelcarvalho.pauta.infrastructure.entity.SessaoDeVotacaoEntity;

public interface SessaoDeVotacaoJpaRepository extends CrudRepository<SessaoDeVotacaoEntity, UUID> {

  Optional<SessaoDeVotacaoEntity> findByPauta(PautaEntity pauta);

}
