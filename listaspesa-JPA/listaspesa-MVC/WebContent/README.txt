E' un progetto di tipo JPA
Il progetto ha bisogno di un Server per funzionare, nel mio caso ho creato un "local Server" di tipo Tomcat v8.5
collegato al server scaricato in locale TomeEE 7.0.4-webProfile. In quest'ultimo ho inserito nella cartella /lib il file
mysql_connector_java. Inoltre ho inserito i jar "hibernate-connector", "hibernate-core" e "hibernate-entitymanager",
ed il progetto si collega al DB mediante la configurazione descritta nel "persistence.xml".
In un contesto Java SE, per lavorare con JPA bisogna necessariamente passare dall'EntityManagerFactory: una volta creato 
un EntityManager dalla factory, bisogna chiuderlo per liberare le risorse.

*Attenzione: Per ottenere la factory si usa l'annotazione @PersistenceUnit (per l'EntityManager invece era @PersistenceContext!). 

Il codice è molto semplice: viene creato l'EntityManager e nella clausola finally dei vari metodi CRUD viene chiuso, 
mentre la transazione per eseguire il salvataggio della persona è delegata al programmatore(non al Container)