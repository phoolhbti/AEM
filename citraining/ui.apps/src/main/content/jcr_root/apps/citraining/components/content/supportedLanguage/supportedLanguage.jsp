<!--/*
    Copyright 2015 Adobe Systems Incorporated
  
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
  
        http://www.apache.org/licenses/LICENSE-2.0
  
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/-->
<%@page import="com.citraining.core.utils.SearchUtils"%>
<%@include file="/apps/citraining/global.jsp"%>
<%@taglib prefix="citraining"
	uri="http://com.citraining.taglibs/authorbox/1.1"%>
<%
	final String DEFAULT_TITLE = "Supported Query Languages";
	String title = properties.get("sqlTitle", DEFAULT_TITLE);
%>
<c:set var="languages"
	value="<%=SearchUtils.getSupportedQueryLanguage(resourceResolver.adaptTo(Session.class))%>" />
<div>
	<ul>
		<c:forEach items="${languages}" var="result" varStatus="status">
			<li>${result}</li>
		</c:forEach>
	</ul>
</div>
<div>
	<%
		String pageName = SearchUtils.getLastMoidifedPage(resourceResolver.adaptTo(Session.class), "/content", "/libs/cq/personalization/templates/campaign");
		out.println("Hello worl.." + pageName);
	%>
</div>