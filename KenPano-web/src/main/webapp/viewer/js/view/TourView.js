define(['jquery','underscore','view/kenview','text!template/tour-template.html'], function($,_,Kenview,TourTemplate) {
    return Kenview.extend({

    	el: $("#tour_page"),

    	show: function() {
    		console.log('pagea show');
    		this.$el.show();
    		Kenview.prototype.show.call(this);
    	},

    	hide: function() {
    		console.log('pagea hide');
    		this.$el.hide();
    		Kenview.prototype.hide.call(this);
    	},

        render: function() {
        	console.log('page a render');
            this.$el.html(this.$el.html()+TourTemplate);
            // this.$el.addClass('animated slideInRight');
            Kenview.prototype.render.call(this);
        }
    });
})
