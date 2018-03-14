# AEM Citraining template

This is a project template for AEM-based applications. It is intended as a best-practice set of examples as well as a potential starting point to develop your own functionality.

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* taglib: Java bundle containing all JSTL functionality.
* jax-ws: integration of jersey with OSGI
* media: used for static code development 
* ui.apps: contains the /apps (and /etc) parts of the project, i.e. JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps
* ui.tests: Java bundle containing JUnit tests that are executed server-side. This bundle is not to be deployed onto production.
* ui.launcher: contains glue code that deploys the ui.tests bundle (and dependent bundles) to the server and triggers the remote JUnit execution
* soap-weather: This module give soap web service integration with AEM. It provide sample step how to generate java class form WSDL file and consume these web service as OSGI service.   

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance, you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish
    
Alternatively, to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Testing

There are three levels of testing contained in the project:

* Unit test in core: this showcases classic unit testing of the code contained in the bundle. To test, execute:

    mvn clean test

* server-side integration tests: this allows to run unit-like tests in the AEM-environment, i.e. on the AEM server. To test, execute:

    mvn clean integration-test -PintegrationTests

* client-side Hobbes.js tests: JavaScript-based browser-side tests that verify browser-side behavior. To test:

In the browser, open the page in 'Developer mode', open the left panel and switch to the 'Tests' tab and find the generated 'MyName Tests' and run them.
	added Guns dependinces

Replacement of repository.loginAdministrative method with user mapper service in AEM:
Value in OSGI service is:

com.citraining.core.services.WriteService

Reference:
http://www.wemblog.com/2014/08/how-to-use-sessions-and-resource.html
http://stackoverflow.com/questions/31350548/resourceresolverfactory-getserviceresourceresolver-throws-exception-in-aem-6-1
---------------------

## How to install a local JAR/OSGI file in local maven repository:
mvn install:install-file -Dfile=D:/AEM/AEMSample/AEM/customUrber/plugins/dynamic-report-core_1.0.0.jar -DgroupId=com.citraining -DartifactId=com.citraining.reports -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true

## How to build and install a package, using command line with argument as password and Host name: 
mvn -PautoInstallPackage clean install -Dcrx.host=production.hostname -Dcrx.password=productionpasswd

## How to declare a java class as OSGI service using OSGI annotation:
Declarative Services (DS) is one way of developing components and services for OSGi, It is easy to learn, easy to manage and maintain and provides all required features.
Service Component Runtime (SCR) implementation will read these descriptor files (generated xml files) and manage the components and services.

@Component(service={MyService.class},immediate=true)
https://blog.osoco.de/2015/08/osgi-components-simply-simple-part-ii/
----------------
https://stackoverflow.com/questions/9819090/how-to-convert-jar-to-osgi-bundle-using-eclipse-and-bndtools

## Why use sling model:
1. Entirely annotation driven. "Pure" POJOs.
2. Use standard annotations where possible.
3. Pluggable OOTB, support resource properties (via ValueMap), SlingBindings, OSGi services, request attributes
4. Adapt multiple objects - minimal required Resource and SlingHttpServletRequest
5. Client does not know/care that these objects are different from any other adapter factory
6. Support both classes and interfaces.
7. Work with existing Sling infrastructure (i.e. not require changes to other bundles).
-----------------------------------------
Properties for servelet:
@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.extensions=html",
        "sling.servlet.selectors=foo",
        "sling.servlet.paths=/bin/foo",
        "sling.servlet.paths=/bin/bar",
        "sling.servlet.methods=get",
        "sling.servlet.resourceTypes=nt:file",
        "sling.servlet.resourceTypes=project/components/component"
    }
)
Reference:
1. http://www.nateyolles.com/blog/2017/05/osgi-declarative-services-annotations-in-aem

## Maven command to generate source code from WSDL using maven plugin
mvn generate-sources -Pgenerate-client-ws
-------------------------------------------------
http://blog.vogella.com/2017/02/13/control-osgi-ds-component-instances/
https://gist.github.com/nateyolles
Configuration factory in OSGI: 
https://techrevel.blog/2017/04/12/felix-configuration-factory/
https://github.com/osgi/osgi.enroute.examples

## Custom Sling Model exporter:
Default JSON exporter: http://localhost:4502/content/we-retail/ca/en/experience/jcr:content/root/hero_image.tidy.json
http://sgaem.blogspot.in/2017/06/sling-model-exporter-in-aem-63.html
http://localhost:4502/content/we-retail/ca/en/experience/jcr:content/root/hero_image.model.json
@ExporterOption (name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true")

http://sgaem.blogspot.in/2018/01/custom-sling-model-exporter-in-aem-63.html
Configruation of admin session:
https://helpx.adobe.com/experience-manager/using/jqom.html
How to access retriwe service in OSGI 
http://blog.vogella.com/2016/09/26/configuring-osgi-declarative-services/
