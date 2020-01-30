package dao;

import entidades.Midia;
import java.util.List;

public interface MidiaDAO {
    public void save(Midia m);
    public void delete(Midia m);
    public Midia find(int id);
    public List<Midia> all();
}
