(function(){
    var app = angular.module('book-directives', []);

    app.directive('booksControl', function(){
        return {
            restrict: 'E',
            templateUrl: '/fragments/books-control.html'
        };
    });

    app.directive('bookBasicInfo', function(){
        return {
            restrict: 'E',
            templateUrl: '/fragments/book-basic-info.html'
        };
    });

    app.directive('bookAdditionalInfo', function(){
        return {
            restrict: 'E',
            templateUrl: '/fragments/book-additional-info.html'
        };
    });

    app.directive('bookAddForm', function(){
        return {
            restrict: 'E',
            templateUrl: '/fragments/book-add-form.html',
            scope: true
        };
    });

    app.directive('bookModifyForm', function(){
        return {
            restrict: 'E',
            templateUrl: '/fragments/book-modify-form.html',
            scope: true
        };
    });
})();