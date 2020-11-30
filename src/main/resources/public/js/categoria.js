const categorias = new  Vue({
        el: "#etiquetas",
        data: {
            lista: [],
            nombreCategoria: '',
            candidatoAQuitar:''
        },
        methods: {
            agregarCategoria: function(){
                if(this.nombreCategoria != '' && !this.lista.includes(this.nombreCategoria)){
                    this.lista.push(this.nombreCategoria)
                    this.nombreCategoria=''
                }

                else
                    alert("Ingrese una etiqueta válida!")
            },
            quitarCategoria: function(candidatoAQuitarr){
                console.log(candidatoAQuitarr);
                console.log(this.lista);
                this.lista = this.lista.filter(function(x){ return x!=candidatoAQuitarr;});
            }
        }
    }
);

const presupuestos = new  Vue({
        el: "#presupuestos",
        data: {
            lista: [],
            nombrePresupuesto: '',
            candidatoAQuitar:''
        },
        methods: {
            agregarPresupuesto: function(){
                if(this.nombrePresupuesto != '' && !this.lista.includes(this.nombrePresupuesto)){
                    this.lista.push(this.nombrePresupuesto)
                    this.nombrePresupuesto=''
                }

                else
                    alert("Ingrese un presupuesto válido!")
            },
            quitarPresupuesto: function(candidatoAQuitarr){
                console.log(candidatoAQuitarr);
                console.log(this.lista);
                this.lista = this.lista.filter(function(x){ return x!=candidatoAQuitarr;});
            }
        }
    }
);

const items = new  Vue({
        el: "#items",
        data: {
            lista: [],
            nombreItem: '',
            candidatoAQuitar:''
        },
        methods: {
            agregarItem: function(){
                if(this.nombreItem != '' && !this.lista.includes(this.nombreItem)){
                    this.lista.push(this.nombreItem)
                    this.nombreItem=''
                }

                else
                    alert("Ingrese un item válido!")
            },
            quitarItem: function(candidatoAQuitarr){
                console.log(candidatoAQuitarr);
                console.log(this.lista);
                this.lista = this.lista.filter(function(x){ return x!=candidatoAQuitarr;});
            }
        }
    }
);

$(document).ready(function(){

    $("#formulario").submit(function(e){
        e.preventDefault();

        var form = $(this);
        var info = form.serializeArray();
        info.push(
            {name: 'etiquetas', value: JSON.stringify(categorias.lista)},
            {name: 'presupuestos', value: JSON.stringify(presupuestos.lista)},
            {name: 'items', value: JSON.stringify(items.lista)}
        )

        console.log(info);

//        $.ajax({
//            type: form.attr('method'),
//            url: form.attr('action'),
//            data: info
//          });

        $.post($(this).attr("action"),info, function(response){
            window.location = "/operaciones/new";
        });
//        return false;
    });
});



