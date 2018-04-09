package com.citraining.core.services;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pcha21 on 4/12/2017.
 */
@Component (service = HandleForm.class)
public class HandleFormImpl implements HandleForm {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
@Override
	public void injestFormData(String firstName, String lastName, String city, String address) {
		String fullAddress = firstName + " LastName: " + lastName + " City: " + city + " Address " + address;
		log.info("Data posted from the AEM EXAMPLE form - First:{} ", fullAddress);
	}
}
