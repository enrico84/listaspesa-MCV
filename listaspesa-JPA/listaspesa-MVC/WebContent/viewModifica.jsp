<%-- 
    Document   : viewModifica
    Created on : 6-dic-2011, 8.59.19
    Author     : Fulvio
--%>
<%@page import="it.capone.bean.Voce"%>
<jsp:useBean id="modificaVoce"
             class="it.capone.bean.ModificaVoceFormData"
             scope="request" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica voce</title>
        <link rel="stylesheet" type="text/css" href="css/stili.css"/>
    </head>
    <body>
        <h1>Modifica la voce della spesa</h1>
        <form action="doModificaAction.jsp">
            <td><input type="text" name="voce" value="${modificaVoce.msg}"/>
                <input type="hidden" name="posVoce" value="${modificaVoce.posVoce}"/></td>
            <td><input type="submit" value="Modifica"/></td>
        </form>
    </body>
</html>
