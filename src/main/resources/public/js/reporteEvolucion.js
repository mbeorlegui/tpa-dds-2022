let datosEvolucion
let etiquetasPeriodicidad
function inicializarDatos(listaHc, periodicidad){
    datosEvolucion = listaHc
    etiquetasPeriodicidad = []
    let mes
    for (periodo of periodicidad){
        if (periodo.mes >= 10) {
            mes = periodo.mes
        } else {
            mes = "0"+periodo.mes
        }
        etiquetasPeriodicidad.push(mes+"/"+String(periodo.anio).substring(2))
    }
    console.log(etiquetasPeriodicidad)
    //etiquetasPeriodicidad = periodicidad
}

/*
    Pantalla: reporte de evolucion
    Funcion: generar el grafico de evolucion
*/
function generarGrafico(){
    let grafico = document.getElementById('myChart');
    console.log(etiquetasPeriodicidad) 
    let myChart = new Chart(grafico, {
        type: 'bar',
        data: {
            labels: etiquetasPeriodicidad,
            datasets: [{
                label: 'Huella de carbono por periodo',
                data: datosEvolucion,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor:'rgba(75, 192, 192, 1)',
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