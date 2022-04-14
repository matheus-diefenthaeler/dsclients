package br.com.matheus.dsclients.controller;

import br.com.matheus.dsclients.entities.Client;
import br.com.matheus.dsclients.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(name = "/clients")
public class clientController {

    @Autowired
    private ClientService service;

    public ResponseEntity<List<Client>> findAll() {
        List<Client> list = new ArrayList<>();
        return ResponseEntity.ok().body(list);
    }
}
