define(['jquery','underscore','view/kenview','text!template/pageb-template.html'], function($,_,Kenview,template_b) {
    return Kenview.extend({

    	el: $("#page-b"),

    	show: function() {
    		console.log('pageb show');
    		this.$el.show();
    		Kenview.prototype.show.call(this);
    	},

    	hide: function() {
    		console.log('pageb hide');
    		this.$el.hide();
    		Kenview.prototype.hide.call(this);
    	},

        render: function() {
        	console.log('page a render');
            this.$el.html(this.$el.html()+template_b);
            Kenview.prototype.render.call(this);
        }
    });
})
