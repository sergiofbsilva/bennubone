require.config({

    paths: {
        'jquery': 'libs/jquery/jquery-min',
        'less': 'libs/less/less-min',
        'moment': 'libs/moment/moment-min',
        'underscore': 'libs/underscore/underscore-min',
        'mustache': 'libs/mustache/mustache-min',
        'backbone': 'libs/backbone/backbone-min',
        'marionette': 'libs/backbone/backbone.marionette-min',
        'jquery.bootstrap': 'libs/bootstrap/bootstrap-min',
        'text': 'libs/require/text',
        'i18n': 'libs/require/i18n',
        'appLayout': 'layouts/AppLayout',
        'templates': '../templates'
    },

    shim: {
        'moment': {
            exports: 'Moment'
        },
        'underscore': {
            exports: '_'
        },
        'jquery.bootstrap': {
            deps: ['jquery'],
            exports: 'jquery'
        },
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'marionette': {
            deps: ['backbone'],
            exports: 'Backbone.Marionette'
        }
    },

    deps: ['main']
});