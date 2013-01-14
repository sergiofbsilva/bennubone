var OrderView = Backbone.View.extend({
	initialize : function() {
		this.render();
	}, 
	
	render : function() {
		var that = this;
        utils.loadTemplate(['OrderView'], function() {
            that.$el.html(that.template(that.model.toJSON()));
        });
        return this;
	}
});