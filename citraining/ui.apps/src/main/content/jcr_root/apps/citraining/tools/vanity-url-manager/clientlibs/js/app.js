

angular.module('vanityUrlManager', ['ngResource', 'App.filters','ui.bootstrap', 'xeditable','angularTreeview'])
    .controller('VanCtrl', ['$scope', '$http', '$location', 
    function ($scope, $http, $location) {

        $scope.app = {
            resource: '',
            running: false
        };



        $scope.sites = [];
        $scope.selectedSite = [];

         $scope.treesites=[];

        var host = $location.host();
             $scope.host = host;

            var port = $location.port();
             $scope.port = port;


         $scope.treelist = function () {

			 $scope.notifications = [];
             if($scope.treesites.length == 0){
                  $scope.app.running = true;

                $http({
    
                    method: 'GET',
                    url: encodeURI('http://'+host+':'+port+'/services/siteslist'),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).
                    success(function (data, status, headers, config) {
    					$scope.app.running = false;
                        $scope.treesites=data;
    
                    }).
                    error(function (data, status, headers, config) {
                        $scope.addNotification('error', 'ERROR', 'Unable to get Sites');
                    });
             }

        };

         $scope.projects = function () {
             $scope.notifications = [];

            $http({

                method: 'GET',
                url: encodeURI('http://'+host+':'+port+'/content.2.json'),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).
                success(function (data, status, headers, config) {


                    angular.forEach(data, function (index, key, value) {
                        if (index['jcr:primaryType'] === 'cq:Page') {
                            $scope.sites.push(key);
                        }
                    });

                }).
            	error(function (data, status, headers, config) {
                    $scope.addNotification('error', 'ERROR', 'Unable to get Sites');
                });

        };


        $scope.list = function () {
            $scope.notifications = [];
            $scope.indexes = [];

            var host = $location.host();
             $scope.host = host;

            var port = $location.port();
             $scope.port = port;

            $http({

                method: 'GET',
                url: encodeURI('http://'+host+':'+port+'/services/vanityurlcheck'),
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).
                success(function (data, status, headers, config) {
                    angular.forEach(data, function (index, key) {
                        $scope.indexes.push(index);
                        
                    });

                }).
            	error(function (data, status, headers, config) {
                    $scope.addNotification('error', 'ERROR', 'Unable to get Vanity Paths');
                });

        };

             $scope.setSelectedSite = function () {
                var id = this.site;
                if (_.contains($scope.selectedSite, id)) {
                    $scope.selectedSite = _.without($scope.selectedSite, id);
                } else {
                    $scope.selectedSite.push(id);
                }
                return false;
            };
    
            $scope.isChecked = function (id) {
                if (_.contains($scope.selectedSite, id)) {
                    return 'icon-ok pull-right';
                }
                return false;
            };
        
            $scope.checkAll = function () {
                $scope.selectedSite = _.pluck($scope.sites, 'site');
            };



         $scope.init = function () {
              $scope.projects();
           // $scope.list();
           //  $scope.treelist();

        };



         $scope.saveVanity = function(data, id, site) {
             $scope.notifications = [];

            //angular.extend(data, {id: id});
             $scope.indexes.VanityUrl=data;
			var vanityUrl =  $scope.indexes.VanityUrl;
            $http({

                method: 'POST',
                url: encodeURI('http://'+host+':'+port+'/services/vanityurlcheck'),
                  data : 'op=update&path='+id+'&vanityurl='+vanityUrl+'&site='+site,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).
                    success(function (data, status, headers, config) {  

                        angular.forEach(data, function (index,key) {

                           if(angular.equals(key,"Error")){
                       			$scope.addNotification('error', 'Error', index);
                           }else{
								$scope.addNotification('success', 'SUCCESS', 'Saved Successfully !');
                           }

                    	});

    
                    }).
                	error(function (data, status, headers, config) {
                        $scope.addNotification('error', 'ERROR', 'Unable to Save');
                    });
          };
        
          // remove user
          $scope.removeVanity = function(index) {

              $scope.notifications = [];
              var data = $scope.indexes[index];
              $http({

                method: 'POST',
                url: encodeURI('http://'+host+':'+port+'/services/vanityurlcheck'),
                  data : 'op=delete&path='+data.Path,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).
                    success(function (data, status, headers, config) {  
                        $scope.indexes.splice(index, 1);
						$scope.addNotification('notice', 'WARNING', 'Its Gone!');
    
                    }).
                	error(function (data, status, headers, config) {
                        $scope.addNotification('error', 'ERROR', 'Unable to Delete');
                    });
          };


         $scope.addNotification = function (type, title, message) {

            $scope.notifications.push({
                type: type,
                title: title,
                message: message
            });


        };


    }]);


angular.module('App.filters', []).filter('siteFilter', [function () {
    return function (indexes, selectedSite) {
        if (!angular.isUndefined(indexes) && !angular.isUndefined(selectedSite) && selectedSite.length > 0) {
            var tempSites = [];
            angular.forEach(selectedSite, function (id) {
                angular.forEach(indexes, function (index) {
                    if (angular.equals(index.Site, id)) {
                        tempSites.push(index);
                    }
                });
            });
            return tempSites;
        } else {
            return indexes;
        }
    };
}]);



