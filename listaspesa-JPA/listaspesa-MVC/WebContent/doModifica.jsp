<%@page import="it.capone.db.ListaSpesaDAO"%>
<%@page import="it.capone.service.CGestioneSpesa" %>
<%@page import="it.capone.bean.Voce"%>
<jsp:useBean id="modificaVoce"
             class="it.capone.bean.ModificaVoceFormData"
             scope="request" />
<jsp:setProperty name="modificaVoce"
                 property="posVoce"
                 param="posVoce" />

<%
	//JDBC
//     ListaSpesaDAO spesaDAO = new ListaSpesaDAO();   

  //JPA
    CGestioneSpesa cGestSpesa = new CGestioneSpesa();
    String msg = cGestSpesa.getMsg(modificaVoce.getPosVoce()) ;
  
    modificaVoce.setMsg(msg);
%>
<jsp:forward page="viewModifica.jsp" />