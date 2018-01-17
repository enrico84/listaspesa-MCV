<%@page import="it.capone.db.ListaSpesaDAO"%>
<%@page import="it.capone.service.CGestioneSpesa" %>
<%
    //ListaSpesaDAO spesaDAO = new ListaSpesaDAO(); 	//JDBC    
	CGestioneSpesa cgSpesa = new CGestioneSpesa(); 		//JPA
    
    String pos = request.getParameter("posVoce");
    int posVoce = Integer.parseInt(pos);
    
    if (request.getParameter("dir").equals("su")) {
    	cgSpesa.spostaSu(posVoce);          //JPA
        //spesaDAO.spostaSu(posVoce);  		//JDBC
    } else if (request.getParameter("dir").equals("giu")) {
    	cgSpesa.spostaGiu(posVoce);			//JPA
        //spesaDAO.spostaGiu(posVoce);		//JDBC
    }

    response.sendRedirect("doLista.jsp");
%>