package com.citraining.core.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.WCMMode;

public class AuthorBoxTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorBoxTag.class);

	private transient PageContext aemPageContext = null;

	@Override
	public int doAfterBody() throws JspException {
		try{
			BodyContent bodyContent = super.getBodyContent();
			String bodyString = bodyContent.getString();
			JspWriter out = bodyContent.getEnclosingWriter();
			if (WCMMode.fromRequest(this.aemPageContext.getRequest()) == WCMMode.EDIT){
				bodyString = "<div style=\"border-width:1px;border-style:solid;border-color:blue\">" + bodyString + "</div>";
			} else if (WCMMode.fromRequest(this.aemPageContext.getRequest()) == WCMMode.DISABLED){
				bodyString = "<div>" + bodyString + "</div>";
			}
			out.write(bodyString);
		} catch (IOException e){
			LOGGER.error("I/O Error in authorbox tag :{}", e);
		}
		return SKIP_BODY;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.aemPageContext = pageContext;
	}

	@Override
	public void release() {
		this.aemPageContext = null;
	}

}
