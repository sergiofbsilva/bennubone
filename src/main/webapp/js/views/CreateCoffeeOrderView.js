window.CreateCoffeeOrderView = Backbone.View.extend({

    initialize: function () {
        this.render();
    },

    render: function () {
        var that = this;
        utils.loadTemplate(['CreateCoffeeOrderView'], function() {
            that.$el.html(that.template());
        });
        return this;
    }
});