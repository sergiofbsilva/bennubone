CoffeeManager.Collection.CoffeeBatchCollection = Backbone.Collection.extend({

    url: "api/batches",

    model: CoffeeManager.Model.CoffeeBatchModel,
    
    parse: function(response) {
        return response.batches;
    },
     
    comparator: function (collection) {
    	return collection.get('creationTimestamp');
    }

});