(function(){

    var app = angular.module('books', []);

    app.controller('BookController', function(){
        this.items = book;
    });

    var book = [{
            title : 'Sample title 1',
            author : 'Sample author 1',
            rating : 5,
            lent : false
        },
        {
            title : 'Sample title 2',
            author : 'Sample author 1',
            rating : 3.5,
            lent : true
        },
        {
            title : 'Sample title 3',
            author : 'Sample author 2',
            rating : 4,
            lent : false
        },
        {
            title : 'Sample title 4',
            author : 'Sample author 3',
            rating : 1,
            lent : false
        }]
})();