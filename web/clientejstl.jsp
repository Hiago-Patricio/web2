<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="aux" tagdir="/WEB-INF/tags/" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<aux:page>
    <jsp:attribute name="header">
        <title>Cliente JSTL</title>        
    </jsp:attribute>
    <jsp:body>
        <h1 style="text-align:center;">Cadastro de Cliente</h1>

        <div class="container">
            <form method="POST" action="clientejstl">
                <input type="hidden" name="id" value="${cliente.id}"/>

                <div class="form-group">
                    <label id="labelNome" for="nome">Nome: </label>
                    <input class="form-control" type="text" id="nome" name="nome" required value="${cliente.nome}"/>
                </div>

                <div class="form-group">
                    <label id="labelEndereco" for="endereco">Endereço: </label>
                    <input class="form-control" type="text" id="endereco" name="endereco" required value="${cliente.endereco}"/>
                </div>

                <div class="form-group">
                    <label id="labelSexo" for="sexo">Sexo: </label><br/>
                    <c:choose>
                        <c:when test="${empty cliente.sexo}">
                            <input type="radio" name="sexo" value="Masculino" required>Masculino<br/>
                            <input type="radio" name="sexo" value="Feminino" >Feminino
                            </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${cliente.sexo}">
                                    <input type="radio" name="sexo" value="Masculino" required checked>Masculino<br/>
                                    <input type="radio" name="sexo" value="Feminino" >Feminino
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="sexo" value="Masculino" required>Masculino<br/>
                                    <input type="radio" name="sexo" value="Feminino" checked>Feminino
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group">
                    <label id="labelDataNascimento" for="dataNascimento">Data de nascimento: </label>
                    <input classe="form-control" type="date" id="dataNascimento" name="dataNascimento" required
                           value="<fmt:formatDate pattern="yyyy-MM-dd" value="${cliente.dataNascimento}"/>" />
                </div>

                <div class="form-group custom-radio">
                    <label id="labelVip" for="vip">Vip: </label><br/>
                    <c:choose>
                        <c:when test="${empty cliente.vip}">
                            <input type="radio" name="vip" value="true" required>Sim<br/>
                            <input type="radio" name="vip" value="false" >Não
                            </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${cliente.vip}">
                                    <input type="radio" name="vip" value="true" required checked>Sim<br/>
                                    <input type="radio" name="vip" value="false" >Não
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="vip" value="true" required>Sim<br/>
                                    <input type="radio" name="vip" value="false" checked>Não
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </div>

                <button id="salvar" class="btn btn-success" type="submit">Salvar</button>
                <button id="cancelar" class="btn btn-danger" onclick="location.href='clientejstl'" 
                    type="button">Cancelar</button>
            </form>
        </div>
                
        <h2 style="text-align:center">Lista de clientes</h2>

        <div class="container">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Endereço</th>
                        <th scope="col">Sexo</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Data de nascimento</th>
                        <th scope="col">Vip</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="c" items="${lista}">
                        <tr>
                            <td>${c.endereco}</td>
                            <td>${c.sexo}</td>
                            <td>${c.nome}</td>
                            <td>
                                <fmt:formatDate value="${c.dataNascimento}"
                                    type="date" pattern="MM/dd/yyyy"/>
                            </td>

                            <c:choose>
                                <c:when test="${c.vip}">
                                    <td>Sim</td>
                                </c:when>
                                <c:otherwise>
                                    <td>Não</td>                                    
                                </c:otherwise>
                            </c:choose>

                            <td>
                                <a href="clientejstl?editar=${c.id}">
                                    <button class="btn btn-primary">Editar</button>
                                </a>

                                <a href="clientejstl?excluir=${c.id}">
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
