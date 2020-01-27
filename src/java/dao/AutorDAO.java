package dao;

import java.util.List;
import entidades.Autor;

public interface AutorDAO {

    public void save(Autor a);

    public void delete(Autor a);

    public Autor find(int id);

    public List<Autor> all();
}
