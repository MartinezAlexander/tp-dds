
const categorias = new  Vue({
        el: "#categorias",
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
                    alert("Ingrese una categoria válida!")
            },
            quitarCategoria: function(candidatoAQuitarr){
                console.log(candidatoAQuitarr);
                console.log(this.lista);
                this.lista = this.lista.filter(function(x){ return x!=candidatoAQuitarr;});
            }
        }
    }
);