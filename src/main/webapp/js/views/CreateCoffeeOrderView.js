CoffeeManager.View.CreateCoffeeOrderView = Backbone.View.extend({

	el : $('#content'),

	render : function() {
		CoffeeManager.Util.renderTemplate("CreateCoffeeOrderView", this.el, {
			items : this.collection.toJSON()
		});
		return this;
	},

	events : {
		"click #create-coffee-order" : "createCoffeeOrder"
	},

	createCoffeeOrder : function(event) {
		event.preventDefault();
		order = new Array();
		$('input[name^=quantity]', this.el).filter(function(index, element) {
			return $(element).val();
		}).each(function(index, element) {
			var id = $(element).attr("id");
			var quantity = parseInt($(element).val());
			order.push({
				id : id,
				quantity : quantity
			});
		});
		var coffeeOrderModel = new CoffeeManager.Model.CoffeeOrderModel({
			entries : order
		});
		coffeeOrderModel.save(null, {
			success : function() {
				CoffeeManager.Application.navigate("orders", true);
			}
		});
		// $('input[name^=quantity]', this.el).each(function(index, element) {
		// if($(element).val()) {
		// console.log($(element).val() +" of "+ $(element).attr("id"));
		// }
		// });
	}
}); 