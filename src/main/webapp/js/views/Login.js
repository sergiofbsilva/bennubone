define([
    'jquery',
    'mustache',
    'backbone',
    'app',
    'router',
    'coffee',
    'models/Login',
    'text!templates/Login.html'
], function($, Mustache, Backbone, App, Router, Coffee, LoginModel, tpl) {

    return Backbone.Marionette.CompositeView.extend({

        template: tpl,

        events: {
            "click #login-button": "login"
        },

        login: function(e) {
            e.preventDefault();
            var email = $('input[name=email]', this.el).val();
            var password = $('input[name=password]', this.el).val();
            if(email === "" || password === "") {
                console.log("email or password are empty");
            } else {
                var loginModel = Coffee.LoginModel;
                loginModel.save({ email: email, password: password }, { success: function(model, response) {
                    var auth = btoa(response.userId+':'+response.token);
                    localStorage["auth"] = auth;
                    Router.initialize();
                    App.router.navigate("", { trigger: true} );
                }});
            }
        }
    });
});