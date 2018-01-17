<%-- 
    Document   : viewLista
    Created on : 2-dic-2011, 14.12.55
    Author     : Fulvio
--%>
<%@page import="it.capone.bean.ListaSpesaBean"%>
<%@page import="it.capone.bean.Voce"%>
<jsp:useBean id="listaSpesa"
             class="it.capone.bean.ListaSpesaBean"
             scope="request" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista della spesa</title>
    </head>
    <body>
        <h1>Lista della spesa</h1>
        <ol>
        
            <%
	            // Prendo la listaSpesa inviatomi dal Controller "doLista.jsp"
	            listaSpesa = (ListaSpesaBean) request.getSession().getAttribute("listaSpesa");  //CAPONE
            	
                for (Voce v : listaSpesa.getSpesa()) {
                    out.print("<li>" + v.getMsg() + "</li>");
                }
            %>
        </ol>
    </body>
</html>
