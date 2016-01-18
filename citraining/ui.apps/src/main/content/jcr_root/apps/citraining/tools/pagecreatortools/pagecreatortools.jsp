<%@page import="com.day.cq.tagging.*,
    			com.day.cq.wcm.api.*,
				com.day.cq.replication.*"%>
<%@include file="/libs/foundation/global.jsp"%>
<html>
    <head>
        <title>Page Crator tools Update</title>
    </head>
    <body>
        <%
			Page p=pageManager.create("/content/citraining/en","TestPage","/apps/citraining/templates/page-content","Hey This is new page");
			TagManager tm=resource.getResourceResolver().adaptTo(TagManager.class);
tm.setTags(p.getContentResource(),new Tag[]{tm.resolve("/etc/tags/marketing/interest/")},true);

//Replicator is exposed as a services
Replicator r=sling.getService(Replicator.class);
r.replicate(currentNode.getSession(),ReplicationActionType.ACTIVATE,p.getPath());
    %>
        <p>Page created,tagged and activated</p>
    </body>
</html>