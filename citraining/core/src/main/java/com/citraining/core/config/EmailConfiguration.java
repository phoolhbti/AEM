package com.citraining.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Citraining Email Service Configuration", description = "Email Service Configuration")
public @interface EmailConfiguration {
	@AttributeDefinition(name = "Default Email Address", description = "Default Email Address")
	String getDefaultEmailAdd();
	
	@AttributeDefinition(name = "Email From Address", description = "Email From Address")
	String getFromEmailAdd();
	
	@AttributeDefinition(name = "Email Address", description = "Email Address")
	String getAddress();
	
	@AttributeDefinition(name = "Label for this SMTP service", description = "Label for this SMTP service")
	String getMailServiceName();
	
	@AttributeDefinition(name = "SMTP Name", description = "SMTP Name")
	String getSMTP();
	
	@AttributeDefinition(name = "Email Subject", description = "Email Subject")
	String getSubjectValues();

}
