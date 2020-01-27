package entidades;

import entidades.Livro;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-27T08:30:45")
@StaticMetamodel(Autor.class)
public class Autor_ { 

    public static volatile ListAttribute<Autor, Livro> livros;
    public static volatile SingularAttribute<Autor, Date> dataFalecimento;
    public static volatile SingularAttribute<Autor, String> nome;
    public static volatile SingularAttribute<Autor, Integer> id;
    public static volatile SingularAttribute<Autor, String> nacionalidade;
    public static volatile SingularAttribute<Autor, Date> dataNascimento;

}