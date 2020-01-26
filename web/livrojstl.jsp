<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="aux" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<aux:page>
    <jsp:attribute name="header">
        <title>Livro JSTL</title>        
    </jsp:attribute>
    <jsp:body>
        <h1 style="text-align:center;">Cadastro de Livro</h1>
        
        <div class="container">
            <form method="POST" action="livrojstl">
                <input type="hidden" name="id" value="${livro.id}"/>

                <div class="form-group">
                    <label id="labelNome" for="nome">Nome: </label>
                    <input class="form-control" type="text" id="nome" name="nome" required value="${livro.nome}"/>
                </div>

                <div class="form-group">
                    <label id="labelSinopse" for="sinopse">Sinopse: </label>
                    <input class="form-control" type="text" id="sinopse" name="sinopse" required value="${livro.sinopse}"/>
                </div>

                <div class="form-group">
                    <label id="labelEditora" for="editora">Editora: </label>
                    <input class="form-control" type="text" id="editora" name="editora" required value="${livro.editora}"/>
                </div>
                
                <div class="form-group">
                    <label id="labelEdicao" for="edicao">Ediçao: </label>
                    <input class="form-control" type="number" min="1" id="edicao" name="edicao" required value="${livro.edicao}"/>
                </div>
                
                <div class="form-group">
                    <label id="labelPreco" for="preco">Preço: </label>
                    <aux:money classe="form-control" id="preco" 
                               nome="preco" valor="${livro.preco}"/>
                </div>
                
                <div class="form-group">
                    <label id="labelQuantidade" for="quantidade">Quantidade: </label>
                    <input class="form-control" type="number" min="1" id="quantidade" name="quantidade" required value="${livro.quantidade}"/>
                </div>

                <button id="salvar" class="btn btn-success" type="submit">Salvar</button>
                <button id="cancelar" class="btn btn-danger" onclick="location.href='${pageContext.request.requestURL}'" type="button">Cancelar</button>
            </form>
        </div>
                
        <h2 style="text-align:center">Lista de livros</h2>

        <div class="container">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Sinopse</th>
                        <th scope="col">Editora</th>
                        <th scope="col">Edição</th>
                        <th scope="col">Preço</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="l" items="${lista}">
                        <tr>
                            <td>${l.nome}</td>
                            <td>${l.sinopse}</td>
                            <td>${l.editora}</td>
                            <td>${l.edicao}</td>
                            <td>
                                <fmt:setLocale value="pt_BR"/>
                                <fmt:formatNumber value="${l.preco}" type="currency"/>
                            </td>
                            <td>${l.quantidade}</td>

                            <td>
                                <a href="livrojstl?editar=${l.id}">
                                    <button class="btn btn-primary">Editar</button>
                                </a>

                                <a href="livrojstl?excluir=${l.id}">
                                    <button class="btn btn-danger">Excluir</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </jsp:body>
</aux:page>
