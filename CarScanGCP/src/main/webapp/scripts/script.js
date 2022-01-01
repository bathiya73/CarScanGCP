var app = angular.module('blog', [ 'ngRoute' ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when("/about", {
		templateUrl : "Templates/about.html"
	}).when("/edit", {
		templateUrl : "Templates/editEmp.html"
	}).when("/add", {
		templateUrl : "Templates/editEmp.html"
	})
});

app.factory("myFactory", function() {
	var saveData = {}
	function set(cust) {
		saveData = cust;
	}
	function get() {
		return saveData;
	}
	return {
		set : set,
		get : get
	}
})

app.controller('home', function($scope, $http, $location, myFactory) {
	
	$http.get('http://localhost:8080/getUsers').then(function(response) {
		$scope.Users = response.data;
	});				
	
	$scope.deleteUser = function(user) {
	console.log("user : ",user.key.id);
		var key = user.key.id;
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/deleteUser/' + key
		});
		$location.path('/about');
	};

	$scope.editUser = function(user) {
		console.log(user);
		myFactory.set(user);
		$location.path('/edit');
	};
});


app.controller("editCtrl", function ($scope, $location, myFactory, $http) {

    $scope.user = myFactory.get();
    
    
    $scope.insertdata = function() {
    
    if(!$scope.user.key){
    	console.log("in");
    	var userSubmit = {
  			ID : $scope.user.properties.ID,
			firstName : $scope.user.properties.firstName,
			lastName : $scope.user.properties.lastName,	
  			dob : $scope.user.properties.dob,
  			city : $scope.user.properties.city,
  			mobileNumber : $scope.user.properties.mobileNumber 
		}
    } else {
    	var userSubmit = {
 			Key : $scope.user.key.id,
  			ID : $scope.user.properties.ID,
			firstName : $scope.user.properties.firstName,
			lastName : $scope.user.properties.lastName,	
  			dob : $scope.user.properties.dob,
  			city : $scope.user.properties.city,
  			mobileNumber : $scope.user.properties.mobileNumber 
		}
    }
  		console.log("userSubmit : " ,userSubmit);
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/addEditUser',
			data : userSubmit,
			headers : {"Content-Type" : "application/json"
			}
		});
		myFactory.set(null);
		$location.path('/about');
	};
});

