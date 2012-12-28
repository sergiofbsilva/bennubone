window.OrderListView = Backbone.View.extend({

    initialize: function () {
       this.render();
    },

    render: function () {
        var that = this;
        utils.loadTemplate(['OrderListView'], function() {
            that.$el.html(that.template());
        });
        
        var orderCollection = new OrderCollection();
        orderCollection.fetch({ success: function() {
            var orders = orderCollection.models;
            var len = orders.length;
            for (var i = 0; i < len; i++) {
                $('.orders', this.el).append(new OrderListItemView({model: orders[i]}).render().el);
            }     
        }});
        return this;
    }
});