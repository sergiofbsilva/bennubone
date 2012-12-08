window.HomeView = Backbone.View.extend({

    initialize: function () {
        this.render();
    },

    render: function () {
        var that = this;
        utils.loadTemplate(['HomeView'], function() {
            that.$el.html(that.template());
        });
        return this;
    }
});