(function(){

    var app = angular.module('books', ['book-service', 'book-directives']);

    app.controller('BookController', [ '$http', '$scope', '$log', 'BookService',
    function($http, $scope, $log, BookService){

        $log.log('bookService:',BookService);
        $log.log('scope:',$scope);

        $scope.newBook = {};
        $scope.items = [];

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
            document.getElementById("new-book-form").reset();
            $scope.showNewBookForm();
        }

        $scope.modifyBook = function(book, item){
            BookService.modifyBook(book, item);
        }

        $scope.deleteBook = function(item){
            $log.log('deleting ', item);
            BookService.deleteBook(item, $scope.items);
        }

        $scope.showDetails = function($event){
            $log.log('inside showDetails()', $event);
            $log.log('closest book row: ', $($event.target).closest('.bookRow'))
            $($event.target).closest('.bookRow').find('.book-details').slideToggle();
        }

        $scope.showEdit = function($event){
            $log.log('inside showDetails()', $event);
            $($event.target).closest('.bookRow').find('.book-modify-form').slideToggle();
        }

        $scope.showNewBookForm = function($event){
            $log.log('inside showDetails()', $event);
            $('#new-book').slideToggle();
        }

        function loadData(response){
            $log.log(response);
            $scope.items = response.data._embedded.books;
        }
    }]);
})();