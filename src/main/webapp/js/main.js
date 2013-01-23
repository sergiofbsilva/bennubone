CoffeeManager.Router.Main = Backbone.Router.extend({

    routes: {
        ""                  : "showHome",
        "home"              : "showHome",
		"orders"            : "showCoffeeOrders",
		"orders/:id"        : "showCoffeeOrder",
		"orders/edit/:id"   : "editCoffeeOrder",
		"order/create"      : "createCoffeeOrder",
		"users"         	: "showUsers",
		"items"				: "showCoffeeItems",
		"items/create"		: "createCoffeeItem",
		"batches"			: "showCoffeeBatches",
		"batches/:id"		: "showCoffeeBatch"
    },

    initialize: function () {
    	var headerView = CoffeeManager.Util.getHeaderView();
    	headerView.render();
    	var footerView = CoffeeManager.Util.getFooterView();
    	footerView.render();
    },
    
    showHome: function() {
        var homeView = new CoffeeManager.View.HomeView();
        homeView.render();
        //this.headerView.selectMenuItem('home-menu');
    },
    
    createCoffeeOrder: function() {
		var itemCollection = new CoffeeManager.Collection.CoffeeItemCollection();
		itemCollection.fetch({ success : function() {
			var createCoffeeOrderView = new CoffeeManager.View.CreateCoffeeOrderView({ collection: itemCollection });
			createCoffeeOrderView.render();
		}}); 
    },

    showUsers: function() {
    	var userCollection = new CoffeeManager.Collection.UserCollection();
    	userCollection.fetch({ success : function() {
	        var userListView = new CoffeeManager.View.UserListView({ collection: userCollection });
    	    userListView.render();	
    	}});
    },
    
    showUser: function(id) {
    	var userModel = new CoffeeManager.Model.UserModel({id : id});
    	userModel.fetch({ success : function() {
    		var userView = new CoffeeManager.View.UserView({ model : userModel });
			userView.render();
    	}});
    },
    
    showCoffeeOrder: function(id) {
    	var coffeOrderModel = new CoffeeManager.Model.CoffeeOrderModel({id : id});
    	coffeOrderModel.fetch({ success : function() {
    		var coffeeOrderView = new CoffeeManager.View.CoffeeOrderView({ model : coffeOrderModel });
			coffeeOrderView.render();
    	}});
    }, 
    
    editCoffeeOrder: function(id) {
    	var itemCollection = new CoffeeManager.Collection.CoffeeItemCollection();
		itemCollection.fetch({ success : function() {
			var editCoffeeOrderView = new CoffeeManager.View.EditCoffeeOrderView({ id: id, collection: itemCollection });
			editCoffeeOrderView.render();
		}}); 
    },
    
    showCoffeeOrders: function() {
    	var orderCollection = new CoffeeManager.Collection.CoffeeOrderCollection();
    	orderCollection.fetch( { success : function() {
    		var orderListView = new CoffeeManager.View.CoffeeOrderListView({ collection : orderCollection });
			orderListView.render();
    	}});
    },
    
   	showCoffeeBatch: function(id) {
    	var batchModel = new CoffeeManager.Model.CoffeeBatchModel({id : id});
    	batchModel.fetch({ success : function() {
    		var batchView = new CoffeeManager.View.CoffeeBatchView({ model : batchModel });
			batchView.render();
    	}});
    }, 
    
    showCoffeeBatches: function() {
    	var batchCollection = new CoffeeManager.Collection.CoffeeBatchCollection();
    	batchCollection.fetch({ success : function() {
    		var batchListView = new CoffeeManager.View.CoffeeBatchListView({ collection : batchCollection });
    		batchListView.render();
    	}});
    },
    
    showCoffeeItems: function() {
		var itemCollection = new CoffeeManager.Collection.CoffeeItemCollection();
		itemCollection.fetch({ success : function() {
			var itemListView = new CoffeeManager.View.CoffeeItemListView({ collection: itemCollection });
			itemListView.render();
		}}); 
    },
    
    createCoffeeItem: function() {
    	var createItemView = CoffeeManager.View.CreateCoffeeItemView();
    	createItemView.render();
    }
    
});

CoffeeManager.Application = new CoffeeManager.Router.Main();
Backbone.history.start();
Backbone.emulateJSON = true;