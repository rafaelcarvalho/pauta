package io.github.rafaelcarvalho.pauta.infrastructure.repository.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.rafaelcarvalho.pauta.domain.repository.CpfRepository;

@Component
public class CpfRepositoryImpl implements CpfRepository {

  private static final String uri = "https://user-info.herokuapp.com/users/";

  @Override
  public void verificar(String cpf) {
    RestTemplate rest = new RestTemplate();
    ResponseEntity<String> resultado = rest.getForEntity(uri + cpf,
        String.class);
    if (resultado.getStatusCode().is4xxClientError()) {
      throw new RuntimeException("nao encontrado");
    }
    if (resultado.getBody().contains("UNABLE_TO_VOTE")) {
      throw new RuntimeException("nao pode");
    }
  }

}
