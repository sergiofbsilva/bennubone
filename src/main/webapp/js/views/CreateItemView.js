var CreateItemView = Backbone.View.extend({

    initialize: function () {
        this.render();
    },
    
    events : {
    	"click #create-item" : "createItem"
    }, 

    render: function () {
        var that = this;
        utils.loadTemplate(['CreateItemView'], function() {
            that.$el.html(that.template());
        });
        return this;
    }, 
    
    createItem : function (e) {
    	e.preventDefault();
    	var target = e.target;
    	var name = $('input[name=name]', this.el).val();
    	var imageUrl = $('input[name=imageUrl]', this.el).val();
    	var unitValue = $('input[name=unitValue]', this.el).val();
    	var units = $('input[name=units]', this.el).val();
    	var itemModel = new ItemModel( { name :  name , imageUrl : imageUrl , unitValue : unitValue, units : units});
    	itemModel.save(null,{ success : function () {
    		app.navigate("items", true);
    	} });
    }
});