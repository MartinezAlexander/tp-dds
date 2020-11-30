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
            lista_ids: [],
            nombrePresupuesto: '',
            candidatoAQuitar:''
        },
        methods: {
            agregarPresupuesto: function(){
                var id_descripcion = this.nombrePresupuesto.split("/")
                var id = id_descripcion[0]
                var descripcion = id_descripcion[1]

                if(descripcion != '' && !this.lista.includes(descripcion)){
                    this.lista.push(descripcion)
                    this.lista_ids.push(id)
                    this.nombrePresupuesto=''

                    console.log(this.lista_ids);
                    console.log(this.lista);
                }

                else
                    alert("Ingrese un presupuesto válido!")
            },
            quitarPresupuesto: function(candidatoAQuitarr){
                console.log(candidatoAQuitarr);
                var index = this.lista.indexOf(candidatoAQuitarr);

                this.lista.splice(index, 1);
                this.lista_ids.splice(index, 1);

                console.log(this.lista_ids);
                console.log(this.lista);
            }
        }
    }
);

const items = new  Vue({
        el: "#items",
        data: {
            lista: [],
            lista_ids: [],
            nombreItem: '',
            candidatoAQuitar:''
        },
        methods: {
            agregarItem: function(){
                var id_descripcion = this.nombreItem.split("/")
                var id = id_descripcion[0]
                var descripcion = id_descripcion[1]

                if(descripcion != '' && !this.lista.includes(descripcion)){
                    this.lista.push(descripcion)
                    this.lista_ids.push(id)
                    this.nombreItem=''

                    console.log(this.lista_ids);
                    console.log(this.lista);
                }

                else
                    alert("Ingrese un item válido!")
            },
            quitarItem: function(candidatoAQuitarr){
                console.log(candidatoAQuitarr);
                var index = this.lista.indexOf(candidatoAQuitarr);

                this.lista.splice(index, 1);
                this.lista_ids.splice(index, 1);

                console.log(this.lista_ids);
                console.log(this.lista);
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
            {name: 'presupuestos', value: JSON.stringify(presupuestos.lista_ids)},
            {name: 'items', value: JSON.stringify(items.lista_ids)}
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



