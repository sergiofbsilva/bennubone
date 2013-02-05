CoffeeManager.View.HeaderView = Backbone.View.extend({

	el: $('#header'),

    render: function () {
    	CoffeeManager.Util.renderTemplate("HeaderView", this.el, this.model.toJSON());
    	return this;
    },

    selectMenuItem: function (menuItem) {
        $('.nav li').removeClass('active');
        if (menuItem) {
            $('.' + menuItem).addClass('active');
        }
    }

});