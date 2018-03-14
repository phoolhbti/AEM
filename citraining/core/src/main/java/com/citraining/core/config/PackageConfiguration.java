package com.citraining.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition (name = "Citraining Package Service Configuration", description = "Package Service Configuration")
public @interface PackageConfiguration {
	@AttributeDefinition (name = "Package Group Name", description = "Enter Package Group Name")
	String getPackageGroupName();

	@AttributeDefinition (name = "Package Name", description = "Enter Package Name")
	String getPackageName();
	
	@AttributeDefinition (name = "Node Path", description = "Plase specify which content  need to change e.g '/content/geometrixx/en_UK/products'")
	String[] getNodePath();
	@AttributeDefinition (name = "Included Related Content", description = "Included Related Content")
	boolean isRealatedContent() default true;

}
