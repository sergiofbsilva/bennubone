window.UserCollection = Backbone.Collection.extend({

    model: UserModel,

    url: "api/user"

});