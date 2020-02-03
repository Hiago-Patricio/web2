<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="aux" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<aux:page>
    <jsp:attribute name="header">
        <title>Compra CDI</title>        
    </jsp:attribute>
    <jsp:body>
        <h1 style="text-align:center;">Cadastro de Compra</h1>

        <div class="container">
            <form method="POST" action="compracdi">
                <input type="hidden" name="id" value="${compra.id}"/>

                <div class="form-group">
                    Cliente:
                    <select name="clienteId" id="clienteId">
                        <c:forEach var="cliente" items="${clientes}">
                            <option value="${cliente.id}"
                                <c:if test="${cliente.id == compra.cliente.id}">
                                    selected
                                </c:if>
                            >${cliente.nome}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    Funcionário:
                    <select name="funcionarioId">
                        <c:forEach var="funcionario" items="${funcionarios}">
                            <option value="${funcionario.id}"
                                <c:if test="${funcionario.id == compra.funcionario.id}">
                                    selected
                                </c:if>
                            >${funcionario.nome}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    Mídia:
                    <select name="midiaId">
                        <c:forEach var="livro" items="${livros}">
                            <option value="${livro.midia.id}">${livro.titulo}</option>
                        </c:forEach>
                            
                        <c:forEach var="revista" items="${revistas}">
                            <option value="${revista.midia.id}">${revista.titulo}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label id="labelQuantidade" for="quantidade">Quantidade: </label>
                    <input class="form-control" type="number" min="1" id="quantidade" name="quantidade" required value="${compra.quantidade}"/>
                </div>

                <div class="form-group">
                    <label id="labelData" for="data">Data de : </label>
                    <input classe="form-control" type="date" id="data" name="data" required
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${compra.data}"/>" />
                </div>

                <button id="salvar" class="btn btn-success" type="submit">Salvar</button>
                <button id="cancelar" class="btn btn-danger" onclick="location.href = 'compracdi'" 
                        type="button">Cancelar</button>
            </form>
        </div>

        <h2 style="text-align:center">Lista de compras</h2>

        <div class="container">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                            <th scope="col">Cliente</th>
                        <th scope="col">Funcionário</th>
                        <th scope="col">Mídia</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Data</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="compra" items="${compras}">
                        <tr>
                            <td>${compra.cliente.nome}</td>
                            <td>${compra.funcionario.nome}</td>
                            <td>
                                <c:forEach var="livro" items="${livros}">
                                    <c:if test="${compra.midia.id == livro.midia.id}">
                                        ${livro.titulo}
                                    </c:if>
                                </c:forEach>
                                <c:forEach var="revista" items="${revistas}">
                                    <c:if test="${compra.midia.id == revista.midia.id}">
                                        ${revista.titulo}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${compra.quantidade}</td>
                            <td>
                                <fmt:formatDate value="${compra.data}"
                                                type="date" pattern="MM/dd/yyyy"/>
                            </td>

                            <td>
                                <a href="compracdi?editar=${compra.id}">
                                    <button class="btn btn-primary">Editar</button>
                                </a>

                                <a href="compracdi?excluir=${compra.id}">
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
