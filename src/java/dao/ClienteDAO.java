package dao;

import entidades.Cliente;
import java.util.List;

public interface ClienteDAO {
    public void save(Cliente c);
    public void delete(Cliente c);
    public Cliente find(int id);
    public List<Cliente> all();
}
