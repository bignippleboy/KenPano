define(['jquery', 'underscore', 'backbone', 'view/PageaView', 'view/PagebView'], function($, _, Backbone, PageaView, PagebView) {

    var AppRouter = Backbone.Router.extend({
        routes: {
            'page-a': 'showPageA',
            'page-b': 'showPageB',
            //default
            '*actions': 'defaultAction'
        }
        // showPageA: function() {
        //     var pageaView = new PageaView();
        //     pageaView.render();
        // }
    });

    var init = function() {
        var app_router = new AppRouter;
        app_router.on('route:showPageA', function() {
            var pageaView = new PageaView();
            pageaView.render();
            var pagebView = new PagebView();
            pagebView.remove();
        });

        app_router.on('route:showPageB', function() {
            var pagebView = new PagebView();
            pagebView.render();
            var pageaView = new PageaView();
            pageaView.remove();
        });

        app_router.on('route:defaultAction', function(actions) {

            var pageaView = new PageaView();
            pageaView.render();
            var pagebView = new PagebView();
            pagebView.remove();
        });

        Backbone.history.start();
    };

    return {
        init: init
    };
});
