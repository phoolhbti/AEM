package com.citraining.core.models;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.osgi.service.component.annotations.Component;

@Component (service = ModelExporter.class)
public class JAXBExporter implements ModelExporter {
@Override
	public <T> T export(Object model, Class<T> clazz, Map<String, String> options) throws org.apache.sling.models.factory.ExportException {
		StringWriter sw = new StringWriter();
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(model, sw);
		} catch (JAXBException e){
			e.printStackTrace();
		}
		return (T) sw.toString();
	}
@Override
	public String getName() {
		return "jaxb";
	}
@Override
	public boolean isSupported(Class<?> model) {
		return true;
	}
}