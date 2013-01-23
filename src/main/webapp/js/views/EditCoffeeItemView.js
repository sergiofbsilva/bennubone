CoffeeManager.View.EditCoffeeItemView = Backbone.View.extend({
	
	el: $('#content'),
	
    events : {
    	"click #update-coffee-item" : "updateCoffeeItem"
    }, 

    render: function () {
        CoffeeManager.Util.renderTemplate("EditCoffeeItemView", this.el, this.model.toJSON());
        return this;
    }, 
    
    updateCoffeeItem : function (e) {
    	e.preventDefault();
    	var target = e.target;
    	var name = $('input[name=name]', this.el).val();
    	var imageUrl = $('input[name=imageUrl]', this.el).val();
    	var unitPrice = $('input[name=unitPrice]', this.el).val();
    	var numUnits = $('input[name=numUnits]', this.el).val();
    	var coffeeItemModel = new CoffeeManager.Model.CoffeeItemModel( { name :  name , imageUrl : imageUrl , unitPrice : unitPrice, numUnits : numUnits});
    	coffeeItemModel.save(null,{ success : function () {
    		app.navigate("items", true);
    	}});
    }
});