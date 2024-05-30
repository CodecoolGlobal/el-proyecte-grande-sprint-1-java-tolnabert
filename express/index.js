const express = require('express')
const { createProxyMiddleware } = require('http-proxy-middleware')
// const url = require('url')
// const proxy = require('express-http-proxy')
// const apiProxy = proxy('http://app_chilibeans:8080', {
//   proxyReqPathResolver: req => url.parse(req.originalUrl).path
// })


const app = express(); 

app.use('/api', createProxyMiddleware({
  target: 'http://app-chilibeans:8080',
  changeOrigin: true
}))

app.use(express.urlencoded({ extended: true })) 
app.use(express.json()) 

app.use(express.static('public'))

// app.use('/api/*', apiProxy)


app.listen(5000, 
() => console.log(`[bootup]: Server is running at port: 5000`));
