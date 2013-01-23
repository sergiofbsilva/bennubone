CoffeeManager.Collection.CoffeeItemCollection = Backbone.Collection.extend({

    url: "api/items",

    model: CoffeeManager.Model.CoffeeItemModel,
    
    parse: function(response){
        return response.items;
     },
     
     comparator: function (collection) {
    	 return collection.get('name');
     }

});