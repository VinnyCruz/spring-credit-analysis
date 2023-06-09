package org.jazztech.creditanalysis.apiclient;

import java.util.List;
import java.util.UUID;
import org.jazztech.creditanalysis.apiclient.dto.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "Client", url = "http://localhost:8080/v1.0/clients")
public interface ClientApi {
    @GetMapping(path = "/{id}")
    Client getClientById(@PathVariable(value = "id") UUID clientId);

    @GetMapping
    List<Client> getClientByCpf(@RequestParam(value = "cpf") String clientCpf);
}
