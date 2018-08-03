var app = require('./config/app_config');
var db = require('./config/db_config');
var Receita = require('./models/Receita');
var Ingrediente = require('./models/Ingrediente');
var receitaController = require('./controllers/receitaController');
var receitas = require('./routes/receitaRouter');

app.get('/', function(req, res) {
    res.end('Api de receita');
});

//rotas utilizadas para as receitas
app.use('/receitas',receitas);