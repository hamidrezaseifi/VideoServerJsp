/**
 * 
 */


videoApp.controller('IndexController', function($scope, $http) {
	$scope.files = [];
	$scope.addSubFile = {};
	$scope.addSubLang = "";
	
	
	$scope.getFolderFiles = function(folderId){
		if(folderId in $scope.files){
			return $scope.files[folderId];
		}
		
		return [];
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
	
	//https://subscene.com/subtitles/searchbytitle
	
	$scope.searchSub = function(fileName){
		
		$("#search-sub-form #query").val(fileName);
		$("#search-sub-form").submit();
		
	}
	
	$scope.showAddSubModal = function(file){
		
		$scope.addSubFile = file;
		$scope.addSubLang = "per";
		
		$("#addSubtitleModal").modal();
		
	}
	
	$scope.addSub = function(){
		
		var data = {"language": $scope.addSubLang, "file": $scope.addSubFile.outputFilePathHash, "command": "status"};
		
		$http.post('/process/add', data).then(function(data){
			alert("add");
		}, function(error){
			alert("error");
		});
		
		$("#addSubtitleModal").modal('hide');
		
	}
	
	
	
});

