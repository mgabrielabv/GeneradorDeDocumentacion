# Documentacion de Clase: Persona

## Resumen

| Campo | Valor |
| :--- | :--- |
| **Nombre** | Persona |
| **Autor** | Maria Bermudez |
| **Version** | 1.0 |
| **Herencia** | Entidad |

**Descripcion:** Entidad para el manejo de usuarios.

## Atributos

| Nombre Documentado | Tipo | Modificadores | Descripcion |
| :--- | :--- | :--- | :--- |
| **Nombre Completo** | `String` | `private` | Nombre unico del usuario |
| **Edad Cronologica** | `int` | `private` | Edad en años |

## Metodos y Operaciones

### Constructor Principal
| Propiedad | Detalle |
| :--- | :--- |
| **Firma Tecnica** | `com.MariaBermudez.Persona(int arg0, String arg1, int arg2)` |
| **Retorno** | `void` |
| **Modificadores** | `public` |
| **Descripcion** | Crea una persona vinculada a una entidad base |

---

### Obtener Nombre
| Propiedad | Detalle |
| :--- | :--- |
| **Firma Tecnica** | `getName()` |
| **Retorno** | `String` |
| **Modificadores** | `public` |
| **Descripcion** | Retorna el nombre del usuario |
| **Tipo** | Getter  |

---

### Obtener ID Personalizado
| Propiedad | Detalle |
| :--- | :--- |
| **Firma Tecnica** | `getId()` |
| **Retorno** | `int` |
| **Modificadores** | `public` |
| **Descripcion** | Retorna el ID heredado pero con logica propia |
| **Tipo** | Getter Sobrescrito  |

---

### Actualizar Edad
| Propiedad | Detalle |
| :--- | :--- |
| **Firma Tecnica** | `setAge(int arg0)` |
| **Retorno** | `void` |
| **Modificadores** | `public` |
| **Descripcion** | Modifica la edad del usuario |
| **Tipo** | Setter  |

---

