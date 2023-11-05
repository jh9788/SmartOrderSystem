// src/main/frontend/src/setProxy.js

const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: 'http://localhost:8080', changeOrigin: true, ws:true
        })
    );
    app.use(
        ['/chat','/app', '/topic'],
        createProxyMiddleware({
            target: 'http://localhost:8080', ws: true
        })
    );

};