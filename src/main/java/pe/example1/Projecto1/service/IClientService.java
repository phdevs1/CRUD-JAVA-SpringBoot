package pe.example1.Projecto1.service;

import pe.example1.Projecto1.model.dto.ClientDto;
import pe.example1.Projecto1.model.entity.Client;

import java.util.List;

public interface IClientService {

    List<Client> list();

    Client save(ClientDto client);

    Client findById(Integer id);

    void delete(Client client);

    boolean existsById(Integer id);
}
