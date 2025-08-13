package pe.example1.Projecto1.model.dao;

import org.springframework.data.repository.CrudRepository;
import pe.example1.Projecto1.model.entity.Client;

public interface ClientDao extends CrudRepository<Client, Integer> {
}
