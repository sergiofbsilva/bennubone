CoffeeManager.View.CoffeeItemListView = Backbone.View.extend({
	
	el: $('#content'),

    render: function () {
        CoffeeManager.Util.renderTemplate("CoffeeItemListView", this.el, { items: this.collection.toJSON()});
        return this;
    }
});