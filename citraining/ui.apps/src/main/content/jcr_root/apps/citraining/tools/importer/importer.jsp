<%@page import="javax.jcr.*,javax.jcr.query.*,java.util.*,com.day.cq.commons.jcr.JcrUtil"%>
<%@include file="/libs/foundation/global.jsp"%>
<html>
    <head>
        <title>Compaign Update</title>
    </head>
    <body>
        <%
			String q="/jcr:root/content/campaigns/citraining/operating-system/windows//*[jcr:contains(., 'foundation/components/parsys')] order by @jcr:score";
Query query=currentNode.getSession().getWorkspace().getQueryManager().createQuery(q,"xpath");
NodeIterator result=query.execute().getNodes();
int counter=0;
while(result.hasNext()){
Node n=result.nextNode();
    Node newTextNode=JcrUtil.createUniqueNode(n,"newtext","nt:unstructured",currentNode.getSession());
    newTextNode.setProperty("sling:resourceType","foundation/components/text");
    newTextNode.setProperty("text","<p>Even more text Here</p>");
     newTextNode.setProperty("textIsRich","true");
    counter++;
}
currentNode.getSession().save();
out.println("added Nodes:"+counter);
    %>
    </body>
</html>