window.OrderEntryCollection = Backbone.Collection.extend({

    model: OrderEntry,

    parse: function(response) {
    	return response.entries;
    }

});