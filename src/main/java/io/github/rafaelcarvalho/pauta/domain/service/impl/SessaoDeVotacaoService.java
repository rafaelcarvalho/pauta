package io.github.rafaelcarvalho.pauta.domain.service.impl;

import java.util.UUID;

import io.github.rafaelcarvalho.pauta.domain.model.SessaoDeVotacao;
import io.github.rafaelcarvalho.pauta.domain.model.Voto;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.SimNao;
import io.github.rafaelcarvalho.pauta.domain.repository.CpfRepository;
import io.github.rafaelcarvalho.pauta.domain.repository.MensagemRepository;
import io.github.rafaelcarvalho.pauta.domain.repository.SessaoDeVotacaoRepository;
import io.github.rafaelcarvalho.pauta.domain.service.Sessao;

public class SessaoDeVotacaoService implements Sessao {

  private final SessaoDeVotacaoRepository sessaoDeVotacaoRepository;
  private final MensagemRepository mensagemRepository;
  private final CpfRepository cpfRepository;

  public SessaoDeVotacaoService(
      SessaoDeVotacaoRepository sessaoDeVotacaoRepository,
      MensagemRepository mensagemRepository, CpfRepository cpfRepository) {
    this.sessaoDeVotacaoRepository = sessaoDeVotacaoRepository;
    this.mensagemRepository = mensagemRepository;
    this.cpfRepository = cpfRepository;
  }

  @Override
  public UUID abrir(UUID id, Long duracao) {
    SessaoDeVotacao s = new SessaoDeVotacao(duracao);
    return sessaoDeVotacaoRepository.salvar(id, PautaStatus.aberta, s);
  }

  @Override
  public void fechar(UUID pauta) {
    SessaoDeVotacao sessao = sessaoDeVotacaoRepository
        .recuperarPelaPauta(pauta);
    boolean podeEncerrar = sessao.fecharSeASessaoEstiverEncerrada();
    if (podeEncerrar) {
      sessaoDeVotacaoRepository.salvar(pauta, PautaStatus.fechada, sessao);
      mensagemRepository.enviar(pauta);
    }
  }

  @Override
  public UUID votar(UUID id, SimNao voto, String cpf) {
    cpfRepository.verificar(cpf);
    SessaoDeVotacao sessao = sessaoDeVotacaoRepository
        .recuperarPelaPauta(id);
    Voto v = new Voto(sessao.getId(), voto, UUID.fromString(cpf));
    sessao.adicionarVoto(v);
    return sessaoDeVotacaoRepository.salvar(id, PautaStatus.aberta, sessao);
  }

}
