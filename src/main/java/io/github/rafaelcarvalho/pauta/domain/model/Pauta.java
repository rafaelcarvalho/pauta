package io.github.rafaelcarvalho.pauta.domain.model;

import java.util.List;
import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor  
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class Pauta {

  UUID id;
  String assunto;
  PautaStatus status;
  List<String> itens;

  public Pauta(String assunto, List<String> itens) {
    this.assunto = assunto;
    this.status = PautaStatus.criada;
    this.itens = itens;
  }

  public void alterarStatus(PautaStatus status) {
    this.status = status;
  }

}