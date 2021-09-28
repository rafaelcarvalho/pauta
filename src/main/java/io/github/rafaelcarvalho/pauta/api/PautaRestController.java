package io.github.rafaelcarvalho.pauta.api;

import java.util.Collection;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rafaelcarvalho.pauta.domain.model.Pauta;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.PautaStatus;
import io.github.rafaelcarvalho.pauta.domain.model.enumeration.SimNao;
import io.github.rafaelcarvalho.pauta.facade.PautaFacade;

@RestController
@RequestMapping("/api/v1/pautas")
public class PautaRestController {

  private final PautaFacade facade;

  public PautaRestController(PautaFacade facade) {
    this.facade = facade;
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public UUID criarPauta(@RequestBody @Valid Pauta pauta) {
    return facade.criar(pauta);
  }

  @GetMapping(path = "/{status}")
  public Collection<Pauta> recuperarPautas(
      @PathVariable("status") PautaStatus status) {
    return facade.listarPautas(status);
  }

  @PutMapping(path = "/{id}/abrir")
  public UUID abrirPauta(@PathVariable("id") String idPauta,
      @RequestParam Long duracaoEmMinutos) {
    return facade.abrirSessao(UUID.fromString(idPauta), duracaoEmMinutos);
  }

  @PutMapping(path = "/{id}/votar")
  public UUID votarNaPauta(@PathVariable("id") String id,
      @RequestParam(required = true) String cpf,
      @RequestParam(required = true) SimNao voto) {
    return facade.votar(UUID.fromString(id), cpf, voto);
  }

  @DeleteMapping(path = "/{id}")
  public void fecharPauta(@PathVariable("id") String idPauta) {
    facade.encerrarPauta(UUID.fromString(idPauta));
  }

}
