CoffeeManager.View.EditCoffeeOrderView = Backbone.View.extend({
	
	el: $('#content'),
	
    events : {
    	"click #update-coffee-order" : "updateCoffeeOrder"
    }, 

    render: function () {
    	var that = this;
    	var coffeeOrderModel = new CoffeeManager.Model.CoffeeOrderModel({ id: this.id });
    	coffeeOrderModel.fetch({success: function() {
        	CoffeeManager.Util.renderTemplate("EditCoffeeOrderView", that.el, { items: that.collection.toJSON() }, function() {
        		that.updateValues(coffeeOrderModel.toJSON());
        	});
      	}});
        return this;
    },
    
    updateValues: function(json) {
    	$.each(json.entries, function() {
    		$("#"+this.item.id).val(this.quantity);
    	});
    },
    
    updateCoffeeOrder : function (e) {
    	event.preventDefault();
		order = new Array();
		var that = this;
		$('input[name^=quantity]', this.el).filter(function(index, element) {
			return $(element).val() && $(element).val() > 0;
		}).each(function(index, element) {
			var id = $(element).attr("id");
			var quantity = parseInt($(element).val());
			order.push({
				id : id,
				quantity : quantity
			});
		});
		var coffeeOrderModel = new CoffeeManager.Model.CoffeeOrderModel({
			id: this.id,
			entries : order
		});
		coffeeOrderModel.save(null, {
			success : function() {
				CoffeeManager.Application.navigate("orders/"+that.id, true);
			}
		});
    }
});