Sistema de Pedidos - Clean Architecture
📌 Descripción
Este proyecto implementa un sistema de gestión de pedidos utilizando Clean Architecture con Spring Boot.

Se separan claramente las responsabilidades en capas (círculos), logrando:

Bajo acoplamiento

Alta cohesión

Código mantenible y escalable

🏗️ Arquitectura
El sistema sigue los 4 círculos de Clean Architecture:

🔵 Domain
Contiene la lógica de negocio pura:

Pedido (Aggregate Root)

PedidoId, Dinero, LineaPedido

EstadoPedido

✔ No depende de Spring
✔ Contiene reglas del negocio

🟡 Use Case
Casos de uso del sistema:

CrearPedidoUseCase

ConsultarPedidoUseCase

CrearPedidoService

ConsultarPedidoService

PedidoRepositoryPort

✔ Orquesta la lógica del dominio
✔ Usa interfaces (DIP)

🟠 Adapter
📥 Entrada (Web)
PedidoController

DTOs:

CrearPedidoRequest

LineaPedidoRequest

PedidoResponse

LineaPedidoResponse

📤 Salida (Persistencia)
PedidoRepositoryAdapter

PedidoJpaRepository

PedidoJpaEntity

✔ Traduce entre HTTP ↔ dominio ↔ base de datos

🔴 Frameworks & Drivers
Spring Boot

Spring Web

Spring Data JPA

H2 Database

⚙️ Tecnologías
Java 17

Spring Boot 3

Maven

H2 Database

IntelliJ IDEA

Postman

▶️ Ejecución
En IntelliJ
Ejecutar:

CleanpedidosApplication
Con Maven
mvn spring-boot:run
Servidor:

http://localhost:8080
🔌 Endpoints
📦 Crear Pedido
POST

/api/pedidos
📦 Buscar por ID
GET

/api/pedidos/{id}
📦 Listar pedidos
GET

/api/pedidos
📥 Ejemplo de Request
{
"clienteNombre": "Ana Garcia",
"lineas": [
{"productoNombre": "Laptop",
"cantidad": 1,
"precioUnitario": 1500.00 }]
}
📤 Ejemplo de Response
{
"pedidoId": "uuid-generado"
}
🧪 Pruebas en Postman
Se verificó:

✔ Creación de pedidos (POST → 201)
✔ Consulta por ID (GET)
✔ Listado de pedidos
✔ Validaciones (errores 400)

📸 Evidencias
Se incluyen capturas de:

Proyecto ejecutándose en IntelliJ

Petición POST en Postman

Respuesta JSON

Endpoints GET funcionando

⚠️ Reglas cumplidas
✔ Domain sin Spring
✔ UseCase sin framework
✔ Separación de capas correcta
✔ Uso de puertos (interfaces)
✔ Inyección de dependencias

🚀 Conclusión
Se implementó correctamente Clean Architecture en un sistema backend, cumpliendo:

Separación de responsabilidades

Independencia del dominio

Arquitectura escalable