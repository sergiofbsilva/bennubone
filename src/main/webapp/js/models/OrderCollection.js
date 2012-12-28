window.OrderCollection = Backbone.Collection.extend({

    model: OrderModel,

    url: "api/orders"

});