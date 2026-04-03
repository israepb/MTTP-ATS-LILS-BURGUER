# MTTP-ATS-LILS-BURGUER
> Proyecto de Métodos y Técnicas de Programación 1 - 2026

## 👥 Integrantes
* **Callejas Rodríguez Jorge Emilio** (Representante)
* **Choquecahuana Cahuana Darwin**
* **Meriles Ballivian Valentin Alejandro**
* **Saigua Clemente Ivo**
* **Meneses Mendieta Reiny Matias**
* **Fuentes Flores Samuel Ernesto**
* **Perez Brañez Israel Emanuel**

-------------------------------------------------------

## 🛠️ Distribución de Módulos

| Responsable | Clases / Módulos |
| :--- | :--- |
| **Ivo** | Administrador, Gerente, Cajero |
| **Darwin** | Cocinero, Repartidor, Cliente |
| **Matias** | Producto, Categoría, Pedido |
| **Valentin** | Detalle de Producto, Inventario, Ingredientes |
| **Samuel** | Receta, Pago, Proveedor |
| **Israel** | Promoción, Combo, Ticket, Factura |

-------------------------------------------------------
📌 FASE I – Sistema de Pagos y Facturación
🧩 Descripción

En esta fase se implementó el módulo de pagos, permitiendo registrar transacciones, generar comprobantes (facturas y tickets) y visualizar reportes mediante una interfaz de consola.

⚙️ Aspectos técnicos
Desarrollo en Java aplicando Programación Orientada a Objetos
Uso del patrón DAO (Data Access Object) para separar la lógica de negocio del acceso a datos
Persistencia mediante archivos de texto (.txt) usando formato delimitado (|)
Manejo de fechas con LocalDateTime para registrar transacciones
Uso de colecciones (List, ArrayList) para gestión de datos en memoria
🧾 Funcionalidades
Registro de pagos
Generación de facturas y tickets
Almacenamiento y lectura de datos
Cálculo de totales
Visualización de reportes por consola
🖥️ Interfaz

El sistema utiliza una interfaz por consola basada en menús, lo que permite interactuar con el usuario de forma sencilla y funcional en esta etapa del desarrollo.

⚠ Limitaciones
Validaciones básicas
Algunas clases con múltiples responsabilidades

## 📂 Estructura del Proyecto
* `/src`: Código fuente organizado por paquetes.
