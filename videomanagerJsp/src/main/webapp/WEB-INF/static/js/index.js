/**
 * 
 */


videoApp.controller('IndexController', function($scope, $http) {
	$scope.files = [];
	$scope.addSubFile = {};
	$scope.addSubLang = "";
	
	$scope.processlist = [];
	$scope.filter = "";
	
	
	
	
	$scope.stompClient = null;
	$scope.connected = false;

	function setConnected(isConnected) {
		$scope.connected = isConnected;
		$scope.$apply();
		//alert($scope.connected);
	}

	$scope.connect = function() {
	    var socket = new SockJS('/videoserver-guide-websocket');
	    $scope.stompClient = Stomp.over(socket);
	    $scope.stompClient.connect({}, function (frame) {
	        setConnected(true);
	        console.log('Connected: ' + frame);
	        $scope.stompClient.subscribe('/socket/messages', function (message) {
	            processMessage(JSON.parse(message.body));
	        });
	    });
	}

	function disconnect() {
	    if ($scope.stompClient !== null) {
	        $scope.stompClient.disconnect();
	    }
	    setConnected(false);
	    console.log("Disconnected");
	}

	function sendName() {
	    $scope.stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
	}

	function processMessage(message) {
	    if(message.status === 'ok'){
	    	
	    	if(message.command === 'add' && message.info){
	    		$scope.processlist.push(message.info);
	    		$('#process-tab').tab('show');
	    		//$('#filestab').tab('hide');
		    }
	    	
	    	if(message.command === 'delete' && message.hash){	
	    		//alert(message.hash);
	    		//$scope.processlist = [];
	    		
	    		$scope.processlist = $scope.processlist.filter(function(process){
	    			return process.hash != message.hash;
	    		});
	    			
		    }
	    	
	    	if(message.command === 'status' && message.hash && message.info){	
	    		//alert(message.hash);
	    		//$scope.processlist = [];
	    		
	    		for(index in $scope.processlist){
	    			if($scope.processlist[index].hash == message.hash){
	    				$scope.processlist[index] = message.info;
	    			}
	    		}
	    		
	    			
		    }
	    }
	    
	    $scope.$apply();
	}
	
	$scope.getConnectedStatus = function(){
		return $scope.connected ? 'Connected ...' : 'Not Connected!';
	}
	
	
	
	
	
	
	
	
	$scope.getFolderFiles = function(folderId){
		var files = [];
		if(folderId in $scope.files){
			files = $scope.files[folderId];
		}
		
		if($scope.filter !== ''){
			var temp = files;
			
			files = temp.filter(function(file){
    			return file.name.toLowerCase().indexOf($scope.filter.toLowerCase()) > -1;
    		});
		}
		
		return files;
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
		
		var data = {"language": $scope.addSubLang, "file": $scope.addSubFile.base64Path, "command": "status"};
		
		$http.post('/process/add', data).then(function(data){
			//alert("add");
		}, function(error){
			alert("error");
		});
		
		$("#addSubtitleModal").modal('hide');
		
	}
	
	
	$scope.startProcess = function(hash){
		$scope.stompClient.send("/process/action", {}, JSON.stringify({'status': "ok", "command" :"start", "hash": hash }));
		
	}
	
	$scope.stopProcess = function(hash){
		$scope.stompClient.send("/process/action", {}, JSON.stringify({'status': "ok", "command" :"stop", "hash": hash }));
		
		
		
	}
	
	$scope.deleteProcess = function(hash){
		$scope.stompClient.send("/process/action", {}, JSON.stringify({'status': "ok", "command" :"delete", "hash": hash }));
		
		
		
	}

	
	$scope.connect();
});

