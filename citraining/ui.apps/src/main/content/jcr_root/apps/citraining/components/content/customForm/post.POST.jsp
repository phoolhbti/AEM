<%@include file="/apps/citraining/global.jsp"%>
<%@page
	import="com.day.cq.wcm.foundation.forms.FormsHelper,
             org.apache.sling.api.resource.ResourceUtil,
             org.apache.sling.api.resource.ValueMap,
             com.citraining.core.services.HandleForm"%>
<%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0"%>
<%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0"%>
<cq:defineObjects />

<sling:defineObjects />
<%
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String city = request.getParameter("city");
	String address = request.getParameter("address");
	HandleForm formHandler = sling.getService(HandleForm.class);
	formHandler.injestFormData(firstName, lastName, city, address);
%>