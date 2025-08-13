package pe.example1.Projecto1.service;

import pe.example1.Projecto1.model.entity.Client;

public interface IClient {
    Client save(Client client);

    Client findById(Integer id);

    void delete(Client client);
}
