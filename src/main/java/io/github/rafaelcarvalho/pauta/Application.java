package io.github.rafaelcarvalho.pauta;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

  static final String topicExchangeName = "pauta-exchange";
  static final String queueName = "pauta-fechada";

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("pauta.encerrada.#");
  }

  // @Bean
  // SimpleMessageListenerContainer container(ConnectionFactory
  // connectionFactory,
  // MessageListenerAdapter listenerAdapter) {
  // SimpleMessageListenerContainer container = new
  // SimpleMessageListenerContainer();
  // container.setConnectionFactory(connectionFactory);
  // container.setQueueNames(queueName);
  // container.setMessageListener(listenerAdapter);
  // return container;
  // }

  // @Bean
  // MessageListenerAdapter listenerAdapter(Receiver receiver) {
  // return new MessageListenerAdapter(receiver, "receiveMessage");
  // }

}
