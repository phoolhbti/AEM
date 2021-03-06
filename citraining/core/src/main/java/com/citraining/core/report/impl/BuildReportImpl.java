package com.citraining.core.report.impl;


import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.report.BuildReport;
import com.day.commons.datasource.poolservice.DataSourcePool;

@Component(service = BuildReport.class)
public class BuildReportImpl implements BuildReport {

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Reference
	private DataSourcePool dataSourcePool;

	protected final Logger logger = LoggerFactory.getLogger(BuildReportImpl.class);

	public String jasperBuildReport() {

		try{

			return "Report was successfully created";

		} catch (Exception e){
			logger.info(e.getMessage());
		}

		return "ERROR";

	}

	// Write the Jasper Report to the DAM
	/*
	 * // Save the uploaded file into the AEM DAM using AssetManager APIs private String writeToDam(InputStream is, String fileName) { try {
	 * ResourceResolver resourceResolver = CommonUtil .getResourceResolver(resolverFactory); com.day.cq.dam.api.AssetManager assetMgr =
	 * resourceResolver .adaptTo(com.day.cq.dam.api.AssetManager.class); String newFile = "/content/dam/reports/" + fileName;
	 * assetMgr.createAsset(newFile, is, "application/pdf", true); // Return the path to the file was stored return newFile; } catch (Exception e) {
	 * logger.info("Error IS " + e.getMessage()); } return null; } // Returns a connection using the configured DataSourcePool private Connection
	 * getConnection() { try { // Inject the DataSourcePool right here! DataSource dataSource = (DataSource) dataSourcePool
	 * .getDataSource("customer"); Connection connection = dataSource.getConnection(); return connection; } catch (Exception e) { e.printStackTrace();
	 * } return null; } public boolean generateReport(String reportName) { Connection connection = getConnection(); JasperReportBuilder jsperReport =
	 * DynamicReports.report(); jsperReport .columns( Columns.column("Customer Id", "custId", DataTypes.integerType()), Columns.column("First Name",
	 * "custFirst", DataTypes.stringType()), Columns.column("Last Name", "custLast", DataTypes.stringType()), Columns.column("Description",
	 * "custDesc", DataTypes.stringType())) .title(Components.text(reportName).setHorizontalAlignment( HorizontalAlignment.CENTER))
	 * .pageFooter(Components.pageXofY()) .setDataSource( "SELECT custId, custFirst, custLast, custDesc FROM customer", connection); try {
	 * jsperReport.show(); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); InputStream inputStream = new
	 * ByteArrayInputStream( byteArrayOutputStream.toByteArray()); // int length = inputStream.available(); // logger.info("IS IS THIS BIG " +
	 * length); // Persist the PDF Report in the AEM DAM String reportDetails = writeToDam(inputStream, "JasperReport.pdf"); // jsperReport.toPdf(new
	 * FileOutputStream("D:/report.pdf")); } catch (DRException e) { e.printStackTrace(); return false; } return true; }
	 */
}