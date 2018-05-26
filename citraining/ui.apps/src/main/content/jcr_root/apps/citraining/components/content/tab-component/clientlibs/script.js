function setIframeHeight(id) {
          var ifrm = parent.document.getElementById(id);
          if (ifrm) {
           var doc = ifrm.contentDocument ? ifrm.contentDocument :
            ifrm.contentWindow.document;
           ifrm.style.visibility = 'hidden';
           ifrm.style.height = "10px"; // reset to minimal height ...
           // IE opt. for bing/msn needs a bit added or scrollbar appears - changed 4 px addition to 10 px due to author instance
           ifrm.style.height = getDocHeight(doc) + "px";
           ifrm.style.visibility = 'visible';
          }
         }
        
        function getDocHeight(doc) {
          doc = doc || document;
          // stackoverflow.com/questions/1145850/
          var body = doc.body,
           html = doc.documentElement;
          var height = Math.max(body.scrollHeight, body.offsetHeight,
           html.clientHeight, html.scrollHeight, html.offsetHeight);
          return height;
         }