var bookServiceModule = angular.module('book-service', [])
.factory('BookService', function($http, $log){
    var serv = {};

    serv.addBook = function(book, items){
            $log.log('inside addBook()', book);
            $http.post('/books', book).then(function(response){
            items.push(angular.copy(response.data));
        }, function(){
            $log.log("Fuckup during saving object")
        });
    }

    serv.modifyBook = function(book, item){
        $log.log('book:', book);
        $log.log('item:', item);

        $http.put(item._links.self.href, book).then(function(response){
            $log.log('data:', response);
            angular.copy(response.data, item);
        });
    }

    serv.deleteBook = function(book, items){
        $http.delete(book._links.self.href).then(function(){
            var index = items.indexOf(book);
            items.splice(index, 1);
        })
    }

    return serv;
})