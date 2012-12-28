window.ItemListView = Backbone.View.extend({

    initialize: function () {
       this.render();
    },

    render: function () {
        var that = this;
        utils.loadTemplate(['ItemListView'], function() {
            that.$el.html(that.template());
        });
        
        var itemCollection = new ItemCollection();
        itemCollection.fetch({ success: function() {
            var items = itemCollection.models;
            console.log(items);
            var len = items.length;
            for (var i = 0; i < len; i++) {
                $('.items', this.el).append(new ItemListItemView({model: items[i]}).render().el);
            }     
        }});
        return this;
    }
});