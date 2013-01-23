CoffeeManager.View.HomeView = Backbone.View.extend({

    el: $('#content'),

    render: function () {
        CoffeeManager.Util.renderTemplate("HomeView", this.el);
        return this;
    }
});