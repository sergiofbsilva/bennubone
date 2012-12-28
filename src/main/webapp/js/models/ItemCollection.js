window.ItemCollection = Backbone.Collection.extend({

    model: ItemModel,

    url: "api/items",
    
    parse: function(response){
        return response.items;
     },
     
     comparator: function (collection) {
    	 return collection.get('name');
     }

});