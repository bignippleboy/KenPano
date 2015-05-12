define(['jquery', 'underscore', 'backbone', 'router', 'view/PageaView', 'view/PagebView', 'view/TourView', 'view/reelview'], function($, _, Backbone, Router, PageaView, PagebView, TourView, ReelView) {

    var App = {
        text: 'abc',

        viewsMap: {},//key-path;value-view

        AppRouter: Backbone.Router.extend({
            routes: {
                // 'page-a': 'showPageA',
                // 'page-b': 'showPageB',
                //default
                // '*actions': 'defaultAction',
                '*path(?*params)': 'any',
                'a':'absdfc'
            },

            any: function(path, params) {
                console.log('route: %s ? ', path, params);
                console.log("what:"+App.text+":"+this.text);
                App.showView(path, params);
            }
            // showPageA: function() {
            //     var pageaView = new PageaView();
            //     pageaView.render();
            // }
        }),

        init: function() {
            console.log('app init:'+this.text+":"+App.text);
            //初始化所有views
            var pageaView = new PageaView();
            var pagebView = new PagebView();
            var tourView = new TourView();
            var reelView = new ReelView();
            this.addView('page-a',pageaView);
            this.addView('page-b',pagebView);
            this.addView('tour_page',tourView);
            this.addView('reel_page', reelView);
            //render all
            this.renderAndHideAll();
            //router
            var app_router = new this.AppRouter;
            // app_router.on('route:showPageA', function() {
            //     var pageaView = new PageaView();
            //     pageaView.render();
            // });

            // app_router.on('route:showPageB', function() {
            //     var pagebView = new PagebView();
            //     pagebView.render();
            // });

            // app_router.on('route:defaultAction', function(actions) {

            //     var pageaView = new PageaView();
            //     pageaView.render();

            // });

            Backbone.history.start();
        },

        addView: function(path, view) {
            this.viewsMap[path] = view;
        },

        renderAndHideAll: function() {
            for(key in this.viewsMap) {
                this.viewsMap[key].render();
                this.viewsMap[key].hide();
            }
        },

        showView: function(path, params) {
            if(!path)
                this.viewsMap['page-a'].show();
            var activeView = this.viewsMap[path];
            if(activeView) {
                // $(this.viewsMap).each(function() {
                //     this.hide();
                // });
                console.log("viewsMap`s type:"+typeof(this.viewsMap));
                // this.viewsMap.forEach(function(view){
                //     view.hide();
                // });
                for(key in this.viewsMap) {
                    // console.log(key+"="+this.viewsMap[key]);
                    this.viewsMap[key].hide();
                }
                activeView.show();
            }
        }

    };

    return App;
});
