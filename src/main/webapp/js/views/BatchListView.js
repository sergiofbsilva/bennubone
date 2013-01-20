var BatchListView = Backbone.View.extend({

	initialize : function() {
		this.render();
	},
	
	events: {
	    "click #show-all"		: 	"showAll",
	    "click #show-sent"		:   "showSent",
	    "click #show-not-sent"	: 	"showNotSent",
	    "click .show-batch"		: 	"showBatch"
	},

	render : function() {
		var that = this;
        utils.loadTemplate(['BatchListView'], function() {
            that.$el.html(that.template({ batches : that.collection.toJSON()}));
        });
        return this;
	},
	
	showAll: function(e) {
		e.preventDefault();
		console.log(this.collection.toJSON());
		this.$el.html(this.template({batches : this.collection.toJSON()}));
		this.selectPill("#show-all");
	},
	
	showSent: function(e) {
		e.preventDefault();
		var result = this.collection.where({sent: true});
		console.log(result);
    	this.$el.html(this.template({batches : new BatchCollection(result).toJSON()}));
    	this.selectPill("#show-sent");
	},
	
	showNotSent: function(e) {
		e.preventDefault();
		var result = this.collection.where({sent: false});
		console.log(result);
    	this.$el.html(this.template({batches : new BatchCollection(result).toJSON()}));
	   	this.selectPill("#show-not-sent");
	},
	
	showBatch: function(e) {
		app.navigate("#batches/" + e.target.id, true);
	},
	
	selectPill: function(pillId) {
		this.parseTimestamps();
		$(".batch-filter-pill").removeClass("active");
		$(pillId).parent().addClass("active");
	},
	
	parseTimestamps: function() {
		$('.time').each(function() {
			$(this).text(moment($(this).text()).fromNow());
		});
		console.log("I've been called");
	}
	
});