
var Ingrediente = require('../model/ingrediente');
var operations = {};
operations.getIngredientesByCategoria = function (req, res) {
    var categoria = req.params.categoria;
    Ingrediente.find({ categoria: categoria }, { ingredientes: 1, _id: 1 }, function (error, resultado) {

        if (error) {
            res.status(401).send({ error: 'não foi possível encontrar as receitas' });
        } else {
            res.status(200).json(resultado);
        }
    });
}


module.exports = operations;

