package io.github.rafaelcarvalho.pauta.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.rafaelcarvalho.pauta.domain.repository.PautaRepository;
import io.github.rafaelcarvalho.pauta.domain.service.impl.PautaService;

@Configuration
public class PautaConfigService {

  @Bean
  public PautaService pautaService(PautaRepository pautaRepository) {
    return new PautaService(pautaRepository);
  }

}
