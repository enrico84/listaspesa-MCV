<%@page import="it.polito.elite.paw.db.ListaSpesaDAO"%>
<%@page import="it.polito.elite.paw.listaspesa.Voce"%>
<jsp:useBean id="listaSpesa"
             class="it.polito.elite.paw.listaspesa.ListaSpesaBean"
             scope="session" />
<%
    String pos = request.getParameter("posVoce");
    int posVoce = Integer.parseInt(pos);

    ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
    spesaDAO.elimina(posVoce);

    //listaSpesa.elimina(posVoce);

    response.sendRedirect("doLista.jsp");

%>
