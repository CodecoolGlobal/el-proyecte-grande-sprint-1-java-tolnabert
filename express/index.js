const express = require('express')
const url = require('url')
const proxy = require('express-http-proxy')
const apiProxy = proxy('http://localhost:8080/', {
  proxyReqPathResolver: req => url.parse(req.originalUrl).path
})


const app = express(); 

app.use(express.urlencoded({ extended: true })) 
app.use(express.json()) 
app.use(express.static('public'))
app.use('/api/*', apiProxy)


app.listen(5000, 
() => console.log(`[bootup]: Server is running at port: 5000`));
