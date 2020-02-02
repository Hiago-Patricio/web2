package dao;

import entidades.Revista;
import java.util.List;

public interface RevistaDAO {
    public void save(Revista r);
    public void delete(Revista r);
    public Revista find(int id);
    public List<Revista> all();
}
