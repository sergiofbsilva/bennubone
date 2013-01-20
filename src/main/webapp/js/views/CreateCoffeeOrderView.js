var CreateCoffeeOrderView = Backbone.View.extend({

    initialize: function () {
        this.render();
    },
    
    events : {
    	"click #add-new-order-entry": "addNewOrderEntry"
    
    },

    render: function () {
        var that = this;
        this.itemCollection = new ItemCollection();
        this.itemCollection.fetch({ success: function() {
        	utils.loadTemplate(['CreateCoffeeOrderView'], function() {
            	that.$el.html(that.template());
            	that.id = 0;
        	});
        }});
        return this;
    },
    
    addNewOrderEntry: function() {
    	this.id = this.id+1;
    	$('#coffee-order-container').append(new CoffeeOrderEntryConfigView({ id: this.id, collection: this.itemCollection }).el);
    }
    
});