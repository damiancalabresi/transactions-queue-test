# Olx Test

## Asunciones

Uso Spring Boot como la librería principal para ejecutar la API. La cual contiene un Tomcat embebido dentro de la aplicación.
El codigo se divide en Controller, Service, Dto, Entity. No hay capas Repository ni Dao dado que no hay persistencia.
Al iniciar la aplicación se crean 3 cuentas en memoria con valores aleatorios.
Uso la Validation-API de Java para validar el input de los servicios REST.
Para encolar las transacciones pendientes se usa un ExecutorService. Esta implementación provista por Java utiliza un ThreadPoolExecutor y un LinkedBlockingQueue (Implementación de Queue eficiente y thread-safe).
Utilizo un WorkStealingPool, que decide la cantidad de threads en el pool a partir de la cantidad de procesadores disponibles.

## Transacciones
Las transacciones operan en memoria, por ende no pueden ser del tipo ACID (Atomicity, Consistency, Isolation, Durability).
La atomicidad se provee por medio del manejo de errores.
La consistencia tambien, la condiciones que puedan requerir un rollback, son evaluadas antes de hacerse las modificaciones en las cuentas. Si surge un error externo a las reglas de negocio, la aplicación dejará de funcionar y la información en memoria se perderá del todo.
La durabilidad no tiene sentido cuando se trabaja en memoria y no hay persistencia.
La Isolación es un punto importante a tratar en este caso, se deben tratar problemas de concurrencia.
### Concurrencia
Si dos o más transacciones modifican concurrentemente una cuenta, podría haber problemas al hacer una suma o resta en el valor, asi como podrían tenerse balances negativos en la cuenta.
Para evitar esto, al ejecutarse una transacción bloqueará las dos cuentas sobre las que opera. Esto se hace utilizando dos bloques synchronized anidados sobre las instancias de cada cuenta.
#### Evitar deadlocks
Al bloquearse una cuenta, y luego la otra, si al mismo tiempo hay una transacción inversa, puede ocurrir un deadlock.
Ejemplo A: 
Transaction 1 -> 2
Transaction 2 -> 1
Si se ejecutan las dos transacciones al mismo tiempo puede haber un deadlock.

Ejemplo B:
Transaction 1 -> 2
Transaction 2 -> 3
Transaction 3 -> 1
Aqui tambien puede haber un deadlock.

Si se bloquean las cuentas en orden ascendente:
Transaction 1 2
Transaction 2 3
Transaction 1 3
Se reduce la posibilidad de error, pero esta sigue existiendo.

Esto se conoce como "Dining philosophers problem" y la solución es utilizar un arbitrator, que bloquee al pedir una cuenta. En Java se implementa con la clase Lock.



## Errores manejados
* No existe la cuenta con el id recibido (AccountDoesNotExistException)
* No se puede procesar la transacción (TransactionRejectException)

Los errores se manejan con el ControllerAdvice ErrorCustomController, el cual es detectado por Spring.

## Documentación
Para auto-documentar los servicios REST uso la librería SpringFox, que integra Swagger a Spring Boot. En la URL:

http://localhost:8080/v2/api-docs

Se puede ver la descripción de los endpoints. En la URL:

http://localhost:8080/swagger-ui.html

Se pueden ver estos en una interfaz y llamarlos desde la misma.


