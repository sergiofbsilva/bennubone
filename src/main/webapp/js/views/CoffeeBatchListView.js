CoffeeManager.View.CoffeeBatchListView = Backbone.View.extend({

	el: $('#content'),
	
	events: {
	    "click #show-all-batches"			: 	"showAll",
	    "click #show-only-sent-batches"		:   "showSent",
	    "click #show-only-batches-not-sent"	: 	"showNotSent",
	    "click #show-only-received-batches"	: 	"showReceived",
	    "click .show-batch"					: 	"showBatch"
	},

	render : function() {
		this.showAll();
        return this;
	},
	
	showAll: function(e) {
		if(e)
			e.preventDefault();
		var that = this;
		CoffeeManager.Util.renderTemplate("CoffeeBatchListView", this.el, { batches: this.collection.toJSON() }, function() {
			CoffeeManager.Util.parseTimestamps();
			that.selectPill("#show-all-batches");
		});
	},
		
	showReceived: function(e) {
		e.preventDefault();
		var that = this;
		var result = this.collection.filter(function(batch) { if(batch.get("receivedTimestamp")) return true; });
		
		CoffeeManager.Util.renderTemplate("CoffeeBatchListView", this.el, { batches : new CoffeeManager.Collection.CoffeeBatchCollection(result).toJSON()}, function() {
			CoffeeManager.Util.parseTimestamps();
			that.selectPill("#show-only-received-batches");
		});
	},
	
	showSent: function(e) {
		e.preventDefault();
		var that = this;
		var result = this.collection.filter(function(batch) { if(batch.get("sentTimestamp") && batch.get("receivedTimestamp") === null) return true; });
		CoffeeManager.Util.renderTemplate("CoffeeBatchListView", this.el, { batches : new CoffeeManager.Collection.CoffeeBatchCollection(result).toJSON()}, function() {
			CoffeeManager.Util.parseTimestamps();
			that.selectPill("#show-only-sent-batches");
		})
	},
	
	showNotSent: function(e) {
		e.preventDefault();
		var that = this;
		var result = this.collection.filter(function(batch) { if(batch.get("sentTimestamp") === null) return true; });
		CoffeeManager.Util.renderTemplate("CoffeeBatchListView", this.el, { batches : new CoffeeManager.Collection.CoffeeBatchCollection(result).toJSON()}, function() {
			CoffeeManager.Util.parseTimestamps();
			that.selectPill("#show-only-batches-not-sent");
		});
	},
	
	showBatch: function(e) {
		CoffeeManager.Application.navigate("#batches/" + e.target.id, true);
	},
	
	selectPill: function(pillId) {
		$(".batch-filter-pill").removeClass("active");
		$(pillId).parent().addClass("active");
	}
	
});