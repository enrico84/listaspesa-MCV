<%@page import="it.capone.db.ListaSpesaDAO"%>
<%@page import="it.capone.service.CGestioneSpesa" %>
<%@page import="it.capone.bean.Voce"%>
<%
	//JDBC
    //ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
    //spesaDAO.pulisci();
    
    //JPA
    CGestioneSpesa gestione = new CGestioneSpesa();
    gestione.pulisci();

    response.sendRedirect("doLista.jsp");

%>