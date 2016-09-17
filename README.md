# Olx Test

## Asunciones

Uso Spring Boot como la librería principal para ejecutar la API. La cual contiene un Tomcat embebido dentro de la aplicación.
El codigo se divide en Controller, Service, Dto, Entity. No hay capas Repository ni Dao dado que no hay persistencia.
Al iniciar la aplicación se crean 3 cuentas en memoria con valores aleatorios.
Para encolar las transacciones pendientes uso una LinkedBlockingQueue, ya que es eficiente para encolar y desencolar y Thread-Safe.



