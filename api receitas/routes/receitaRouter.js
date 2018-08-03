var express = require('express');
var router = express.Router();
var receitaController = require('../controllers/receitaController');

router.get('/', function(req, res) {
     receitaController.list(function(resp){
        res.json(resp);
    });
});

module.exports = router;