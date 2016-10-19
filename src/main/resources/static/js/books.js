(function(){

    var app = angular.module('books', ['bookServiceModule']);

    app.controller('BookController', [ '$http', '$scope', '$log', 'BookService',
     function($http, $scope, $log, BookService){

        $log.log('bookService:',BookService);
        $log.log('scope:',$scope);

        $scope.newBook = {};

        $scope.items = [];
        $scope.next20 = "";
        $scope.prev20 = "";

        $log.log('initial load of data');
        $http.get('/books').then(loadData);

        $scope.getUserDetails = function(){
            $http.get('/user').then(function(data){
                $log.log('user details: ', data);
            })
        }

        $scope.reloadData = function(){
            $http.get('/books').then(loadData);
        }

        $scope.addBook = function(book){
            BookService.addBook(book, $scope.items);
        }

        $scope.modifyBook = function(book, item){
            BookService.modifyBook(book, item);
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
            $log.log('inside showDetails()', $event);
            $($event.target).closest('.bookRow').find('.book-details').slideToggle();
        }

        $scope.showEdit = function($event){
            $log.log('inside showDetails()', $event);
            $($event.target).closest('.bookRow').find('.book-modify-form').slideToggle();
        }

        $scope.showNewBookForm = function($event){
            $log.log('inside showDetails()', $event);
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