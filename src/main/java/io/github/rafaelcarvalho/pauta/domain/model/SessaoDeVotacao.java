package io.github.rafaelcarvalho.pauta.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.SimNao;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor  
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class SessaoDeVotacao {

  UUID id;
  Pauta pauta;
  LocalDateTime duracaoDaSessao;
  @JsonIgnore
  List<Voto> votos;
  Long votoSim;
  Long votosNao;

  public SessaoDeVotacao(Long duracaoEmMinutos) {
    if (duracaoEmMinutos > 0) {
      this.duracaoDaSessao = LocalDateTime.now().plusMinutes(duracaoEmMinutos);
    } else {
      this.duracaoDaSessao = LocalDateTime.now().plusMinutes(1);
    }
    this.votos = new ArrayList<>();
  }

  public boolean fecharSeASessaoEstiverEncerrada() {
    if (LocalDateTime.now().isAfter(duracaoDaSessao)) {
      this.pauta.alterarStatus(PautaStatus.fechada);
      this.votoSim = votos.stream()
          .filter(vote -> vote.getVoto().equals(SimNao.sim)).count();
      this.votosNao = votos.stream()
          .filter(vote -> vote.getVoto().equals(SimNao.nao)).count();

      return true;
    }

    return false;
  }

  public void adicionarVoto(Voto voto) {
    validar(voto);
    votos.add(voto);
  }

  private void validar(Voto voto) {
    boolean invalido = votos.stream()
        .anyMatch(v -> v.getUsuario().equals(voto.getUsuario()));
    if (invalido) {
      throw new RuntimeException();
    }

    if (LocalDateTime.now().isAfter(duracaoDaSessao)) {
      throw new RuntimeException();
    }

  }

  public SessaoDeVotacao(UUID id, Pauta pauta, LocalDateTime duracaoDaSessao,
      List<Voto> votos) {
    this.id = id;
    this.pauta = pauta;
    this.duracaoDaSessao = duracaoDaSessao;
    this.votos = votos;
  }

}