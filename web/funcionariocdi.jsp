<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="aux" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<aux:page>
    <jsp:attribute name="header">
        <title>Funcionario JSTL</title>        
    </jsp:attribute>
    <jsp:body>
        <h1 style="text-align:center;">Cadastro de Funcionário</h1>

        <div class="container">
            <form method="POST" action="funcionariocdi">
                <input type="hidden" name="id" value="${funcionario.id}"/>

                <div class="form-group">
                    <label id="labelNome" for="nome">Nome: </label>
                    <input class="form-control" type="text" id="nome" name="nome" required value="${funcionario.nome}"/>
                </div>

                <div class="form-group">
                    <label id="labelCargo" for="cargo">Cargo: </label>
                    <input class="form-control" type="text" id="cargo" name="cargo" required value="${funcionario.cargo}"/>
                </div>

                <div class="form-group">
                    <label id="labelSalario" for="salario">Salário: </label>
                    <aux:money classe="form-control" id="salario" 
                               nome="salario" valor="${funcionario.salario}"/>
                </div>

                <div class="form-group">
                    <label id="labelDataAdmissao" for="dataAdmissao">Data de admissão</label>
                    <input classe="form-control" type="date" id="dataAdmissao" name="dataAdmissao" required
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${funcionario.dataAdmissao}"/>" />
                </div>

                <button id="salvar" class="btn btn-success" type="submit">Salvar</button>
                <button id="cancelar" class="btn btn-danger" onclick="location.href = 'funcionariocdi'" 
                        type="button">Cancelar</button>
            </form>
        </div>

        <h2 style="text-align:center">Lista de funcionarios</h2>

        <div class="container">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Cargo</th>
                        <th scope="col">Salário</th>
                        <th scope="col">Data de Admissão</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="l" items="${lista}">
                        <tr>
                            <td>${l.nome}</td>
                            <td>${l.cargo}</td>
                            <td>
                                <fmt:setLocale value="pt_BR"/>
                                <fmt:formatNumber value="${l.salario}" type="currency"/>
                            <td>
                                <fmt:formatDate value="${l.dataAdmissao}"
                                                type="date" pattern="MM/dd/yyyy"/>
                            </td>

                            <td>
                                <a href="funcionariocdi?editar=${l.id}">
                                    <button class="btn btn-primary">Editar</button>
                                </a>

                                <a href="funcionariocdi?excluir=${l.id}">
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
