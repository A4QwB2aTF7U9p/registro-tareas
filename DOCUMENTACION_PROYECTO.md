# Documentación del Proyecto: Aplicación de Gestión de Tareas

## 1. Visión General
Aplicación móvil (Android/iOS) para gestión básica de tareas diarias.

## 2. Stack Tecnológico
*   **Framework:** Compose Multiplatform (Kotlin).
*   **Arquitectura:** MVVM simplificado para prototipo rápido.
*   **Persistencia:** In-memory (para alcanzar el objetivo de 10 min).
*   **UI:** Material Design 3.

## 3. Plan de Desarrollo (MVP 10 minutos)
El objetivo de este sprint es un prototipo funcional (MVP).
*   **Min 0-2:** Configuración y estructura básica. ✅
*   **Min 2-5:** Implementación del modelo de datos (`Task`) y ViewModel. ✅
*   **Min 5-10:** Interfaz de usuario (listado y formulario simple). ✅

### Estado del Proyecto
1.  **Fase 1: Setup y Arquitectura** ✅ (Completado).
2.  **Fase 2: Capa de Datos** ✅ (Completado: Room integrado).
3.  **Fase 3: Interfaz de Usuario (UI)** ✅ (Completado: Prototipo funcional).
4.  **Fase 4: Verificación y Testing** ✅ (Completado: Pruebas unitarias básicas para ViewModel).

## 4. Guía de Referencia Técnica

### Entidad de Tarea (Task)
```kotlin
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val dueDate: String, // Simplificado para MVP
    val priority: String = "Media" // Nueva funcionalidad: Alta, Media, Baja
)
```

## 6. Configuración de Dependencias (Gradle)
Para que Room funcione, asegúrate de añadir las dependencias en tu `build.gradle.kts`:
```kotlin
// Dependencias Room (KMP)
implementation("androidx.room:room-runtime:2.6.1")
ksp("androidx.room:room-compiler:2.6.1")
```
