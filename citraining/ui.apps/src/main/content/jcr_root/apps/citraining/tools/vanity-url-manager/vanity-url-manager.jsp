<%--
  #%L
  @Author - Lokesh BS
     http://adobeaemclub.com
  --%>

<%@include file="/libs/foundation/global.jsp" %><%
%><%@page session="false" %><%

    pageContext.setAttribute("pagePath", resourceResolver.map(currentPage.getPath()));
    pageContext.setAttribute("resourcePath", resourceResolver.map(resource.getPath()));
    pageContext.setAttribute("favicon", component.getPath() + "/clientlibs/images/favicon.ico");

%><!doctype html><html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Vanity URL Manager</title>

    <link rel="shortcut icon" href="${favicon}"/>

    <cq:includeClientLib css="vanity-url-manager.app"/>
</head>

<body>
    <div id="vanity-url-manager-app">
        <header class="top">

            <div class="logo">
                <a href="/"><i class="icon-marketingcloud medium"></i></a>
            </div>

            <nav class="crumbs">
                <a href="/miscadmin">Tools</a>
                <a href="${pagePath}.html">Vanity URL Manager</a>
            </nav>
        </header>


         <div class="page" role="main" ng-app="vanityUrlManager" 
                 ng-controller="VanCtrl"
        		 ng-init= "init();">

           <div ng-show="notifications.length  > 0"
                 class="notifications ng-cloak">
                <div ng-repeat="notification in notifications">
                    <div class="alert {{ notification.type }}">
                        <button class="close" data-dismiss="alert">&times;</button>
                        <strong>{{ notification.title }}</strong>

                        <div>{{ notification.message }}</div>
                    </div>
                </div>
            </div> 

            <div class="content">
                <div class="content-container">
                    <div class="content-container-inner">

                        <h1>Vanity URL Manager</h1>

                        <!-- List the Sites here -->


						<uib-tabset>
                            	<uib-tab select="list()">
                             <uib-tab-heading class="ng-cloak"> Manage  </uib-tab-heading>

                                <div class="btn-group ng-cloak" data-ng-class="{open: open}">
                                    <button class="btn">Filter by Sites</button>
                                    <button class="btn dropdown-toggle" data-ng-click="open=!open"><span class="caret"></span>

                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                                      <!--  <li><a data-ng-click="checkAll()"><i class="icon-ok-sign"></i>  Check All</a>
                    
                                        </li>
                                        <li><a data-ng-click="selectedCompany=[];"><i class="icon-remove-sign"></i>  Uncheck All</a>
                    
                                        </li>
                                        <li class="divider"></li> -->
                                        <li data-ng-repeat="site in sites"> <a data-ng-click="setSelectedSite()">{{site}}<span data-ng-class="isChecked(site)"></span></a>
        
                                        </li>
                                    </ul>
                                </div>
        

                                <table class="data index-table ng-cloak">
                                    <tr ng-if="(!indexes || indexes.length === 0)" colspan="4">
                                        <td>No data found</td>
                                      </tr>
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Site</th>
                                            <th>Page Title</th>
                                            <th>Vanity URL</th>
                                            <th>Remove</th>
        
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="index in filtered = (indexes | siteFilter:selectedSite)" >
											<td>
                                                <div ng-if="(index.isVanityDup == 'true')" >
                                                    <span class="icon-warning-sign yellow" tooltip="Duplicate Vanity URL"></span>
                                                </div>
                                            </td>        
                                            <td>{{ index.Site }}</td>
        
                                            <td>{{ index.Title }}</td>
        
                                            <td>
                                                <div ng-repeat="vanity in index.VanityUrl track by $index">
                                                    <a ng-href="{{vanity}}.html" editable-text="index.VanityUrl[$index]" onaftersave="saveVanity(index.VanityUrl, index.Path, index.Site)">{{vanity}}</a>
                                                </div>
                                               <!-- <span editable-text="index.VanityUrl" e-name="VanityUrl" e-form="rowform">{{index.VanityUrl}}</span> 
                                                 <div ng-repeat="vanity in index.VanityUrl track by $index">
                                                    <a ng-href="{{vanity}}.html" target="_blank">{{vanity}}</a>
                                                </div>
                                               <div ng-repeat="(key, value) in index.VanityUrl">
                                                    <span editable-text="{{index.VanityUrl}}" e-name="VanityUrl" e-form="rowform"  >

                                                        <a ng-href="{{value}}.html" target="_blank">{{value}}</a>
                                                    </span>
                                                </div> -->
                                               <!-- <span data-ng-hide="editMode">{{ index.VanityUrl }}</span>
                                                 <input type="text" data-ng-show="editMode" data-ng-model="index.VanityUrl" data-ng-required /> -->
        
                                            </td>
        
                                             <td style="white-space: nowrap">
                                                <!-- form 
                                                <form editable-form name="rowform" onbeforesave="saveVanity($data, index.Path, index.Site)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == index">
                                                  <button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary">
                                                    save
                                                  </button>
                                                  <button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default">
                                                    cancel
                                                  </button>
                                                </form> -->
                                                <div class="buttons" ng-show="!rowform.$visible">
                                                  <!--<button class="btn btn-primary" ng-click="rowform.$show()">edit</button> -->
                                                  <button class="btn btn-danger" ng-click="removeVanity($index)">del</button>
                                                </div>  
                                              </td>      
        
                                        </tr>     
        
                                    </tbody>
                                </table>

                            </uib-tab>


						 <uib-tab select="treelist()">
                             <uib-tab-heading class="ng-cloak"> Add  </uib-tab-heading>
                             <table>
								<tr>
                                    <td width="25%">
                                     <div class="ng-cloak">
                                         <div><h2> Select a Page</h2> </div>
                                         <div ng-show="app.running"
                                             class="running-indicator spinner large"></div>
                                        <div
                                          data-angular-treeview="true"
                                          data-tree-model="treesites"
                                          data-node-id="path"
                                          data-node-label="title"
                                          data-node-children="child" >
                                        </div>
                                     </div>
                                    </td>
                                    <td>
                                            <div class="ng-cloak" style="margin:10px 0 30px 0; padding:10px; background-color:#EEEEEE; border-radius:5px; font:12px;">
                                            <span class="ng-cloak"><b>Selected Page</b> : {{currentNode.path}}</span>
            
                                            <table ng-if="(currentNode.path != null)" class="data index-table ng-cloak">
                                                <thead>
                                                    <tr>
                                                       <!-- <th class="check"><label><input type="checkbox" ng-model="toggleChecks"><span></span></label></th> -->
                                                        <th>Site</th>
                                                        <th>Page Path</th>
                                                        <th>Page Title</th>
                                                        <th>Vanity URL</th>
                                                        <th>Edit</th>        
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr> 
                                                        <td> 
                                                            <span e-name="Site" e-form="addform" >
                                                                {{currentNode.site}}
                                                            </span>        
                                                        </td>
                                                        <td> 
                                                            <span e-name="Path" e-form="addform" >
                                                                {{currentNode.path}}
                                                            </span>        
                                                        </td>
                    
                                                        <td>
                                                            {{ currentNode.title }} 
                                                        </td>
            
                                                        <td>
                                                            <span editable-text="index.VanityUrl" e-name="VanityUrl" e-form="addform" >
                                                            </span>        
                                                        </td>
                    
                                                         <td style="white-space: nowrap">
                                                            <!-- form -->          
            
                                                            <form editable-form name="addform" onaftersave="saveVanity(index.VanityUrl, currentNode.path, currentNode.site)" ng-show="addform.$visible" class="form-buttons form-inline" shown="inserted == index">
                                                              <button type="submit" ng-disabled="addform.$waiting" class="btn btn-primary">
                                                                save
                                                              </button>
                                                              <button type="button" ng-disabled="addform.$waiting" ng-click="addform.$cancel()" class="btn btn-default">
                                                                cancel
                                                              </button>
                                                            </form>
                                                            <div class="buttons" ng-show="!addform.$visible">
                                                              <button class="btn btn-primary" ng-click="addform.$show()">add new</button>
                                                            </div>             
                                                          </td>                 
                                                    </tr>                   
                                                </tbody>
                                            </table>            
                                        </div>
                                    </td>
                                 </tr>
                             </table>
							</uib-tab>
  					</uib-tabset>
                        </div>
                    </div>
                </div>
            <cq:includeClientLib js="vanity-url-manager.app"/>
        </div>
    </div>
</body>
</html>