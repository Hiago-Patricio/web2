
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Autor"%>
<%@page import="dao.AutorDAO"%>
<%@page import="dao.AutorDAOImpl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autor</title>
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    </head>
    <body>
        
        <%
            int id = 0;
            String nome = "";
            String nacionalidade = "";
            String dataNascimento = null;
            String dataFalecimento = null;
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(request.getAttribute("autor") != null){
                Autor autor = (Autor) request.getAttribute("autor");
                
                id = autor.getId();
                nome = autor.getNome();
                nacionalidade = autor.getNacionalidade();
                try{
                    dataNascimento = dateFormat.format(autor.getDataNascimento());
                    dataFalecimento = dateFormat.format(autor.getDataFalecimento());
                }catch(Exception e){
                    
                }
            }
            
            AutorDAO dao = new AutorDAOImpl();
            List<Autor> list = dao.all();
        %>
        
        <button class='btn btn-primary' type="button" onclick="window.location.href='index.html'">Home</button>
        <h1 style="text-align:center;">Cadastro de Autor</h1>
        
        <div class="container">
            <form action="autor" method="POST">
                <input type="hidden" name="id" value="<%= id%>">
                <div class="form-group">
                    <label id="labelNome" for="nome">Nome:</label>
                    <input class="form-control" type="text" id="nome" name="nome" required="" value="<%=nome%>"/>
                </div>
                <div class="form-group">
                    <label id="labelNacionalidade" for="nacionalidade">Nacionalidade:</label>
                    <input class="form-control" type="text" id="nacionalidade" name="nacionalidade" required="" value="<%=nacionalidade%>"/>
                </div>
                <div class="form-group">
                    <label id="labelDataNascimento" for="dataNascimento">Data de nascimento</label>
                    <input class="form-control" type="date" id="dataNascimento" name="data_nascimento" required="" value="<%=dataNascimento%>"/>
                </div>
                <div class="form-group">
                    <label id="labelDataFalecimento" for="dataFalecimento">Data de falecimento</label>
                    <input class="form-control" type="date" id="dataFalecimento" name="data_falecimento" value="<%=dataFalecimento%>"/>
                </div>
                <button type="submit" class="btn btn-primary">Salvar</button>
                <button id="cancelar" class="btn btn-danger" onclick="location.href='${pageContext.request.requestURL}'" type="button">Cancelar</button>
            </form>
        </div>
        
        
        <h2 style="text-align:center">Lista de autores</h2>
        
        <div class='container'>
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Nome</th>    
                        <th scope="col">Nacionalidade</th>    
                        <th scope="col">Data de Nascimento</th>    
                        <th scope="col">Data de Falecimento</th>    
                        <th scope="col">Opções</th>    
                    </tr>
                </thead>
                <tbody>
                <%for(Autor a: list){%>

                    <tr>   
                        <td><%=a.getNome()%></td>
                        <td><%=a.getNacionalidade()%></td>
                        <%
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
                        %>
                        <td><%=df.format(a.getDataNascimento())%></td>
                        <td><%
                            try{
                                out.println(df.format(a.getDataFalecimento()));
                            }catch(Exception e){
                            }
                        %></td>

                            <td>
                                <a href="autor?editar=<%= a.getId()%>">
                                  <button class="btn btn-primary">Editar</button>
                                </a>
                                <a href="autor?excluir=<%= a.getId()%>">
                                    <button class="btn btn-danger">Excluir</button>
                                </a>
                        </td> 
                    </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </body>

</html>
