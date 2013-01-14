var AppRouter = Backbone.Router.extend({

    routes: {
        ""                  : "showHome",
        "home"              : "showHome",
		"orders/"           : "showOrders",
		"orders/:id"        : "showOrder",
		"user/list"         : "showUsers",
		"order/create"      : "addOrder",
		"item/list"			: "showItems",
		"item/create"      : "addItem",
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
    		orderListView = new OrderListView( { collection : orderCollection });
    		$('#content').html(orderListView.el);
            that.headerView.selectMenuItem('list-orders-menu');
    	}})
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