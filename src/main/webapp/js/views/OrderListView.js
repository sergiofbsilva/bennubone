var OrderListView = Backbone.View.extend({
	initialize : function() {
		this.render();
	},
	
	events: {
	    "click #show-all"		: 	"showAll",
	    "click #show-batched"	:   "showBatched",
	    "click #show-unbatched"	: 	"showUnbatched",
	    "click .show-order"		: 	"showOrder"
	  },
	
	render : function() {
		var that = this;
        utils.loadTemplate(['OrderListView'], function() {
            that.$el.html(that.template({orders : that.collection.toJSON()}));
        });
        return this;
	},
	
	showAll : function (e) {
		
	},
	
	showBatched : function (e) {
		
	},
	
	showUnbatched : function (e) {
		
	},
	
	showOrder : function (e) {
		console.log("show order" +"#orders/" + e.t);
		app.navigate("#orders/" + e.target.id, true);
	}
});