<%@page import="it.capone.db.ListaSpesaDAO"%>
<%@page import="it.capone.service.CGestioneSpesa" %>
<%@page import="it.capone.bean.Voce"%>
<jsp:useBean id="listaSpesa"
             class="it.capone.bean.ListaSpesaBean"
             scope="session" />
<%
    String pos = request.getParameter("posVoce");
    int posVoce = Integer.parseInt(pos);

    //JDBC
    //ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
    //spesaDAO.elimina(posVoce);
    
    //JPA
    CGestioneSpesa spesaEjb = new CGestioneSpesa();
    spesaEjb.elimina(posVoce);
    

    //listaSpesa.elimina(posVoce);

    response.sendRedirect("doLista.jsp");

%>
