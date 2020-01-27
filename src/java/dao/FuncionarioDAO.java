package dao;

import entidades.Funcionario;
import java.util.List;

public interface FuncionarioDAO {

    public void save(Funcionario f);

    public void delete(Funcionario f);

    public Funcionario find(int id);

    public List<Funcionario> all();
}
