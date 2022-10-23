function agregarParada(boton){
    let cantidadParadas = document.querySelectorAll(".paradaIntermedia").length
    let parada = document.createElement('div');
    parada.classList.add('col-8');
    parada.classList.add('d-flex');
    parada.classList.add('my-2');
    parada.classList.add('paradaIntermedia');
    parada.innerHTML= `
    <label for="exampleDataList" class="form-label my-auto ms-5 paradaIntermediaLabel">Parada ${cantidadParadas + 1}</label>
    <i class="fa fa-trash-o align-self-center ms-2" aria-hidden="true" onclick="eliminarParada(this)"></i>
    <input class="form-control w-50 ms-auto" list="datalistOptions" id="exampleDataList" placeholder="Type to search...">
    <datalist id="datalistOptions">
      <option value="San Francisco">
      <option value="New York">
      <option value="Seattle">
      <option value="Los Angeles">
      <option value="Chicago">
    </datalist>`

    let barra = document.createElement('div');
    barra.classList.add('container');
    barra.classList.add('d-flex');
    barra.classList.add('align-items-center');
    barra.innerHTML= `<div class="col-8 d-flex justify-content-center my-2">
    <hr class="line">
    <button type="button" class="btn btn-outline-secondary btn-circle" onclick="agregarParada(this)">+</button>
    <hr class="line">
  </div>
  <select class="form-select h-50">
    <option selected hidden>Seleccione transporte del tramo</option>
    <option value="1">One</option>
    <option value="2">Two</option>
    <option value="3">Three</option>
  </select>`

    let seccion = boton.parentNode.parentNode
    seccion.insertAdjacentElement('afterend', parada);
    parada.insertAdjacentElement('afterend', barra);
    enumerarParadas();
}

function eliminarParada(boton){
  let contenido = document.getElementById("contenido");
  let barra = boton.parentNode.nextSibling;
  contenido.removeChild(boton.parentNode);
  contenido.removeChild(barra);
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