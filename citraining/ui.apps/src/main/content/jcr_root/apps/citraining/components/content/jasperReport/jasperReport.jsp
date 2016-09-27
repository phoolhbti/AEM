<%@include file="/libs/foundation/global.jsp"%>
<h1><%= properties.get("title", currentPage.getTitle()) %></h1>
<%
   
com.citraining.core.report.BuildReport report = sling.getService(com.citraining.core.report.BuildReport.class);
String ff = report.jasperBuildReport();
%>
   
<h2>Report Component</h2>
<h3>Report Details: <%=ff%></h3>