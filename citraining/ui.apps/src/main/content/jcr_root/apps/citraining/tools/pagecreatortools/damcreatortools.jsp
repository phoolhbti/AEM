<%@page
	import="com.day.cq.tagging.*,
    			com.day.cq.dam.api.*,
				com.day.cq.replication.*,
				java.net.URL"%>
<%@include file="/apps/citraining/global.jsp"%>
<html>
<head>
<title>File Creator and publish files in DAM</title>
</head>
<body>
	<%
		URL fileURL = new URL("https://avatars3.githubusercontent.com/u/3950361?s=400&u=3804a57a08c58e3ef4e522ec688e6a5a93421dad&v=4");
		AssetManager am = resource.getResourceResolver().adaptTo(AssetManager.class);
		Asset myAsset = am.createAsset("/content/dam/citraining/myself.jpeg", fileURL.openStream(), "image/jpg", true);
		TagManager tm = resource.getResourceResolver().adaptTo(TagManager.class);
		String pathMetadata = myAsset.getPath() + "/jcr:content/metadta";
		Resource resourceImage = resource.getResourceResolver().resolve(pathMetadata);
		tm.setTags(resourceImage, new Tag[] { tm.resolve("/etc/tags/marketing/interest/") }, true);

		//Replicator is exposed as a services
		Replicator replicator = sling.getService(Replicator.class);
		replicator.replicate(currentNode.getSession(), ReplicationActionType.ACTIVATE, myAsset.getPath());
	%>
	<p>Asset created,tagged and activated</p>
</body>
</html>