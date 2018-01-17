<%-- 
    Document   : doLista
    Created on : 14-dic-2011, 16.43.58
    Author     : Fulvio
--%>
<%@page import="it.polito.elite.paw.db.ListaSpesaDAO"%>
<jsp:useBean id="listaSpesa"
             class="it.polito.elite.paw.listaspesa.ListaSpesaBean"
             scope="request" />
<%
    // interroga il database per ottenere l'elenco delle voci
    // crea oggetto DAO
    ListaSpesaDAO spesaDAO = new ListaSpesaDAO();

    // chiedi al DAO di ottenere l'elenco e memorizzarlo nel bean "listaSpesa"
    spesaDAO.fillListaSpesa(listaSpesa);

    // passa il controllo a viewLista per impaginarle/visualizzarle
%>
<% if (request.getParameter("stampa") != null) {%>
    <jsp:forward page="viewStampa.jsp" />
<% } else {%>
    <jsp:forward page="viewLista.jsp" />
<% }%>