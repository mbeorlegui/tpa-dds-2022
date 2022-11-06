/*
    Pantalla: registrarTrayecto
    Funcion: generar todo lo necesario para agregar 
            una parada intermedia al hacer click en el boton "+"
*/
let transporteJson
let paradasJson
let ubicacionesJson
function inicializarDatos(transportesData, paradasPorTransporteData, ubicacionesData){
    transporteJson = JSON.stringify(transportesData)
    paradasJson = JSON.stringify(paradasPorTransporteData)
    ubicacionesJson = JSON.stringify(ubicacionesData)
}

function agregarParada(boton){
    let cantidadParadas = document.querySelectorAll(".paradaIntermedia").length
    let numeroDeParada = cantidadParadas + 1
    let nroTransporte = numeroDeParada + 1
    let contenidoParada = document.createElement('div');
    contenidoParada.classList.add('col-8');
    contenidoParada.classList.add('d-flex');
    contenidoParada.classList.add('my-2');
    contenidoParada.classList.add('paradaIntermedia');

    let labelParada = document.createElement('label');
    labelParada.classList.add('form-label');
    labelParada.classList.add('my-auto');
    labelParada.classList.add('ms-5');
    labelParada.classList.add('paradaIntermediaLabel');
    labelParada.setAttribute("for","ubicacionParada"+ numeroDeParada)
    labelParada.innerText = "Parada " + numeroDeParada

    let iconoEliminar = document.createElement('i')
    iconoEliminar.classList.add('fa');
    iconoEliminar.classList.add('fa-trash-o');
    iconoEliminar.classList.add('align-self-center');
    iconoEliminar.classList.add('ms-2');
    iconoEliminar.setAttribute("onclick","eliminarParada(this)")

    let inputUbicacion = document.createElement('input')
    inputUbicacion.classList.add('form-control');
    inputUbicacion.classList.add('w-50');
    inputUbicacion.classList.add('ms-auto');
    inputUbicacion.classList.add('ubicacion');
    inputUbicacion.setAttribute("list","datalist"+numeroDeParada)
    inputUbicacion.setAttribute("id","ubicacionParada"+numeroDeParada)
    inputUbicacion.setAttribute("onfocus","eliminarTexto(this)")
    inputUbicacion.setAttribute("placeholder","Escriba para buscar...")
    inputUbicacion.disabled = true

    let datalist = document.createElement('datalist')
    datalist.classList.add('listaUbicaciones');
    datalist.setAttribute("id","datalist"+numeroDeParada)/*
    let options = ["San Francisco","New York","Seattle","Los Angeles","Chicago"]
    for(optionValue of options){
        let option = document.createElement('option')
        option.setAttribute("value",optionValue)
        datalist.appendChild(option)
    }*/
    generarListaUbicaciones(datalist,ubicacionesJson)

    contenidoParada.appendChild(labelParada);
    contenidoParada.appendChild(iconoEliminar);
    contenidoParada.appendChild(inputUbicacion);
    contenidoParada.appendChild(datalist)

    let barraTransporte = document.createElement('div')
    barraTransporte.classList.add('container');
    barraTransporte.classList.add('d-flex');
    barraTransporte.classList.add('align-items-center');

    let barraSeparacion = document.createElement('div');
    barraSeparacion.classList.add('col-8');
    barraSeparacion.classList.add('d-flex');
    barraSeparacion.classList.add('justify-content-center');
    barraSeparacion.classList.add('my-2');

    let linea1 = document.createElement('hr');
    linea1.classList.add('line');
    let linea2 = document.createElement('hr');
    linea2.classList.add('line');

    let botonParada = document.createElement('button')
    botonParada.classList.add('btn');
    botonParada.classList.add('btn-outline-secondary');
    botonParada.classList.add('btn-circle');
    botonParada.setAttribute("type","button")
    botonParada.setAttribute("onclick","agregarParada(this)")
    botonParada.innerText = '+'

    barraSeparacion.appendChild(linea1)
    barraSeparacion.appendChild(botonParada)
    barraSeparacion.appendChild(linea2)

    let transportes = document.createElement('select')
    transportes.classList.add('form-select');
    transportes.classList.add('h-50');
    transportes.classList.add('transporte');
    let idTransporte ='transporte'+nroTransporte
    transportes.setAttribute('id',idTransporte)
    transportes.setAttribute("onchange","modificarUbicaciones(this)");
    transportes.innerHTML=`<option selected hidden>Seleccione transporte del tramo</option>`

    barraTransporte.appendChild(barraSeparacion)
    barraTransporte.appendChild(transportes)
    

    let paradaAnterior = boton.parentNode.parentNode
    paradaAnterior.insertAdjacentElement('afterend', contenidoParada);
    contenidoParada.insertAdjacentElement('afterend', barraTransporte);
    enumerarParadas();
    generarOptions(idTransporte, transporteJson);
    bloquearInputsUbicacion(inputUbicacion.id)
}

/*
    Pantalla: registrarTrayecto
    Funcion: eliminar una parada intermedia al hacer click en el icono "trash"
*/
function eliminarParada(boton){
    let contenido = document.getElementById("contenido");
    let barraSeparacion = boton.parentNode.nextSibling;
    contenido.removeChild(boton.parentNode);
    contenido.removeChild(barraSeparacion);
    enumerarParadas();
}

/*
    Pantalla: registrarTrayecto
    Funcion: enumerar las paradas intermedias al agregar o eliminar
            para que queden numeradas de forma creciente
*/
function enumerarParadas(){
    let paradas = document.querySelectorAll('.paradaIntermediaLabel');
    let numero = 1;
    for (let parada of paradas) {
        parada.textContent = "Parada "+ numero;
        numero++;
    }
}

/*
    Funcion: seleccionado el transporte, las ubicaciones disponibles se limitan si es un
            transporte publico, cuando se modifique el transporte habilitar dos input ubicacion
 */
function modificarUbicaciones(select){
    let elementos = document.querySelectorAll(".listaUbicaciones, .transporte")
    let inputUbicaciones = document.querySelectorAll(".ubicacion, .transporte")
    let indexTransporte = obtenerNumeroNodo(elementos, select)
    ubicacionesDisponibles = JSON.parse(paradasJson)[select.selectedIndex]
    if(ubicacionesDisponibles.length > 0){
        //Es un transporte publico, se limita que el primer input no tenga la ultima
        //parada y que el segundo input no tenga la primer parada
        let ubicacionesSinPrimerParada = [].concat(ubicacionesDisponibles)
        let ubicacionesSinUltimaParada = [].concat(ubicacionesDisponibles)
        ubicacionesSinPrimerParada.shift()
        ubicacionesSinUltimaParada.pop()
        generarListaUbicaciones(elementos[indexTransporte-1],JSON.stringify(ubicacionesSinUltimaParada))
        generarListaUbicaciones(elementos[indexTransporte+1],JSON.stringify(ubicacionesSinPrimerParada))
    }else{
        //No es un transporte publico, no se limitan las ubicaciones
        generarListaUbicaciones(elementos[indexTransporte-1],ubicacionesJson)
        generarListaUbicaciones(elementos[indexTransporte+1],ubicacionesJson)
    }
    inputUbicaciones[indexTransporte-1].disabled = false;
    inputUbicaciones[indexTransporte+1].disabled = false;
}

/**
 * @param {NodeList} elementos se utiliza querySelector para intercalar input o lista de ubicacion y select transporte
 * @param {Node} nodo es el nodo que se busca su indice dentro del querySelector
 * @returns {Number} numero del nodo dentro de un querySelector
 */
function obtenerNumeroNodo(elementos, nodo){
    let nroNodo = 0
    while(!elementos[nroNodo].isSameNode(nodo)){
        nroNodo++
    }
    return nroNodo
}

/*
    Funcion: generar todos los options para el select de transporte
    Parametros: id - id del select
                transportes - list de objetos transporte
*/
function generarOptions(id, transportes){
    let select = document.getElementById(id)
    select.innerHTML= `<option selected hidden>Seleccione transporte del tramo</option>`
    for([i, t] of JSON.parse(transportes).entries()){
        crearOption(select, i, t)
    }
}

/*
    Funcion: generar un option para el select de transporte
    Parametros: select - nodo
                i - transporte id
                transporte - objeto transporte
*/
function crearOption(select, i, transporte){
    let nombre
    if(transporte.tipoTransporte=="PARTICULAR"){
        nombre=transporte.tipoDeVehiculo
    }else if(transporte.tipoTransporte=="CONTRATADO"){
        nombre=transporte.tipoDeServicioContratado
    }else if(transporte.tipoTransporte=="PUBLICO"){
        nombre=transporte.tipoDeTransportePublico + " " + transporte.linea
    }else{
        nombre=transporte.tipoTransporte
    }
    let option = document.createElement('option')
    option.setAttribute("value",i)
    option.innerText = nombre
    select.appendChild(option)
}

/*
    Funcion: generar datalist para el input de ubicacion
    TODO: no borrar los options, con las nuevas ubicaciones comparar con las existentes
*/
function generarListaUbicaciones(datalist,ubicaciones){
    datalist.innerText = ""
    for(data of JSON.parse(ubicaciones)){
        let option = document.createElement('option')
        let value = data.ubicacion.calle + " "+ data.ubicacion.altura
        option.setAttribute("value",value)
        datalist.appendChild(option)
    }
}

/*
    Funcion: eliminar texto de un elemento 
    (lo uso para focus del input ubicacion y cuando agrego paradas)
*/
function eliminarTexto(elemento){
    elemento.value=""
}

/*
    Funcion: desahabilitar los inputs que estan debajo de una nueva parada
            De esta manera deben elegir transporte antes que ubicacion 
*/
function bloquearInputsUbicacion(id){
    let elementos = document.querySelectorAll(".ubicacion")
    let inputUbicacion = document.getElementById(id)
    let elementoEncontrado = false
    for(elemento of elementos){
        if(elemento.isSameNode(inputUbicacion) || elementoEncontrado){
            elementoEncontrado = true
            eliminarTexto(elemento)
            elemento.disabled = true
        }
    }
}

/**
 * TODO: cuando selecciona una ubicacion, eliminar del siguiente input
 */