package com.citraining.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Citraining Email Service Configuration", description = "Email Service Configuration")
public @interface EmailConfiguration {
	@AttributeDefinition(name = "Default Email Address", description = "Default Email Address")
	String getDefaultEmailAdd() default "phool@gmail.com";
	
	@AttributeDefinition(name = "Email From Address", description = "Email From Address")
	String getFromEmailAdd() default "pc@gmail.com";
	
	@AttributeDefinition(name = "Email Address", description = "Email Address")
	String getAddress() default "address@gmail.com";
	
	@AttributeDefinition(name = "Label for this SMTP service", description = "Label for this SMTP service")
	String getMailServiceName() default "InternetA";
	
	@AttributeDefinition(name = "SMTP Name", description = "SMTP Name")
	String getSMTP() default "localhost";
	
	@AttributeDefinition(name = "Email Subject", description = "Email Subject")
	String getSubjectValues() default "Hello Wrold";

}
