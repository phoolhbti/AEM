<%@include file="/libs/fd/af/components/guidesglobal.jsp"%>
<%@include file="/libs/foundation/global.jsp"%>
<%@page
	import="com.day.cq.wcm.foundation.forms.FormsHelper,
             org.apache.sling.api.resource.ResourceUtil,
             org.apache.sling.api.resource.ValueMap"%>
<%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0"%>
<%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0"%>
<cq:defineObjects />

<sling:defineObjects />
<%

    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String city = request.getParameter("city");
    String address = request.getParameter("address");

    com.citraining.core.services.HandleForm formHandler = sling.getService(com.citraining.core.services.HandleForm.class);
    formHandler.injestFormData(firstName,lastName,city, address);


%>