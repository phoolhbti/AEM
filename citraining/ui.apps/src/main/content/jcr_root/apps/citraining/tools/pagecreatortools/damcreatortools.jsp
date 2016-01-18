<%@page import="com.day.cq.tagging.*,
    			com.day.cq.dam.api.*,
				com.day.cq.replication.*,
				java.net.URL"%>
<%@include file="/libs/foundation/global.jsp"%>
<html>
    <head>
        <title>DAM Crator tools Update</title>
    </head>
    <body>
        <%
            URL fileURL=new URL("http://www.rpgmonline.com/wp-content/uploads/2015/01/technology-wallpapers-hd-2014121-1000x439.jpg");
			AssetManager am=resource.getResourceResolver().adaptTo(AssetManager.class);
			Asset myAsset=am.createAsset("/content/dam/citraining/myassest.jpeg",fileURL.openStream(),"image/jpg",true);
			TagManager tm=resource.getResourceResolver().adaptTo(TagManager.class);
String pathMetadata=myAsset.getPath()+"/jcr:content/metadta";
             Resource resourceImage=   resource.getResourceResolver().resolve(pathMetadata);
			tm.setTags(resourceImage,new Tag[]{tm.resolve("/etc/tags/marketing/interest/")},true);

//Replicator is exposed as a services
Replicator r=sling.getService(Replicator.class);
r.replicate(currentNode.getSession(),ReplicationActionType.ACTIVATE,myAsset.getPath());
    %>
        <p>Assest created,tagged and activated</p>
    </body>
</html>