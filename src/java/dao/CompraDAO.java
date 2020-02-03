package dao;

import entidades.Compra;
import java.util.List;

public interface CompraDAO {
    public void save(Compra c);
    public void delete(Compra c);
    public Compra find(int id);
    public List<Compra> all();
}
