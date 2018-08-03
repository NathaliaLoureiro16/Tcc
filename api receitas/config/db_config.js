var mongoose = require('mongoose');

//var urlString = 'mongodb://localhost/API';
var urlString = 'mongodb://nathalialoureiro:abc010203@ds137687.mlab.com:37687/tccfundatec';

mongoose.connect(urlString, function(error, res) {
    if (error) {
        console.log('Não foi possivel conectar a: ' + urlString);
    } else {
        console.log('Conectado a: ' + urlString);
    }
});