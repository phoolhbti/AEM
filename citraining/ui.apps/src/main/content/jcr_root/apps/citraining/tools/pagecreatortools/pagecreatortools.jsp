<%@page
	import="com.day.cq.tagging.*,
    			com.day.cq.wcm.api.*,
				com.day.cq.replication.*"%>
<%@include file="/apps/citraining/global.jsp"%>
<html>
<head>
<title>Page Creator tools Update</title>
</head>
<body>
	<%
		Page customPage = pageManager.create("/content/citraining/en", "TestPage", "/apps/citraining/templates/page-content", "Hey This is new page");
		TagManager tagManager = resource.getResourceResolver().adaptTo(TagManager.class);
		tagManager.setTags(customPage.getContentResource(), new Tag[] { tagManager.resolve("/etc/tags/marketing/interest/") }, true);

		//Replicator is exposed as a services
		Replicator replicator = sling.getService(Replicator.class);
		replicator.replicate(currentNode.getSession(), ReplicationActionType.ACTIVATE, customPage.getPath());
	%>
	<p>Page created,tagged and activated</p>
</body>
</html>