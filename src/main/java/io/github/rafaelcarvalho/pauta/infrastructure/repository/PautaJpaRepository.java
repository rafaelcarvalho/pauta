package io.github.rafaelcarvalho.pauta.infrastructure.repository;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.infrastructure.entity.PautaEntity;

public interface PautaJpaRepository extends CrudRepository<PautaEntity, UUID> {

  Stream<PautaEntity> findByStatus(PautaStatus status);

}
