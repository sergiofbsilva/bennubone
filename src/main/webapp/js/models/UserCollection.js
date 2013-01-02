window.UserCollection = Backbone.Collection.extend({

    model: UserModel,

    url: "api/users",
    
    parse: function(response) {
    	return response.users;
    }

});