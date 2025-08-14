package pe.example1.Projecto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.example1.Projecto1.model.dto.ClientDto;
import pe.example1.Projecto1.model.entity.Client;
import pe.example1.Projecto1.model.payload.MessageResponse;
import pe.example1.Projecto1.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto){
        Client client = null;
        try {
            client = clientService.save(clientDto);
            clientDto = ClientDto.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .registrationDate(client.getRegistrationDate())
                    .build();
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("save success")
                    .object(clientDto)
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(e.getMessage())
                    .object(null)
                    .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("client/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDto clientDto, @PathVariable Integer id){
        Client client = null;
        try {
            if (clientService.existsById(id)){
                clientDto.setId(id);
                client = clientService.save(clientDto);
                clientDto = ClientDto.builder()
                        .id(client.getId())
                        .name(client.getName())
                        .lastName(client.getLastName())
                        .email(client.getEmail())
                        .registrationDate(client.getRegistrationDate())
                        .build();
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("save success")
                        .object(clientDto)
                        .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("the register is not found")
                        .object(null)
                        .build()
                        , HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(e.getMessage())
                    .object(null)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Client client = clientService.findById(id);
            clientService.delete(client);
            return new ResponseEntity<>(client, HttpStatus.NO_CONTENT);
        }catch (DataAccessException e){
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(e.getMessage())
                    .object(null)
                    .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Client client = clientService.findById(id);
        if (client == null){
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("The register cant find")
                    .object(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
        ClientDto clientDto = ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .registrationDate(client.getRegistrationDate())
                .build();
        return new ResponseEntity<>(MessageResponse.builder()
                .message("")
                .object(clientDto)
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("clients")
    public ResponseEntity<?> list(){
        List<Client> list = clientService.list();
        if (list == null){
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("dont exists registers")
                    .object(null)
                    .build()
                    , HttpStatus.OK);
        }

        return new ResponseEntity<>(MessageResponse.builder()
                .message("")
                .object(list)
                .build()
                , HttpStatus.OK);
    }
}
