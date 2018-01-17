<%@page import="it.polito.elite.paw.db.ListaSpesaDAO"%>
<%@page import="it.polito.elite.paw.listaspesa.Voce"%>
<jsp:useBean id="modificaVoce"
             class="it.polito.elite.paw.listaspesa.ModificaVoceFormData"
             scope="request" />
<jsp:setProperty name="modificaVoce"
                 property="posVoce"
                 param="posVoce" />

<%
    ListaSpesaDAO spesaDAO = new ListaSpesaDAO();

    String msg = spesaDAO.getMsg(modificaVoce.getPosVoce()) ;
    
    modificaVoce.setMsg(msg);
%>
<jsp:forward page="viewModifica.jsp" />