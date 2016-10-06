(function(){

    var app = angular.module('books', []);

    app.controller('BookController', [ '$http', '$scope', '$log', function($http, $scope, $log){

        $scope.items = [];
        $scope.next20 = "";
        $scope.prev20 = "";

        $log.log('initial load of data');
        $http.get('/books').then(loadData);

        $scope.reloadData = function(){
            $http.get('/books').then(loadData);
        }

        $scope.addBook = function(book){
            $log.log('inside addBook()');
            $log.log(book);
            $http.post('/books', book).then(function(){
                $scope.items.push(book);
            }, function(){
            $log.log("Fuckup during saving object")});
        }

        $scope.nextPage = function(){
            $log.log('inside nextPage()');
            $http.get($scope.next20).then(loadData);
        }

        $scope.prevPage = function(){
            $log.log('inside prevPage()');
            $http.get($scope.prev20).then(loadData);
        }

        $scope.showDetails = function($event){
            $log.log('inside showDetails()');
            $log.log($event);
            $($event.target).closest('.bookRow').find('.book-details').slideToggle();
        }

        $scope.showEdit = function($event){
            $log.log('inside showDetails()');
            $log.log($event);
            $($event.target).closest('.bookRow').find('.book-modify-form').slideToggle();
        }

        $scope.showNewBookForm = function($event){
            $log.log('inside showDetails()');
            $log.log($event);
            $('#new-book-form').slideToggle();
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