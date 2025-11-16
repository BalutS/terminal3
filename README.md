Se quiere crear la funcion de crear en "terminal_autobuses" y las persistencias de datos.

desde las opciones de crear en los combobox de la cabecera del programa se debe poder acceder a las opciones de crear de cada una:

Equipaje:
pesoEquipaje: opcion para digitar el peso del equipaje.
duenioEquipaje: combobox para elegir al pasajero duenio del equipaje (en el combobox se deben mostrar los nombres de los pasajeros que ya han sido creados, cuya informacion debe ser extraida desde la persistencia de pasajero).
opcion para subir una foto del equipaje.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Equipaje.txt debera guardar esta informacion, de la siguiente manera. "idEquipaje;pesoEquipaje;idPasajero;identificador unico de la imagen seleccionada"

Pasajero:
cedulaPasajero: opcion para digitar la cedula del pasajero.
nombrePasajero: opcion para escribir el nombre del pasajero.
edadPasajero: opcion para digitar la edad del pasajero.
generoPasajero: radio button para elegir el genero del pasajero. (verdadero si es masculino, falso si es femenino)
opcion para subir una foto del pasajero.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Pasajero.txt debera guardar esta informacion, de la siguiente manera. "cedulaPasajero;nombrePasajero;edadPasajero;generoPasajero;identificador unico de la imagen seleccionada"


Tiquete:
pasajeroTiquete: combobox para elegir al pasajero del tiquete (en el combobox se deben mostrar los nombres de los pasajeros que ya hayan sido creados, cuya informacion debe ser extraida desde la persistencia de pasajero).
viajeTiquete: combobox para elegir el viaje del tiquete (en el combobox se deben mostrar la informacion de los viajes que ya han sido creados, de esta manera "idViaje, horaViaje, fechaViaje, ciudadOrigen-ciudadDestino", cuya informacion debe ser extraida desde la persistencia de viaje).
asientoTiquete: combobox para elegir el asiento del tiquete (en el combobox se debe mostrar el id de los asientos que ya han sido creados, extrayendo la informacion de la persistencia de asiento).
opcion para subir una foto del tiquete.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Tiquete.txt debera guardar esta informacion, de la siguiente manera. "idTiquete;idPasajero;idViaje;idAsiento;identificador unico de la imagen seleccionada"

Asiento:
busAsiento: combobox para elegir el bus del asiento (en el combobox se debe mostar la informacion de los buses que ya han sido creados de esta manera "idBus, modeloBus", extrayendo la informacion desde la persistencia de bus).
estadoAsiento: radio button para elegir el estado del asiento (verdadero si activo, falso si es inactivo).
opcion para subir una foto del asiento.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Asiento.txt debera guardar esta informacion, de la siguiente manera. "idAsiento;idBus;estadoAsiento;identificador unico de la imagen seleccionada"

Viaje:
rutaViaje: combobox para elegir la ruta del viaje (en el combobox se deben mostrar la informacion de las rutas que ya han sido creadas, de esta manera "ciudadOrigen-ciudadDestno", cuya informacion debe ser extraida desde la persistencia de ruta).
conductroViaje: combobox para elegir el conductor del viaje (en el combobox se debe mostrar el nombre de los conductores que ya han sido creados, cuya informacion debe ser extraida desde la persistencia de conductor).
busViaje: combobox para elegir el bus del viaje (en el combobox se debe mostar la informacion de los buses que ya han sido creaos esta manera "idBus, modeloBus", cuya informacion debe ser extraida desde la persistencia de bus).
fechaViaje: opcion para escribir la fecha del viaje.
horaViaje: opcion para digitar la hora del viaje.
opcion para subir una foto del viaje.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Viaje.txt debera guardar esta informacion, de la siguiente manera "idViaje;idRuta;idConductor;idBus;fechaViaje;horaViaje;identificador unico de la imagen seleccionada"

Conductor:
cedulaConductor: opcion para digitar la cedula del conductor.
nombreConductor: opcion para escribir el nombre del conductor.
edadConductor: opcion para digitar la edad del conductor.
generoConductor: radio button para elegir el genero del conductor (verdadero si es masculino, falso si es femenino)
opcion para subir una foto del conductor.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Conductor.txt debera guardar la siguiente informacion, de la siguiente manera "cedulaConductor;nombreConductor;edadConductor;generoConductor;identificador unico de la imagen seleccionada"

Bus:
modeloBus: opcion para escribir el modelo del bus.
empresaBus: combobox para elegir la empresa del bus (en el combobox se debe mostrar el nombre de las empresas que ya han sido creadas, cuya informacion debe ser extraida desde la persistencia de empresa).
opcion para subir una foto del bus.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Bus.txt debera guardar la siguiente informacion, de la siguiente manera "idBus;modeloBus;idEmpresa;identificador unico de la imagen seleccionada"

Empresa:
nombreEmpresa: opcion para escribir el nombre de la empresa.
opcion para subir una foto del bus.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Empresa.txt debera guardar la siguiente informacion de la siguiente manera "idEmpresa;nombreEmpresa;identificador unico de la imagen seleccionada"

Ruta:
ciudadOrigenRuta: opcion para escribir la ciudad de origen.
ciudadDestinoRuta: opcion para escribir la ciudad de destino.
tarifaRuta: opcion para digitar la tarifa de la ruta.
opcion para subir una foto de la ruta.
visualizacion de la imagen seleccionada.
boton para grabar la informacion.
Ruta.txt debera guardar la siguiente informacion de la siguiente manera "idRuta;ciudadOrigenRuta;ciudadDestinoRuta;tarifaRuta;identificador unico de la imagen seleccionada"

en todos los cuestionarios anteriores, el cuestionario debera ser limpiado de toda la informacion que el usuario digito, luego de haber sido guardada.
en todas las clases que tengan como atributo "id", dicho id debe ser generado de forma auotomatica (a excepcion de "Conductor" y "Pasajero" ya que estos no tienen id).
toda la informacion debe ser guardada en la persistencia, en archivos .txt. Ejemplo la informacion de bus se debe guardar en un arhivo llamado Bus.txt. 
en todas las persistencias la informacion se debe separar por punto y coma.
cada linea en los txt representa un registro.
los datos de tipo "Double" se deberan guardar en la persistencia con todos los decimales (sin formato exponencial).
Las imagenes se deben guardar con un identificador unico.
el identificador de la imagen tambien debe estar guardada en los archivos .txt de la persistencia.

Para poder hacer todas las implementacios anteriores, utiliza como guia al proyecto "Guia" que tiene implementaciones muy similares. Utiliza sintaxis, organizacion de clase, metodos, persistencia y diseño de interfaz, de la manera mas similar posible a como esta hecho en el proyecto "Guia"
Trabaja utilizando como base las clases y el codigo ya creado y adaptandote a el, no borres ni hagas cambios al codigo ya he creado a menos que sea estrictamente necesario para el funcionamiento del codigo






