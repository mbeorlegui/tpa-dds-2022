# Scheduler

Para probar el job que ejecuta el scheduler debemos compilar nuestro proyecto. Maven nos facilita esto, por lo que sólo
deberíamos hacer

```console
$ mvn package
```

Luego, corremos el comando ejecutando directamente el método principal (`Main`) de la clase `OrganizacionesRepositorio`:

```console
$ java -cp $PWD:target/ejercicio-1.0-SNAPSHOT.jar domain.organizacion.RepoOrganizaciones <LINK>
```

**NOTA**: Tener en cuenta que `$PWD` es una variable de entorno de Linux que almacena el path actual.

# Crontab

**ACLARACIÓN:** Suponemos que el deploy de la aplicación va a hacerse en un servidor con Linux.

Para crear una tarea programada en Linux, debemos usar el comando `crontab -e`.
Una vez dentro del archivo que se abre, debemos configurar la acción a realizar, y el horario en el que se ejecuta.

En nuestro caso, la tarea programada será la siguiente:

```
0 17 * * 1-5 mbeorlegui /usr/bin/java -cp /home/mbeorlegui/Escritorio/Matias/dds/tpIntegrador:target/ejercicio-1.0-SNAPSHOT.jar domain.organizacion OrganizacionesRepositorio > /dev/null 2>&1
```

Lo que quiere decir esta tarea programada es que de lunes a viernes a las 17 horas se ejecutará con el usuario
`mbeorlegui` (el que uso en mi máquina) todo el comando que explicamos anteriormente.

Luego, el comando final (`>/dev/null 2>&1`) es para evitar un warning propio del sistema operativo sobre el envío de
mails.

Cabe destacar que para este caso no nos sirve la variable de entorno `$PWD` ya que crontab funciona con rutas absolutas.

De forma genérica, y para deployar en otro servidor, el comando nos quedaría de la siguiente forma:

```
0 17 * * 1-5 <USER> /usr/bin/java -cp <PROJECT_PATH>:target/ejercicio-1.0-SNAPSHOT.jar domain.organizacion OrganizacionesRepositorio > /dev/null 2>&1
```

## Testear funcionamiento

Debido a que dejamos que el sistema operativo se encargue de la tarea programada, no hay mayor testeo que el monitoreo.
Para hacer esto, podemos usar varios comando, en general todos asociados con los logs que brinda el sistema.

Un comando que se puede utilizar es el siguiente:

```console
$ grep CRON /var/log/syslog
```

Allí pudimos verificar el funcionamiento de nuestra tarea programada, viendo que se ejecutaba a la hora que necesitamos:

```
Jul 16 17:00:01 MatiasFierro CRON[51021]: (mbeorlegui) CMD (mbeorlegui /usr/bin/java -cp /home/mbeorlegui/Escritorio/Matias/dds/tpIntegrador:target/ejercicio-1.0-SNAPSHOT.jar domain.organizacion.RepoOrganizaciones >/dev/null 2>&1)
```