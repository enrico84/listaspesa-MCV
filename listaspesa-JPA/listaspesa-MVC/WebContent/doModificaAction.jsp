<%@page import="it.capone.db.ListaSpesaDAO"%>
<%@page import="it.capone.bean.Voce"%>
<%@page import="it.capone.service.CGestioneSpesa"%>
<jsp:useBean id="modificaVoce"
             class="it.capone.bean.ModificaVoceFormData"
             scope="request" />
<jsp:setProperty name="modificaVoce"
                 property="posVoce"
                 param="posVoce" />
<jsp:setProperty name="modificaVoce"
                 property="msg"
                 param="voce" />

<%
	//JDBC
	//ListaSpesaDAO spesaDAO = new ListaSpesaDAO();  
    //spesaDAO.modifica( modificaVoce.getPosVoce(), modificaVoce.getMsg() ) ;
	
    //JPA
    CGestioneSpesa cgSpesa = new CGestioneSpesa();
    cgSpesa.modifica(modificaVoce.getPosVoce(), modificaVoce.getMsg());
    

    response.sendRedirect("doLista.jsp");
%>