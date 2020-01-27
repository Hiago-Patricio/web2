package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="autor")
@SequenceGenerator(name="seq_autor", 
        sequenceName="autor_id_seq",
        allocationSize=1)
public class Autor {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_autor")
    @Column(nullable=false)
    private int id;

    @Column(nullable=false)
    private String nacionalidade;
    
    @Column(nullable=false)
    private String nome;
    
    @Column(name="data_nascimento", nullable=false)
    private Date dataNascimento;
    
    @Column(name="data_falecimento", nullable=true)
    private Date dataFalecimento;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros = new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(Date dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }
    
}