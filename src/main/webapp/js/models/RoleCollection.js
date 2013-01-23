CoffeeManager.Collection.RoleCollection = Backbone.Collection.extend({

    url: "api/roles",

    model: CoffeeManager.Model.RoleModel,
    
    parse: function(response) {
        return response.roles;
    }
});