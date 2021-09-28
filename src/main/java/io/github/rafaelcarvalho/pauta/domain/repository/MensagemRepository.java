package io.github.rafaelcarvalho.pauta.domain.repository;

import java.util.UUID;

public interface MensagemRepository {
  
  void enviar(UUID pauta);

}
