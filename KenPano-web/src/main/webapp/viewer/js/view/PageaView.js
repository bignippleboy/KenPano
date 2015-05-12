define(['jquery','underscore','view/kenview','text!template/pagea-template.html'], function($,_,Kenview,template_a) {
    return Kenview.extend({

    	el: $("#page-a"),

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
            this.$el.html(this.$el.html()+template_a);
            // this.$el.addClass('animated slideInRight');
            Kenview.prototype.render.call(this);
        }
    });
})
