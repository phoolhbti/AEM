package com.citraining.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition (name = "Citraining Package Service Configuration", description = "Package Service Configuration")
public @interface PackageConfiguration {
	@AttributeDefinition (name = "Package Group Name", description = "Enter Package Group Name")
	String getPackageGroupName();

	@AttributeDefinition (name = "Package Name", description = "Enter Package Name")
	String getPackageName();

}
