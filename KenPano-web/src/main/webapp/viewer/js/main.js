requirejs.config({
	//别名
	paths: {
		backbone: 'libs/backbone',
		underscore: 'libs/underscore',
		jquery: 'libs/jquery',
		jqReel: 'libs/jquery.reel',
		template: '../template'
	}
});

require(['app'],function(App){
	// var app = new App();
	App.init();
})