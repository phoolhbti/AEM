package com.citraining.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition (name = "Component Mapping Service Configuration", description = "Select a Page path, which you want to convert from src template to dest template")
public @interface ComponentMappingConfig {
	@AttributeDefinition (name = "Src path", description = "Select a Page path, which you want to convert from src template to dest template")
	String getSourcePath();

	@AttributeDefinition (name = "Dest template", description = "Select Destination template")
	String getDistinationTemplate();

	@AttributeDefinition (name = "Component Manpping from to", description = "Plase specify which component need to change e.g 'collab/calendar/components/exportlink' to 'geometrixx-media/components/popular-articles'")
	String[] getComponentMappings();

}