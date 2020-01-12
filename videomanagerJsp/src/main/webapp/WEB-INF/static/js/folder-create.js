

videoApp.controller('FolderController', function($scope, $http) {
	$scope.folder = {};
	
	
	$("#main-form").submit(function( event ) {
		
		var form = $("#main-form")[0];
		if (form.checkValidity() === false) {
			alert( "Handler for .submit() called." );
        	event.preventDefault();
        	event.stopPropagation();
        }
        form.classList.add('was-validated');
		  
	});
	
	
	$scope.getFolderFiles = function(folderId){
		
	}
	
	$scope.reloadFiles = function(folderId){
		
		$scope.files[folderId] = [];
		
		$http({
		    method : "GET",
		    url : "/folders/listfiles/" + folderId
		  }).then(function mySuccess(response) {
			  $scope.files[folderId] = response.data;
		    }, function myError(response) {
		      alert("Error: " + response.statusText);
		  });
		
	}
	


});

