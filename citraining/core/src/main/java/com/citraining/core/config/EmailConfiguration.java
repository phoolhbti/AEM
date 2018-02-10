package com.citraining.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Citraining Email Service Configuration", description = "Email Service Configuration")
public @interface EmailConfiguration {
	@AttributeDefinition(name = "Default Email Address", description = "Default Email Address")
	String getDefaultEmailAdd();
	
	@AttributeDefinition(name = "Email From Address", description = "Email From Address")
	String getFromEmailAdd();
	
	@AttributeDefinition(name = "Email Address", description = "Email Address")
	String getAddress();
	
	@AttributeDefinition(name = "Name", description = "Name")
	String getName();
	
	@AttributeDefinition(name = "SMTP Name", description = "SMTP Name")
	String getSMTP();
	
	@AttributeDefinition(name = "Subject MultipleValues", description = "Subject Multi Configuration values")
	String[] getSubjectValues();
	
	@AttributeDefinition(name = "NumberValue", description = "Number values", type=AttributeType.INTEGER)
	int getNumberValue();

}
