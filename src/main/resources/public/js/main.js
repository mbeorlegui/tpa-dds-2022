/*
    Pantalla: reportes
    Funcion: habilitar el select y generar options para las organizaciones
*/
function obtenerOrganizaciones(){
    let selectEntidad = document.getElementById("entidad");
    selectEntidad.disabled = false;
    //tendría que generar options para las organizaciones registradas en la db
}

/*
    Pantalla: reportes
    Funcion: habilitar el select y generar options para los sectores territoriales
*/
function obtenerSectoresTerritoriales(){
    let selectEntidad = document.getElementById("entidad");
    selectEntidad.disabled = false;
    //tendría que generar options para los sectores territoriales registrados en la db
}

/*
    Pantalla: request
    Funcion: habilitar el select de sectores y generar options segun una organizacion
*/
function obtenerSectorDe(organizacion){
    let selectSector = document.getElementById("sector");
    selectSector.disabled = false;
    //tendría que generar options para los sectores territoriales registrados en la db
}

/*
    Pantalla: registrarMedicionParticular
    Funcion: cambiar title icono info de periodo segun la periodicidad
*/
function obtenerFormato(icono){
    let inputPeriodicidad = document.getElementById("periodicidad").value
    if(inputPeriodicidad==""){
        icono.setAttribute("title","Seleccione primero la periodicidad")
    }else if(inputPeriodicidad=="Mensual"){
        icono.setAttribute("title","Inserte periodo con formato: 01/2022")
    }else if(inputPeriodicidad=="Anual"){
        icono.setAttribute("title","Inserte periodo con formato: 2022")
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
    let contenedores = document.querySelectorAll(".mes-container")
    console.log(contenedores)
    for(contenedor of contenedores){
        var nodes = contenedor.childNodes;
        console.log(nodes)
        for(node of nodes){
            if(boton.value == "anual"){
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
