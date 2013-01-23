CoffeeManager.View.CoffeeOrderListView = Backbone.View.extend({

	el : $('#content'),

	events : {
		"click #show-all" : "showAll",
		"click #show-batched" : "showBatched",
		"click #show-unbatched" : "showUnbatched",
		"click #show-dispatched" : "showDispatched",
		"click .show-order" : "showOrder",
		"click .edit-order" : "editOrder",
		"click .clone-order" : "cloneOrder"
	},

	render : function() {
		this.showAll();
		return this;
	},

	showAll : function(e) {
		if (e)
			e.preventDefault();
		var that = this;
		CoffeeManager.Util.renderTemplate("CoffeeOrderListView", this.el, {
			orders : this.collection.toJSON()
		}, function() {
			that.selectPill("#show-all");
		});
	},

	showBatched : function(e) {
		e.preventDefault();
		var result = this.collection.where({
			batched : true,
			sent: false
		});
		var that = this;
		CoffeeManager.Util.renderTemplate("CoffeeOrderListView", this.el, {
			orders : new CoffeeManager.Collection.CoffeeBatchCollection(result).toJSON()
		}, function() {
			that.selectPill("#show-batched");
		});
	},

	showUnbatched : function(e) {
		e.preventDefault();
		var result = this.collection.where({
			batched : false
		});
		var that = this;
		CoffeeManager.Util.renderTemplate("CoffeeOrderListView", this.el, {
			orders : new CoffeeManager.Collection.CoffeeBatchCollection(result).toJSON()
		}, function() {
			that.selectPill("#show-unbatched");
		});
	},

	showDispatched : function(e) {
		e.preventDefault();
		var result = this.collection.where({
			sent : true
		});
		var that = this;
		CoffeeManager.Util.renderTemplate("CoffeeOrderListView", this.el, {
			orders : new CoffeeManager.Collection.CoffeeBatchCollection(result).toJSON()
		}, function() {
			that.selectPill("#show-dispatched");
		});
	},

	showOrder : function(e) {
		e.preventDefault();
		CoffeeManager.Application.navigate("#orders/" + e.target.id.replace("view-",""), true);
	},
	
	editOrder: function(e) {
		e.preventDefault();
		CoffeeManager.Application.navigate("#orders/edit/" + e.target.id.replace("edit-",""), true);
	},
	
	cloneOrder: function(e) {
		console.log("Cloning");
		e.preventDefault();
		var that = this;
		var entriesJson = this.collection.get(e.target.id.replace("clone-","")).toJSON().entries;
		clonedEntries = [];
		$.each(entriesJson, function() {
			clonedEntries.push({id: this.item.id, quantity: this.quantity });
		});
		var clonedCoffeeOrderModel = new CoffeeManager.Model.CoffeeOrderModel({ entries: clonedEntries });
		clonedCoffeeOrderModel.save(null, {
			success : function() {
				that.collection.fetch({ success: function() {
					that.undelegateEvents();
					CoffeeManager.Application.navigate("orders/"+clonedCoffeeOrderModel.get("id"), true);
				}});
			}
		});
	},

	selectPill : function(pillId) {
		$(".order-filter-pill").removeClass("active");
		$(pillId).parent().addClass("active");
	}
}); 