<%@page import="it.capone.service.CGestioneSpesa" %>
<%@page import="it.capone.db.ListaSpesaDAO"%>
<%@page import="it.capone.bean.Voce"%>
<%
    String voce = request.getParameter("voce");

	//Con connessione JDBC
    // interroga il database per ottenere l'elenco delle voci
    // crea oggetto DAO
    //ListaSpesaDAO spesaDAO = new ListaSpesaDAO();

	CGestioneSpesa cGestSpesa = new CGestioneSpesa();
	
    // chiedi al DAO di aggiungere la voce al database
    if (!voce.isEmpty()) {
    	cGestSpesa.aggiungi(voce);   //JPA
        //spesaDAO.aggiungi(voce);   //JDBC
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