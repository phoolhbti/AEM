package com.citraining.core.models;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.citraining.core.beans.HeroBean;

public class HeroTextComponent extends WCMUsePojo {

	private HeroBean HeroBean = null;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void activate() throws Exception {

		Node currentNode = getResource().adaptTo(Node.class);

		HeroBean = new HeroBean();

		if (currentNode.hasProperty("jcr:Heading")) {
			HeroBean.setHeading(currentNode.getProperty("./jcr:Heading")
					.getString());
		}
		if (currentNode.hasProperty("jcr:description")) {
			HeroBean.setDescription(currentNode
					.getProperty("./jcr:description").getString());
		}
		if (currentNode.hasProperty("./jcr:drop")) {

			String myDrop = currentNode.getProperty("./jcr:drop").getString();
			HeroBean.setDrop(myDrop);
		}
		if (currentNode.hasProperty("./kitten")) {
			String myCheck = currentNode.getProperty("./kitten").getString();
			HeroBean.setCheck(myCheck);
		}
		if (currentNode.hasProperty("./color")) {
			String color = currentNode.getProperty("./color").getString();
			HeroBean.setColor(color);
		}

	}

	public HeroBean getHeroBean() {
		return this.HeroBean;
	}
}