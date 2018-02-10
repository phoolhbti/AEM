package com.citraining.core.config;

import org.apache.commons.lang.StringUtils;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Citraining Annotation Demo Scheduler - OSGi")
public @interface SchedulerConfig {
    @AttributeDefinition(
        name="My parameter",
        description="Sample String parameter")
    String myParameter() default StringUtils.EMPTY;

    @AttributeDefinition(
        name = "Concurrent",
        description = "Schedule task concurrently",
        type = AttributeType.BOOLEAN
    )
    boolean schedulerConcurrent() default true;

    @AttributeDefinition(
        name = "Expression",
        description = "Cron-job expression. Default: run every 30 minute.",
        type = AttributeType.STRING
    )
    String schedulerExpression() default "*/30 * * * * ?";
}