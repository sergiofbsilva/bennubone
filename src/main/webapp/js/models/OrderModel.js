window.OrderModel = Backbone.Model.extend({
	
	urlRoot : 'api/orders' ,  
	
    initialize: function () {
    },

    defaults: {
        id: null,
        orderDate: "22-10-2012"
    }
});