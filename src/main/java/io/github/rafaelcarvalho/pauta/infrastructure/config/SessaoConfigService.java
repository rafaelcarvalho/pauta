package io.github.rafaelcarvalho.pauta.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.rafaelcarvalho.pauta.domain.repository.CpfRepository;
import io.github.rafaelcarvalho.pauta.domain.repository.MensagemRepository;
import io.github.rafaelcarvalho.pauta.domain.repository.SessaoDeVotacaoRepository;
import io.github.rafaelcarvalho.pauta.domain.service.impl.SessaoDeVotacaoService;

@Configuration
public class SessaoConfigService {

  @Bean
  public SessaoDeVotacaoService sessaoDeVotacaoService(
      SessaoDeVotacaoRepository sessaoDeVotacaoRepository,
      MensagemRepository mensagemRepository, CpfRepository cpfRepository) {
    return new SessaoDeVotacaoService(sessaoDeVotacaoRepository,
        mensagemRepository, cpfRepository);
  }

}
