<%-- 
    Document   : doLista
    Created on : 14-dic-2011, 16.43.58
    Author     : Fulvio
--%>
<%@page import="it.capone.bean.ListaSpesaBean"%>
<%@page import="it.capone.db.ListaSpesaDAO" %>
<%@page import="it.capone.service.CGestioneSpesa" %>
<jsp:useBean class="it.capone.bean.ListaSpesaBean" id="listaSpesa" />

<%
  
	// Creo un oggetto CONTROLLER "CGestioneSpesa" che mi durerà per tutta la sessione utente, e che 
	// mi porto avanti negli altri Controller
//     CGestioneSpesa cGestSpesa = (CGestioneSpesa)request.getSession().getAttribute("cGestSpesa");
//     if(cGestSpesa == null) {
//     	cGestSpesa = new CGestioneSpesa();
//         request.getSession().setAttribute("cGestSpesa", cGestSpesa);
//     }
    
 	CGestioneSpesa cGestSpesa = new CGestioneSpesa();
     // interroga il database per ottenere l'elenco delle voci
 	cGestSpesa.fillListaSpesa(listaSpesa);   //JPA
    
	//Con connessione JDBC
	// crea oggetto DAO
    //ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
    // chiedi al DAO di ottenere l'elenco e memorizzarlo nel bean "listaSpesa"
    //spesaDAO.fillListaSpesa(listaSpesa);

  	//La listaSpesa recuperata la setto come variabile di sessione e me lo porto avanti 
	//recuperandola successivamente
    request.getSession().setAttribute("listaSpesa", listaSpesa); //CAPONE
    
// passa il controllo a viewLista per impaginarle/visualizzarle
%>
<% if (request.getParameter("stampa") != null) {%>
    <jsp:forward page="viewStampa.jsp" />
<% } else {%>
    <jsp:forward page="viewLista.jsp" />
<% }%>