<%@page import="it.polito.elite.paw.db.ListaSpesaDAO"%>
<%@page import="it.polito.elite.paw.listaspesa.Voce"%>
<jsp:useBean id="modificaVoce"
             class="it.polito.elite.paw.listaspesa.ModificaVoceFormData"
             scope="request" />
<jsp:setProperty name="modificaVoce"
                 property="posVoce"
                 param="posVoce" />
<jsp:setProperty name="modificaVoce"
                 property="msg"
                 param="voce" />

<%
    ListaSpesaDAO spesaDAO = new ListaSpesaDAO();

    spesaDAO.modifica( modificaVoce.getPosVoce(), modificaVoce.getMsg() ) ;

    response.sendRedirect("doLista.jsp");
%>