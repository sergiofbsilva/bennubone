CoffeeManager.View.FooterView = Backbone.View.extend({
	
	el: $('#footer'),

    render: function () {
        CoffeeManager.Util.renderTemplate("FooterView", this.el);
		return this;	
    }

});