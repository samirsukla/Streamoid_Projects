const express = require('express');
var serveIndex = require('serve-index');

var path = require('path');

const app = express();
app.use('/p',express.static(path.join(__dirname,'')));
app.use('/p',serveIndex(path.join(__dirname,'')));
app.listen(9300, ()=>console.log('Server started and listening in 9300 port'));
