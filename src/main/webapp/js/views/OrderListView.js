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
		e.preventDefault();
		console.log(this.collection.toJSON());
		this.$el.html(this.template({orders : this.collection.toJSON()}));
		this.selectPill("#show-all");
	},
	
	showBatched : function (e) {
		e.preventDefault();
		var result = this.collection.where({batched: true});
		console.log(result);
    	this.$el.html(this.template({orders : new OrderCollection(result).toJSON()}));
    	this.selectPill("#show-batched");
	},
	
	showUnbatched : function (e) {
		e.preventDefault();
		var result = this.collection.where({batched: false});
		console.log(result);
    	this.$el.html(this.template({orders : new OrderCollection(result).toJSON()}));
	   	this.selectPill("#show-unbatched");
	},
	
	showOrder : function (e) {
		console.log("show order" +"#orders/" + e.t);
		app.navigate("#orders/" + e.target.id, true);
	},
	
	selectPill: function(pillId) {
		$(".order-filter-pill").removeClass("active");
		$(pillId).parent().addClass("active");
	}
	
});