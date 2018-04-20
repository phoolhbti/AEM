<%@include file="/apps/citraining/global.jsp"%>

<script>  
$(document).ready(function() {  
    $('body').hide().fadeIn(5000);         
$('#submit').click(function() {
    var failure = function(err) {
             alert("Unable to retrive data "+err);
   };  
    //Get the user-defined values that represent claim data to persist in the Adobe CQ JCR
    var WordPressId= $('#wordpressid').val() ; 
    var myPassword= $('#password').val() ; 
    var url= $('#url').val() ; 
    //Use JQuery AJAX request to post data to a Sling Servlet
    $.ajax({
         type: 'GET',  
       	 contentType: 'text/plain', 
         url:'/bin/myWordPress',
         data:'wordpressid='+ WordPressId+'&mypassword='+ myPassword+'&url='+ url,
         success: function(msg){
			$('#wordpress').val(msg);
         }
     });
  });
     
}); // end ready
</script>
<form method="#">
	<table border="1" align="left">
		<tr>
			<td><label for="wordpressid" id="wordpressidL">A WordPress Id</label></td>
			<td><input id="wordpressid" name="wordpressid" type="text" value="scottwpsite"></td>
		</tr>
		<tr>
			<td><label for="password" id="passwordl">A.2. Password</label></td>
			<td><input id="password" name="password" type="text" value="AemRocks"></td>
		</tr>
		<tr>
			<td><label for="url" id="URLl">B2. URL</label></td>
			<td><input id="url" name="url" type="text" size="39" value="https://scottwpsite.wordpress.com/xmlrpc.php"></td>
		</tr>
		<tr>
			<td></td>
			<td><textarea id="wordpress" rows="4" cols="50"></textarea></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="button" value="Get WordPress Data"	name="submit" id="submit"></td>
		</tr>
	</table>
</form>
