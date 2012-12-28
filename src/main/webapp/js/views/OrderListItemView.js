window.OrderListItemView = Backbone.View.extend({

    tagName: "tr",

    initialize: function () {
        this.render();
    },

    render: function () {
        var that = this;
        console.log(that.model.toJSON());
        utils.loadTemplate(['OrderListItemView'], function() {
            that.$el.html(that.template(that.model.toJSON()));
        });
        
        return this;
    }

});