Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'AM',

    appFolder: 'app',

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'panel',
                    title: 'Users',
                    html : 'List of users will go here'
                }
            ]
        });
    }
});

//Ext.define('AM.controller.Users', {
//    extend: 'Ext.app.Controller',
//
//    init: function() {
//        this.control({
//            'viewport > panel': {
//                render: this.onPanelRendered
//            }
//        });
//    },
//
//    onPanelRendered: function() {
//        console.log('The panel was rendered');
//    }
//});