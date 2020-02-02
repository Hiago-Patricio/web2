<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="aux" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<aux:page>
    <jsp:attribute name="header">
        <title>Revista CDI</title>        
    </jsp:attribute>
    <jsp:body>
        <h1 style="text-align:center;">Cadastro de Revista</h1>

        <div class="container">
            <form method="POST" action="revistacdi">
                <input type="hidden" name="id" value="${revista.id}"/>
                <input type="hidden" name="idMidia" value="${revista.midia.id}"/>

                <div class="form-group">
                    <label id="labelTitulo" for="nome">Título: </label>
                    <input class="form-control" type="text" id="titulo" name="titulo" required value="${revista.titulo}"/>
                </div>

                <div class="form-group">
                    <label id="labelEditora" for="editora">Editora: </label>
                    <input class="form-control" type="text" id="editora" name="editora" required value="${revista.editora}"/>
                </div>

                <div class="form-group">
                    <label id="labelPreco" for="preco">Preço: </label>
                    <aux:money classe="form-control" id="preco" 
                               nome="preco" valor="${revista.midia.preco}"/>
                </div>

                <div class="form-group">
                    <label id="labelQuantidade" for="quantidade">Quantidade: </label>
                    <input class="form-control" type="number" min="1" id="quantidade" name="quantidade" required value="${revista.midia.quantidade}"/>
                </div>

                <button id="salvar" class="btn btn-success" type="submit">Salvar</button>
                <button id="cancelar" class="btn btn-danger" onclick="location.href = 'revistacdi'" 
                        type="button">Cancelar</button>
            </form>
        </div>

        <h2 style="text-align:center">Lista de revistas</h2>

        <div class="container">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Título</th>
                        <th scope="col">Editora</th>
                        <th scope="col">Preço</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="r" items="${lista}">
                        <tr>
                            <td>${r.titulo}</td>
                            <td>${r.editora}</td>
                            <td>
                                <fmt:setLocale value="pt_BR"/>
                                <fmt:formatNumber value="${r.midia.preco}" type="currency"/>
                            </td>
                            <td>${r.midia.quantidade}</td>

                            <td>
                                <a href="revistacdi?editar=${r.id}">
                                    <button class="btn btn-primary">Editar</button>
                                </a>

                                <a href="revistacdi?excluir=${r.id}">
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
