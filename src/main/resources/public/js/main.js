/*
    Pantalla: registrarMedicionParticular
    Funcion: cambiar title icono info de periodo segun la periodicidad
*/
function obtenerFormato(icono){
    let inputPeriodicidad = document.getElementById("periodicidad").value
    if(inputPeriodicidad==""){
        icono.setAttribute("title","Seleccione primero la periodicidad")
    }else if(inputPeriodicidad=="Mensual"){
        icono.setAttribute("title","Inserte periodo con formato: MM/AAAA")
    }else if(inputPeriodicidad=="Anual"){
        icono.setAttribute("title","Inserte periodo con formato: AAAA")
    }else{
        icono.setAttribute("title","Seleccione una periodicidad valida")
    }
}

/*
    Pantalla: registrarMedicionParticular
    Funcion: habilitar input periodo luego de seleccionar periodicidad
*/
function habilitarPeriodo(selectPeriodo){
    let periodicidad = selectPeriodo.value
    let inputPeriodo = document.getElementById("periodoImputacion")
    if (periodicidad=="Mensual" || periodicidad=="Anual"){
        inputPeriodo.removeAttribute("disabled")
    } else {
        inputPeriodo.removeAttribute("disabled")
    }
}

/*
    Pantalla: reportes
    Funcion: habilitar y desahabilitar input mes luego de seleccionar periodicidad
*/
function modificarPeriodo(boton){
    let inputAnio = document.getElementById("anio")
    if(inputAnio != null){
        inputAnio.disabled = false
    }
    let contenedores = document.querySelectorAll(".mes-container")
    console.log(contenedores)
    for(contenedor of contenedores){
        var nodes = contenedor.childNodes;
        console.log(nodes)
        for(node of nodes){
            if(boton.value === "anual"){
                node.disabled = true
                node.hidden = true
                node.required = false
            }else{
                node.disabled = false
                node.hidden = false
                node.required = true
            }
        }
    }
}

/*
    Pantalla: reportes
    Funcion: cambiar encabezado tabla segun selecciona organizacion o sector territorial
*/
function modificarTabla(valor){
    let columna = document.getElementById("entidad-tabla");
    columna.innerText = valor
}

/*
    Pantalla: reportes
    Funcion: habilitar y desahabilitar select de organizacion
*/
function modificarSelectOrg(boton){
    let selectEntidad = document.getElementById("entidad")
    if(boton.value === "sector"){
        selectEntidad.disabled = true
        selectEntidad.hidden = true
        selectEntidad.required = false
    }else{
        selectEntidad.disabled = false
        selectEntidad.hidden = false
        selectEntidad.required = true
    }
}

function cambiarSeleccionReporte(tipo){
    let hcTotal = document.getElementById("hcTotal")
    let evolucion = document.getElementById("evolucion")
    let composicion = document.getElementById("composicion")
    if(tipo === "Huella de Carbono"){
        hcTotal.classList.remove("btn-secondary")
        hcTotal.classList.add("btn-primary")
    }else if(tipo === "Evolución"){
        evolucion.classList.remove("btn-secondary")
        evolucion.classList.add("btn-primary")
    }else if(tipo === "Composición"){
        composicion.classList.remove("btn-secondary")
        composicion.classList.add("btn-primary")
    }
}