CoffeeManager.View.CoffeeBatchView = Backbone.View.extend({

	el: $('#content'), 
	
	render: function() {
		CoffeeManager.Util.renderTemplate("CoffeeBatchView", this.el, this.model.toJSON());
        return this;
	}

});