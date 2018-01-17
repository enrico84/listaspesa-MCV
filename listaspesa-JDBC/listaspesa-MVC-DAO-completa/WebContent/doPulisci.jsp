<%@page import="it.polito.elite.paw.db.ListaSpesaDAO"%>
<%@page import="it.polito.elite.paw.listaspesa.Voce"%>
<%
    ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
    spesaDAO.pulisci();

    response.sendRedirect("doLista.jsp");

%>