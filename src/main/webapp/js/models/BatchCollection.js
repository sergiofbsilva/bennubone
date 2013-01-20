window.BatchCollection = Backbone.Collection.extend({

    model: BatchModel,

    url: "api/batches",
    
    parse: function(response){
        return response.batches;
    },
     
    comparator: function (collection) {
    	return collection.get('creationTimestamp');
    }

});