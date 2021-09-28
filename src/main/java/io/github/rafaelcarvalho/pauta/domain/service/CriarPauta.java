package io.github.rafaelcarvalho.pauta.domain.service;

import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;

public interface CriarPauta {
  
  UUID criar(Pauta pauta);

}
