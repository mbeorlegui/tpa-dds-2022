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
    Pantalla: reporte de evolucion
    Funcion: generar el grafico de evolucion
*/
function generarGrafico(){
    let grafico = document.getElementById('myChart');
    let etiquetas = [] //serian los meses o años seleccionados
    let datos = [{
        label: 'Huella de carbono por periodo',
        data: [],
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        borderColor:'rgba(75, 192, 192, 1)',
        borderWidth: 1
    }]
 
    let myChart = new Chart(grafico, {
        type: 'bar',
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'], //etiquetas
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
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
