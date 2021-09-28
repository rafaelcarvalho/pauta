package io.github.rafaelcarvalho.pauta.infrastructure.repository.impl;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import io.github.rafaelcarvalho.pauta.domain.repository.MensagemRepository;

@Component
public class RabbitRepository implements MensagemRepository {

  private final RabbitTemplate template;

  public RabbitRepository(RabbitTemplate template) {
    this.template = template;
  }

  @Override
  public void enviar(UUID pauta) {
    template.convertAndSend(pauta.toString(),
        "pauta.encerrada." + pauta.toString());

  }

}
