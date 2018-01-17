<%@page import="it.polito.elite.paw.db.ListaSpesaDAO"%>
<%
    ListaSpesaDAO spesaDAO = new ListaSpesaDAO();    

    String pos = request.getParameter("posVoce");
    int posVoce = Integer.parseInt(pos);
    
    if (request.getParameter("dir").equals("su")) {
        spesaDAO.spostaSu(posVoce);
    } else if (request.getParameter("dir").equals("giu")) {
        spesaDAO.spostaGiu(posVoce);
    }

    response.sendRedirect("doLista.jsp");
%>