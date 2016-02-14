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
<%@include file="/libs/foundation/global.jsp"%>
<%@taglib prefix="citraining" uri="http://com.citraining.taglibs/authorbox/1.1" %>
<jsp:useBean id="pageHelper" class="com.citraining.core.PageHelper"/>


<c:forEach items="<%=currentPage.listChildren()%>" var="currentChild" varStatus="status">
    <p><c:out value="${status.count}"/>:<c:out value="${currentChild.title}"/></p>  
</c:forEach>
<citraining:authorbox><c:out value="${pageHelper.message}"/></citraining:authorbox>