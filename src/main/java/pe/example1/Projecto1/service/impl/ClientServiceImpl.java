package pe.example1.Projecto1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.example1.Projecto1.model.dao.ClientDao;
import pe.example1.Projecto1.model.dto.ClientDto;
import pe.example1.Projecto1.model.entity.Client;
import pe.example1.Projecto1.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientDao clientDao;

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .email(clientDto.getEmail())
                .registrationDate(clientDto.getRegistrationDate())
                .build();
        return clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Integer id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public boolean existsById(Integer id) {
        return clientDao.existsById(id);
    }
}
