function agregarParada(boton){
    let cantidadParadas = document.querySelectorAll(".paradaIntermedia").length
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
    labelParada.setAttribute("for","ubicacionParada")
    labelParada.innerText = "Parada" + cantidadParadas + 1

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
    inputUbicacion.setAttribute("list","datalistOptions")
    inputUbicacion.setAttribute("id","ubicacionParada")
    inputUbicacion.setAttribute("placeholder","Type to search...")

    let datalist = document.createElement('datalist')
    let options = ["San Francisco","New York","Seattle","Los Angeles","Chicago"]
    for(optionValue of options){
        let option = document.createElement('option')
        option.setAttribute("value",optionValue)
        datalist.appendChild(option)
    }

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
    transportes.innerHTML=`<option selected hidden>Seleccione transporte del tramo</option>`

    let optionsTransportes = ["Bici","Auto","Colectivo","Taxi","Moto"]
    for(i in optionsTransportes){
        let option = document.createElement('option')
        option.setAttribute("value",i)
        option.innerText = optionsTransportes[i]
        transportes.appendChild(option)
    }

    barraTransporte.appendChild(barraSeparacion)
    barraTransporte.appendChild(transportes)

    let paradaAnterior = boton.parentNode.parentNode
    paradaAnterior.insertAdjacentElement('afterend', contenidoParada);
    contenidoParada.insertAdjacentElement('afterend', barraTransporte);
    enumerarParadas();
}

function eliminarParada(boton){
    let contenido = document.getElementById("contenido");
    let barraSeparacion = boton.parentNode.nextSibling;
    contenido.removeChild(boton.parentNode);
    contenido.removeChild(barraSeparacion);
    enumerarParadas();
}

function enumerarParadas(){
    let paradas = document.querySelectorAll('.paradaIntermediaLabel');
    let numero = 1;
    for (let parada of paradas) {
        parada.textContent = "Parada "+ numero;
        numero++;
    }
}