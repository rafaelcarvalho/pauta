package io.github.rafaelcarvalho.pauta.infrastructure.job;

import org.springframework.stereotype.Component;

import io.github.rafaelcarvalho.pauta.facade.PautaFacade;

@Component
public class EncerrarVotacaoJob {

  private final PautaFacade facade;

  public EncerrarVotacaoJob(PautaFacade facade) {
    this.facade = facade;
  }

//  @Scheduled(fixedDelay = 60000)
  public void execute() {
//    facade.listarPautasAbertas().stream().forEach(facade::encerrarPauta);
  }

}
