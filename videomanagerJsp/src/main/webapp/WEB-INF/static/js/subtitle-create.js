

videoApp.controller('SubtitleController', function($scope, $http) {
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
	
	
	
	


});

