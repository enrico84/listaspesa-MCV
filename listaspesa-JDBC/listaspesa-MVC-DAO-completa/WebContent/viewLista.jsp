<%-- 
    Document   : viewLista
    Created on : 2-dic-2011, 14.12.55
    Author     : Fulvio
--%>
<%@page import="it.polito.elite.paw.listaspesa.Voce"%>
<jsp:useBean id="listaSpesa"
             class="it.polito.elite.paw.listaspesa.ListaSpesaBean"
             scope="request" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista della spesa</title>
        <link rel="stylesheet" type="text/css" href="css/stili.css"/>
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
                int pos = 0;
                for (Voce v : listaSpesa.getSpesa()) {
                    out.print("<tr><td>");
                    out.print(v.getMsg());
                    out.print("</td><td>");
                    // link per le azioni
                    out.print("<a href=\"doElimina.jsp?posVoce="
                            + pos + "\">Elimina</a> ");

                    out.print("<a href=\"doModifica.jsp?posVoce="
                            + pos + "\">Modifica</a> ");
                    
                    if (pos != 0) {
                        out.print("<a href=\"doSuGiu.jsp?dir=su&posVoce="
                                + pos + "\">Su</a> ");
                    }

                    if (pos < listaSpesa.getSpesa().size()-1 ) {
                        out.print("<a href=\"doSuGiu.jsp?dir=giu&posVoce="
                                + pos + "\">Giu</a> ");
                    }


                    out.println("</td></tr>");
                    pos++;
                }
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
