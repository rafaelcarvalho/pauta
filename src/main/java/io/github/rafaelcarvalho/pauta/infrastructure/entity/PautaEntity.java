package io.github.rafaelcarvalho.pauta.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
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
@Table(name = "pauta")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PautaEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  @Include
  @Column(updatable = false)
  UUID id;
  @NotNull
  String assunto;
  @NotNull
  @Enumerated(EnumType.STRING)
  PautaStatus status;
  @OneToMany(mappedBy = "pauta")
  List<ItemEntity> itens;

  public void adicionarItem(String texto) {
    if (itens == null) {
      itens = new ArrayList<>();
    }
    ItemEntity item = ItemEntity.builder().id(UUID.randomUUID()).pauta(this)
        .texto(texto).build();

    itens.add(item);
  }

  public void alterarStatus(PautaStatus status) {
    this.status = status;
  }

}
