var CoffeeOrderEntryConfigView = Backbone.View.extend({

    initialize: function () {
        this.render();
        this.id = 0;
    },
    
    events : {
    	"click .remove-order-entry": "removeCoffeeOrder"
    },

    render: function () {
        var that = this;
      	utils.loadTemplate(['CoffeeOrderEntryConfigView'], function() {
	    	that.$el.html(that.template({ id: that.id, items: that.collection.toJSON()}));
	    });
        return this;
    },
    
    removeCoffeeOrder: function(e) {
    	var target = e.target;
    	$(target).parent().remove();
    }
    
});