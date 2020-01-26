package dao;

import entidades.Livro;
import java.util.List;

public interface LivroDAO {
    public void save(Livro c);
    public void delete(Livro c);
    public Livro find(int id);
    public List<Livro> all();
}
