package com.citraining.core.models;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.citraining.core.beans.HeroBean;
import com.drew.lang.annotations.NotNull;

public class HeroTextComponent extends WCMUsePojo {

	private HeroBean heroBean = null;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void activate() throws Exception {
		@NotNull
		Node currentNode = getResource().adaptTo(Node.class);
		heroBean = new HeroBean();
		if (null != currentNode){
			if (currentNode.hasProperty("jcr:heading")){
				heroBean.setHeading(currentNode.getProperty("./jcr:heading").getString());
			}
			if (currentNode.hasProperty("jcr:description")){
				heroBean.setDescription(currentNode.getProperty("./jcr:description").getString());
			}
			if (currentNode.hasProperty("./jcr:drop")){

				String myDrop = currentNode.getProperty("./jcr:drop").getString();
				heroBean.setDrop(myDrop);
			}
			if (currentNode.hasProperty("./kitten")){
				String myCheck = currentNode.getProperty("./kitten").getString();
				heroBean.setCheck(myCheck);
			}
			if (currentNode.hasProperty("./color")){
				String color = currentNode.getProperty("./color").getString();
				heroBean.setColor(color);
			}
		}
	}

	public HeroBean getHeroBean() {
		return this.heroBean;
	}
}