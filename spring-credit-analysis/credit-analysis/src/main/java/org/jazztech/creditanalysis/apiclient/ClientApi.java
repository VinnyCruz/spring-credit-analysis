package org.jazztech.creditanalysis.apiclient;

import org.jazztech.creditanalysis.apiclient.dto.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "Client", url = "http://localhost:8080/v1.0/clients")
public interface ClientApi {

    @GetMapping(path = "/{id}")
    Client getClientById(@PathVariable(value = "id") UUID clientId);
}
