package com.citraining.core.models;

import org.osgi.annotation.versioning.ConsumerType;

/**
 * Defines the {@code Title} Sling Model used for the {@code /apps/core/wcm/components/title} component.
 *
 * @since com.adobe.cq.wcm.core.components.models 11.0.0
 */
@ConsumerType
public interface Title {

    /**
     * Name of the configuration policy property that will store the default value for this title's HTML element type.
     *
     * @see #getType()
     * @since com.adobe.cq.wcm.core.components.models 11.1.0
     */
    String PN_DESIGN_DEFAULT_TYPE = "type";

    /**
     * Returns the text to be displayed as title.
     *
     * @return the title's text
     * @since com.adobe.cq.wcm.core.components.models 11.0.0; marked <code>default</code> in 12.1.0
     */
    default String getText() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the HTML element type (h1-h6) used for the markup.
     *
     * @return the element type
     * @since com.adobe.cq.wcm.core.components.models 11.0.0; marked <code>default</code> in 12.1.0
     */
    default String getType() {
        throw new UnsupportedOperationException();
    }
}