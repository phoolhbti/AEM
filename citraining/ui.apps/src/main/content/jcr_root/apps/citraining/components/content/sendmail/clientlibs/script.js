$(document).ready(function() {
	$('#submit').click(function() {
		var failure = function(err) {
			alert("Unable to retrive data " + err);
		};
		//Get the user-defined values that represent claim data to persist in the Adobe CQ JCR
		var hostPrefix = $('#hostPrefixF').val();
		var forumUrl = $('#forumUrlF').val();
		var modifiedByFullname = $('#name').val();
		var TopicSubject = $('#topicSubject').val();
		var topicUrl = $('#url').val();
		var message = $('#message').val();

		//Use JQuery AJAX request to post data to a Sling Servlet
		$.ajax({
			type : 'POST',
			url : '/bin/htlMailServlet',
			data : 'TopicSubject=' + TopicSubject + '&message=' + message,
			success : function(msg) {

				$('#message').val(msg);
			}
		});
	});

});