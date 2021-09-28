package io.github.rafaelcarvalho.pauta.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.SessaoDeVotacao;
import io.github.rafaelcarvalho.pauta.domain.model.Voto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "sessao_de_votacao")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class SessaoDeVotacaoEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(updatable = false)
  @Include
  UUID id;
  @ManyToOne
  @NotNull
  PautaEntity pauta;
  @NotNull
  LocalDateTime duracaoEmMinutos;
  @OneToMany(mappedBy = "sessaoDeVotacao")
  List<VotoEntity> votos;

  public SessaoDeVotacao toDomain() {
    Pauta p = new Pauta(pauta.getAssunto(), pauta.getItens().stream()
        .map(i -> i.getTexto()).collect(Collectors.toList()));
    List<Voto> vts = votos.stream()
        .map(v -> new Voto(id, v.getVoto(), v.getUsuario()))
        .collect(Collectors.toList());
    return new SessaoDeVotacao(id, p, duracaoEmMinutos, vts);
  }

}
