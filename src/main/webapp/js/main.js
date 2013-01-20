var AppRouter = Backbone.Router.extend({

    routes: {
        ""                  : "showHome",
        "home"              : "showHome",
		"orders"            : "showOrders",
		"orders/:id"        : "showOrder",
		"users"         	: "showUsers",
		"order/create"      : "addOrder",
		"items"				: "showItems",
		"item/create"		: "addItem",
		"batches"			: "showBatches"
    },

    initialize: function () {
        if(!this.headerView) {
            this.headerView = new HeaderView();
        }
        if(!this.footerView) {
            this.footerView = new FooterView();
        }
        $('#header').html(this.headerView.el);
        $('#footer').html(this.footerView.el);
    },
    
    showHome: function() {
        if (!this.homeView) {
            this.homeView = new HomeView();
        }
        $('#content').html(this.homeView.el);
        this.headerView.selectMenuItem('home-menu');
    },
    
    addOrder: function() {
        if (!this.createCoffeeOrderView) {
            this.createCoffeeOrderView = new CreateCoffeeOrderView();
        }
        $('#content').html(this.createCoffeeOrderView.el);
        this.headerView.selectMenuItem('createorder-menu');
    },

    showUsers: function() {
        var userListView = new UserListView();
        $('#content').html(userListView.el);
        this.headerView.selectMenuItem('list-users-menu'); 
    },
    
    showOrder: function(id) {
    	var orderModel = new OrderModel({id : id});
    	orderModel.fetch( { success : function() {
    		var orderView = new OrderView( { model : orderModel });
    		$('#content').html(orderView.el);
    	}})
    }, 
    
    showOrders: function() {
    	var that = this;
    	var orderCollection = new OrderCollection();
    	orderCollection.fetch( { success : function() {
    		$('#content').html(new OrderListView( { collection : orderCollection }).el);
            that.headerView.selectMenuItem('list-orders-menu');
    	}});
    },
    
    showBatches: function() {
    	var that = this;
    	var batchCollection = new BatchCollection();
    	batchCollection.fetch( { success : function() {
    		that.batchListView = new BatchListView( { collection : batchCollection });
    		$('#content').html(that.batchListView.el);
    		that.batchListView.parseTimestamps();
    		
            that.headerView.selectMenuItem('list-batches-menu');
    	}});
    },
    
    showItems: function() {
    	 if (!this.itemListView) {
             this.itemListView = new ItemListView();
         }
         $('#content').html(this.itemListView.el);
         this.headerView.selectMenuItem('list-items-menu'); 
    },
    
    addItem: function() {
         $('#content').html(new CreateItemView().el);
         this.headerView.selectMenuItem('createitem-menu');
    }
    
});

var app;

utils.loadTemplate([], function() {
    app = new AppRouter();
    Backbone.history.start();
    Backbone.emulateJSON = true;
});