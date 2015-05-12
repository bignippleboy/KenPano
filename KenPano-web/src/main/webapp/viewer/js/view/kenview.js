define(['jquery','underscore','backbone'], function($,_,Backbone){
	return Backbone.View.extend({
		el:null,
		render: function(){
			console.log('kenview render');
		},

		show: function(){
			console.log('kenview show:');
		},

		hide: function(){
			console.log('kenview hide');
		}
	});
})