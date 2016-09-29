(function(){

    var app = angular.module('books', []);

    app.controller('BookController', [ '$http', '$scope', '$log', function($http, $scope, $log){

        $scope.items = [];
        $scope.next20 = "";
        $scope.prev20 = "";

        $log.log('initial load of data');
        $http.get('/books').then(loadData);

        $scope.nextPage = function(){
            $log.log('inside nextPage()');
            $http.get($scope.next20).then(loadData);
        }

        $scope.prevPage = function(){
            $log.log('inside prevPage()');
            $http.get($scope.prev20).then(loadData);
        }

        function loadData(response){
            $log.log(response);
            $scope.items = response.data._embedded.books;
            if(response.data._links.next !== undefined){
                $scope.next20 = response.data._links.next.href;
            }
            if(response.data._links.prev !== undefined){
                $scope.prev20 = response.data._links.prev.href;
            }
        }
    }]);
})();