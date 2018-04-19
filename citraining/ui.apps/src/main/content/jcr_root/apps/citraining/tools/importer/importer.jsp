<%@page
	import="javax.jcr.*,
	javax.jcr.query.*,
	java.util.*,
	com.day.cq.commons.jcr.JcrUtil"%>
<%@include file="/apps/citraining/global.jsp"%>
<html>
<head>
<title>Updating existing Campaign</title>
</head>
<body>
	<%
		String queryString = "/jcr:root/content/campaigns/citraining/operating-system/windows//*[jcr:contains(., 'foundation/components/parsys')] order by @jcr:score";
		Query query = currentNode.getSession().getWorkspace().getQueryManager().createQuery(queryString, "xpath");
		NodeIterator result = query.execute().getNodes();
		int counter = 0;
		while (result.hasNext()){
			Node node = result.nextNode();
			Node newTextNode = JcrUtil.createUniqueNode(node, "newtext", "nt:unstructured", currentNode.getSession());
			newTextNode.setProperty("sling:resourceType", "foundation/components/text");
			newTextNode.setProperty("text", "<p>Even more text Here</p>");
			newTextNode.setProperty("textIsRich", "true");
			counter++;
		}
		currentNode.getSession().save();
		out.println("added Nodes:" + counter);
	%>
</body>
</html>