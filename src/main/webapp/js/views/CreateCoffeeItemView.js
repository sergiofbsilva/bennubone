CoffeeManager.View.CreateCoffeeItemView = Backbone.View.extend({
	
	el: $('#content'),
	
    events : {
    	"click #create-item" : "createItem"
    }, 

    render: function () {
        CoffeeManager.Util.renderTemplate("CreateItemView", this.el);
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