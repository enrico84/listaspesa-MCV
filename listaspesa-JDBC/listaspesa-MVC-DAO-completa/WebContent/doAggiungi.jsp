<%@page import="it.polito.elite.paw.db.ListaSpesaDAO"%>
<%@page import="it.polito.elite.paw.listaspesa.Voce"%>
<%
    String voce = request.getParameter("voce");
    // interroga il database per ottenere l'elenco delle voci
    // crea oggetto DAO
    ListaSpesaDAO spesaDAO = new ListaSpesaDAO();

    // chiedi al DAO di aggiungere la voce al database
    if (!voce.isEmpty()) {
        spesaDAO.aggiungi(voce);
    }
%>
<!-- jsp:forward page="doLista.jsp" / -->
<%
    response.sendRedirect("doLista.jsp");
%>
<%--
HTTP GET doAggiungi.jsp?voce=xxxx

HTTP RESPONSE 302 (Redirect) doLista.jsp
    (tutti i bean di richiesta vengono cancellati)
    
    
HTTP GET doLista.jsp

    (forward a viewLista.jsp)
  
HTTP RESPONSE 200 (OK) con HTML
--%>