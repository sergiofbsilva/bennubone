CoffeeManager.View.CoffeeOrderView = Backbone.View.extend({

	el: $('#content'), 
	
	render: function() {
		CoffeeManager.Util.renderTemplate("CoffeeOrderView", this.el, this.model.toJSON());
        return this;
	}

});