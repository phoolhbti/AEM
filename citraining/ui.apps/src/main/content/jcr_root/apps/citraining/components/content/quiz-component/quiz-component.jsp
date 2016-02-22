<%@include file="/libs/foundation/global.jsp"%>

<% 
	String url = properties.get("configurl", String.class);
if(url!=null && url.length()>0) {
    com.aemquiz.aemquiz.core.services.QuizImpl s = sling.getService(com.aemquiz.aemquiz.core.services.QuizImpl.class);
%> 

<body id="slickQuiz">
    <h1 class="quizName"></h1>
    <div class="quizArea">
        <div class="quizHeader">
            <a class="startQuiz" href="">Get Started!</a>
        </div>
    </div>
    <div class="quizResults">
        <h3 class="quizScore">You Scored: <span></span></h3>
        <h3 class="quizLevel"><strong>Ranking:</strong> <span></span></h3>
        <div class="quizResultsCopy"></div>
    </div>
    
    <script>

        var quizJSON = <%= s.getData(url) %> 
            data = <%= s.getOptions(url) %> 
            
    </script>
<%
} else { 
	out.print("Please Author the URL where configurations are saved");
}
%>
