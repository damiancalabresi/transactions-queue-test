# Olx Test

## Asunciones

Uso Spring Boot como la librería principal para ejecutar la API. La cual contiene un Tomcat embebido dentro de la aplicación.
El codigo se divide en Controller, Service, Dto, Entity. No hay capas Repository ni Dao dado que no hay persistencia.
Al iniciar la aplicación se crean 3 cuentas en memoria con valores aleatorios.
Uso la Validation-API de Java para validar el input de los servicios REST.
Para encolar las transacciones pendientes se usa un ExecutorService. Esta implementación provista por Java utiliza un ThreadPoolExecutor y un LinkedBlockingQueue (Implementación de Queue eficiente y thread-safe).
Utilizo un WorkStealingPool, que decide la cantidad de threads en el pool a partir de la cantidad de procesadores disponibles.

## Errores manejados
* No existe la cuenta con el id recibido (AccountDoesNotExistException)
* No se puede procesar la transacción (TransactionRejectException)


