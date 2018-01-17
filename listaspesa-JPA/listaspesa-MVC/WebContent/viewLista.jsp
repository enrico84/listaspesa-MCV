<%@page import="it.capone.bean.ListaSpesaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.capone.bean.Voce"%>
<jsp:useBean id="listaSpesa" class="it.capone.bean.ListaSpesaBean" scope="request" />
<html>
    <head>
        <title>Lista della spesa</title>
    </head>
    <body>
        <h1>Lista della spesa</h1>
        <table>
            <%
                /* genera righe del tipo:
                <tr><td>Prosciutto</td>
                <td>Modifica Elimina Su Giu</td>
                </tr>
                 */
              	
                 // Prendo la listaSpesa inviatomi dal Controller "doLista.jsp"
                listaSpesa = (ListaSpesaBean) request.getSession().getAttribute("listaSpesa");  //CAPONE
                if(listaSpesa.getDimensione() > 0) {
	                int pos = 0;
	                for (Voce v : listaSpesa.getSpesa()) {
	                    out.print("<tr><td>");
	                    out.print(v.getMsg()); 
	                    out.print("</td><td>");
	                    // link per le azioni
	                    
	                   
	                    out.print("<a href=\"doElimina.jsp?posVoce="
	                            + v.getOrd() + "\">Elimina</a> ");
	
	                    out.print("<a href=\"doModifica.jsp?posVoce="
	                            + v.getOrd() + "\">Modifica</a> ");
	                    
	                    if (pos != 0) {
	                        out.print("<a href=\"doSuGiu.jsp?dir=su&posVoce="
	                                + v.getOrd() + "\">Su</a> ");
	                    }
	
	                    if (pos < listaSpesa.getSpesa().size()-1 ) {
	                        out.print("<a href=\"doSuGiu.jsp?dir=giu&posVoce="
	                                + v.getOrd() + "\">Giu</a> ");
	                    }
	
	
	                    out.println("</td></tr>");
	                    pos++;
	                }
                }
                else
                	 out.println("<h3>Nessun elemento nella lista</h3>");
            %>
            <tr><form action="doAggiungi.jsp">
                <td><input type="text" name="voce"/></td>
                <td><input type="submit" value="Aggiungi"/></td>
            </form></tr>
    </table>
            <p><a href="doPulisci.jsp">Cancella tutto</a> 
                <a href="doLista.jsp?stampa" target="stampa">Stampa</a></p>
</body>
</html>
