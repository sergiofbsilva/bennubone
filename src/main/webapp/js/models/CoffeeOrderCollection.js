CoffeeManager.Collection.CoffeeOrderCollection = Backbone.Collection.extend({

    url: "api/orders",

    model: CoffeeManager.Model.CoffeeOrderModel,
    
    parse: function(response) {
    	return response.orders;
    }

});