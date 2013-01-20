var UserListView = Backbone.View.extend({

    initialize: function () {
       this.render();
    },

    render: function () {
        var that = this;
        utils.loadTemplate(['UserListView'], function() {
            that.$el.html(that.template());
        });
        
        var userCollection = new UserCollection();
        userCollection.fetch({ success: function() {
            var users = userCollection.models;
            var len = users.length;
            for (var i = 0; i < len; i++) {
                $('.users', this.el).append(new UserListItemView({model: users[i]}).render().el);
            }     
        }});
        return this;
    }
});