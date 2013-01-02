window.OrderCollection = Backbone.Collection.extend({

    model: OrderModel,

    url: "api/orders",
    
    parse: function(response) {
    	return response.orders;
    }

});