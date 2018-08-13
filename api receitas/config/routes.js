var router = require('express').Router();
var ingredienteController = require("../controller/ingredienteController.js");

router.get('/', function (req, res) {
    res.send("Hello World");
});

router.get('/', function (req, res) {
    res.json({ msg: 'Hello World' });
})
/* Route Music */
 router.get('/ingredientes/:categoria', ingredienteController.getIngredientesByCategoria);
// router.post('/musica', musicaController.save);
//router.put('/music', musicaController.alter);
//router.delete('/music', musicaController.delete);

module.exports = router;  