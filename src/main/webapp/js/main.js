require(['jquery', 'jquery.bootstrap', 'backbone', 'mustache', 'marionette', 'app', 'router', 'coffee'], function($, jQueryBootstrap, Backbone, Mustache, Marionette, App, Router, Coffee) {

    $.ajaxSetup({
        statusCode : {
            401 : function() {
                // Redirect the to the login page.
                App.router.navigate("login", true);
            },
            403 : function() {
                // 403 -- Access denied
                App.router.navigate("login", true);
            }
        }
    });

    Backbone.ajaxSync = Backbone.sync;

    Backbone.customSync = function(method, model, option) {
        option.beforeSend = function(jqXHR) {
            if (localStorage.getItem("auth") != null) {
                var auth = localStorage["auth"];
                jqXHR.setRequestHeader('Authorization', 'Basic ' + auth);
            }
        };
        return Backbone.ajaxSync(method, model, option);
    }

    Backbone.sync = Backbone.customSync;
    Backbone.emulateJSON = true;

    Backbone.Marionette.Renderer.render = Mustache.to_html;

    App.addRegions({
        page: "body"
    });

    App.addInitializer(function() {
        Router.initialize();
        Backbone.history.start();
    });

    App.start();

});