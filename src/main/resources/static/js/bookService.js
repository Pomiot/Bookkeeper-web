var bookServiceModule = angular.module('bookServiceModule', [])
.factory('BookService', function($http, $log){
    var serv = {};

    serv.addBook = function(book, items){
            $log.log('inside addBook()', book);
            $http.post('/books', book).then(function(){
            items.push(book);
        }, function(){
            $log.log("Fuckup during saving object")
        });
    }

    serv.modifyBook = function(book, item){
        $log.log('book:', book);
        $log.log('item:', item);

        $http.put(item._links.self.href, book).then(function(data){
            $log.log('data:', data);
            angular.copy(book, item);
        });
    }

    return serv;
})