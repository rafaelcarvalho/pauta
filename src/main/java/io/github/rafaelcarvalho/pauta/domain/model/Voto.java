package io.github.rafaelcarvalho.pauta.domain.model;

import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.enumeration.SimNao;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class Voto {

  UUID id;
  UUID idSessao;
  SimNao voto;
  UUID usuario;

  public Voto(UUID idSessao, SimNao voto, UUID usuario) {
    this.idSessao = idSessao;
    this.voto = voto;
    this.usuario = usuario;
  }

}
