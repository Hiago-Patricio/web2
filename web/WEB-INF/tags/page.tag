<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="aux" tagdir="/WEB-INF/tags/"%>

<%@attribute name="header" fragment="true"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <aux:header></aux:header>
        <jsp:invoke fragment="header" />
    </head>
    
    <body>
        <button class='btn btn-primary' type="button" onclick="window.location.href='index.html'">Home</button>
        <jsp:doBody />
    </body>
</html>
