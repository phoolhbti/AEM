package com.citraining.core.report.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.report.BuildReport;
import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;

//This is a component so it can provide or consume services
	@Component	 
	@Service	 
	public class BuildReportImpl implements BuildReport  {	     
		//Inject a Sling ResourceResolverFactory
	    @Reference
	    private ResourceResolverFactory resolverFactory;
	     
	    @Reference
	    private DataSourcePool source;
	     
	     
	    /** Default log. */
	    protected final Logger log = LoggerFactory.getLogger(BuildReportImpl.class);
	     
	    public String jasperBuildReport(){         
	         
	        DataSource dataSource = null;
	        Connection connection = null;
	         
	        try{
	             
	             // Create a Connection object
	            connection =  getConnection();
	             
	            //Use JasperReport app logic in an AEM Service 
	            JasperReportBuilder report = DynamicReports.report();//a new report
	            report
	                    .columns(
	                            Columns.column("Customer Id", "custId", DataTypes.integerType()),
	                            Columns.column("First Name", "custFirst", DataTypes.stringType()),
	                            Columns.column("Last Name", "custLast", DataTypes.stringType()),
	                            Columns.column("Description", "custDesc", DataTypes.stringType()))
	                    .title(//title of the report
	                            Components.text("SimpleReportExample")
	                                    .setHorizontalAlignment(HorizontalAlignment.CENTER))
	                    .pageFooter(Components.pageXofY())//show page number on the page footer
	                    .setDataSource("SELECT custId, custFirst, custLast, custDesc FROM customer",
	                            connection);
	 
	                               
	                //Create an InputStream that represents the report. The InputSteam is used by an AEM service
	                //to write to the AEM DAM
	                ByteArrayOutputStream os = new ByteArrayOutputStream(); 
	                              
	                //export the report to a pdf file
	                report.toPdf(os);
	                  
	               InputStream is =  new ByteArrayInputStream(os.toByteArray());; 
	 
	               int length = is.available();
	                  
	                log.info("IS IS THIS BIG " +length);
	                  
	                //Persist the PDF Report in the AEM DAM
	                String reportDetails =  writeToDam(is, "JasperReport.pdf"); 
	                  
	                log.info("Details is " +reportDetails);
	                  
	                return "Report was successfully created";
	                  
	            }
	            catch (Exception e)
	            {
	                log.info(e.getMessage());
	            }
	              
	            return "ERROR" ; 
	              
	            }
	         
	        //Write the Jasper Report to the DAM 
	      //Save the uploaded file into the AEM DAM using AssetManager APIs
	        private String writeToDam(InputStream is, String fileName)
	        {
	        try
	        {
	            //Inject a ResourceResolver
	            ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
	              
	            //Use AssetManager to place the file into the AEM DAM
	            com.day.cq.dam.api.AssetManager assetMgr = resourceResolver.adaptTo(com.day.cq.dam.api.AssetManager.class);
	             
	             
	            String newFile = "/content/dam/reports/"+fileName ; 
	             
	             
	            assetMgr.createAsset(newFile, is,"application/pdf", true);
	                  
	            // Return the path to the file was stored
	            return newFile;
	        }
	        catch(Exception e)
	        {
	            log.info("Error IS "+e.getMessage())  ;
	        }
	        return null;
	        }
	         
	         
	         
	      //Returns a connection using the configured DataSourcePool 
	        private Connection getConnection()
	        {
	                 DataSource dataSource = null;
	                 Connection con = null;
	                 try
	                 {
	                     //Inject the DataSourcePool right here! 
	                     dataSource = (DataSource) source.getDataSource("customer");
	                     con = dataSource.getConnection();
	                     return con;
	                      
	                   }
	                 catch (Exception e)
	                 {
	                     e.printStackTrace(); 
	                 }
	                     return null; 
	        }
	 
	    }