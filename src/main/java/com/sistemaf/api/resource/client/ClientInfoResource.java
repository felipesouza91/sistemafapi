package com.sistemaf.api.resource.client;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sistemaf.api.docs.controllers.ClientInfoResourceOpenApi;
import com.sistemaf.api.dto.input.InfoInputModel;
import com.sistemaf.api.dto.manager.ClientInfoMapper;
import com.sistemaf.api.dto.model.ClientInfoModel;
import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.filter.InformacaoFilter;
import com.sistemaf.domain.model.ClienteInformacao;
import com.sistemaf.domain.service.ClientInformationService;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/clients/{clientId}/info", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientInfoResource implements ClientInfoResourceOpenApi {


  private ClientInfoMapper dtoManager = ClientInfoMapper.INSTANCE;

  @Autowired
  private ClientInformationService clientInfoService;

  @Autowired
  private ApplicationEventPublisher publisher;

  @Override
  @GetMapping()
  @PreAuthorize("hasAuthority('51')")
  @ResponseStatus(HttpStatus.OK)
  public Page<ClientInfoModel> listarInformacoes(@PathVariable Long clientId,
                                                 InformacaoFilter filter, Pageable pageable){
    Page<ClienteInformacao> clientListPage = this.clientInfoService.filtrarAtendimento(clientId, filter, pageable);

    Page<ClientInfoModel> listClientInfoModel = new PageImpl<>(
            this.dtoManager.toCollectionModel(clientListPage.getContent()),
            pageable, clientListPage.getTotalElements());
    return listClientInfoModel;
  }

  @Override
  @GetMapping("/{code}")
  @PreAuthorize("hasAuthority('51')")
  @ResponseStatus(HttpStatus.OK)
  public ClientInfoModel listarInformacaoPorId(@PathVariable Long clientId, @PathVariable Long code) {
    ClienteInformacao info = clientInfoService.getInformacaoById(code);
    return dtoManager.toDTO(info);
  }

  @Override
  @PostMapping()
  @PreAuthorize("hasAuthority('49')")
  @ResponseStatus(HttpStatus.CREATED)
  public ClientInfoModel salvaInformacao(@Valid @RequestBody InfoInputModel info,
                                         @PathVariable Long clientId, HttpServletResponse response){
    ClienteInformacao clienteinfo = this.dtoManager.toModel(info);
    ClienteInformacao infoSalva = this.clientInfoService.saveInfo(clientId, clienteinfo);
    publisher.publishEvent(new RecursoCriarEvent(this, response, infoSalva.getId()));
    return this.dtoManager.toDTO(infoSalva);
  }

  @Override
  @PutMapping("/{code}")
  @PreAuthorize("hasAuthority('49')")
  @ResponseStatus(HttpStatus.OK)
  public ClientInfoModel atualizarInformacao(@PathVariable Long code, @PathVariable Long clientId,
                                             @Valid @RequestBody InfoInputModel info) {
    ClienteInformacao updatedInfo = this.dtoManager.toModel(info);
    ClienteInformacao infoSalva = this.clientInfoService.atualizarInformacao(code, clientId, updatedInfo);
    return this.dtoManager.toDTO(infoSalva);
  }

  @Override
  @DeleteMapping("/{code}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasAuthority('50')")
  public void removerInfo(@PathVariable Long code){
    clientInfoService.deleteInfoById(code);
  }

}
