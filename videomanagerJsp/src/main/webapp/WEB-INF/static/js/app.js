/**
 * 
 */


var videoApp = angular.module('videoApp', []).config( [
    '$compileProvider',
    function( $compileProvider )
    {   
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|chrome-extension|localexplorer):/);
        // Angular before v1.2 uses $compileProvider.urlSanitizationWhitelist(...)
    }
]);

videoApp.controller('BodyController', function($scope) {
	

});

