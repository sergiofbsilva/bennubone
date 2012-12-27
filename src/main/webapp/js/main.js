var AppRouter = Backbone.Router.extend({

    routes: {
        ""                  : "showHome",
        "home"              : "showHome",
		"order/list"        : "showOrders",
		"user/list"        : "showUsers",
		"order/create"      : "addOrder"
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
        if (!this.userListView) {
            this.userListView = new UserListView();
        }
        $('#content').html(this.userListView.el);
        this.headerView.selectMenuItem('list-users-menu'); 
    },
    
    showOrders: function() {
        if (!this.orderListView) {
            this.orderListView = new OrderListView();
        }
        $('#content').html(this.orderListView.el);
        this.headerView.selectMenuItem('list-orders-menu'); 
    }
    
});

utils.loadTemplate([], function() {
    app = new AppRouter();
    Backbone.history.start();
});