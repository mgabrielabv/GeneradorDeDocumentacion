# Entidad: Article

Archivo original: `src/article/article.entity.ts`

Descripción
- Entidad que representa un artículo (Article) con metadatos, contenido, etiquetas y relaciones con autor y comentarios.

Índice
- Propiedades
- Métodos

Propiedades

- id (number)
  - Tipo: number
  - Decoradores: `@PrimaryGeneratedColumn()`
  - Definido en: `src/article/article.entity.ts:9`
  - Descripción: Identificador primario autogenerado de la entidad.

- slug (string)
  - Tipo: string
  - Decoradores: `@Column()`
  - Definido en: `src/article/article.entity.ts:12`
  - Descripción: Slug único legible por humanos para la URL del artículo.

- title (string)
  - Tipo: string
  - Decoradores: `@Column()`
  - Definido en: `src/article/article.entity.ts:15`
  - Descripción: Título del artículo.

- description (string)
  - Tipo: string
  - Decoradores: `@Column({default: ''})`
  - Definido en: `src/article/article.entity.ts:18`
  - Descripción: Breve descripción o resumen del artículo (por defecto cadena vacía).

- body (string)
  - Tipo: string
  - Decoradores: `@Column({default: ''})`
  - Definido en: `src/article/article.entity.ts:21`
  - Descripción: Contenido completo del artículo (por defecto cadena vacía).

- created (Date)
  - Tipo: Date
  - Decoradores: `@Column({type: 'timestamp', default: undefined})`
  - Definido en: `src/article/article.entity.ts:24`
  - Descripción: Fecha de creación (timestamp).

- updated (Date)
  - Tipo: Date
  - Decoradores: `@Column({type: 'timestamp', default: undefined})`
  - Definido en: `src/article/article.entity.ts:27`
  - Descripción: Fecha de última actualización (timestamp).

- tagList (string[])
  - Tipo: string[]
  - Decoradores: `@Column('simple-array')`
  - Definido en: `src/article/article.entity.ts:35`
  - Descripción: Lista de etiquetas asociadas al artículo (almacenadas como "simple-array").

- author (UserEntity)
  - Tipo: UserEntity
  - Decoradores: `@ManyToOne(undefined, undefined)`
  - Definido en: `src/article/article.entity.ts:38`
  - Descripción: Relación ManyToOne hacia la entidad de usuario que es autor del artículo.

- comments (Comment[])
  - Tipo: Comment[]
  - Decoradores: `@OneToMany(undefined, undefined, {eager: undefined})`, `@JoinColumn()`
  - Definido en: `src/article/article.entity.ts:42`
  - Descripción: Relación OneToMany con la entidad Comentario; colección de comentarios del artículo.

- favoriteCount (number)
  - Tipo: number
  - Decoradores: `@Column({default: 0})`
  - Definido en: `src/article/article.entity.ts:45`
  - Descripción: Contador de favoritos/likes (por defecto 0).

Métodos

- updateTimestamp(): void
  - Decoradores: `@BeforeUpdate()`
  - Definido en: `src/article/article.entity.ts:30`
  - Descripción: Hook que se ejecuta antes de actualizar la entidad. Suele usarse para actualizar el campo `updated` con la fecha actual.

Notas y recomendaciones
- Los valores `default: undefined` en `created` y `updated` sugieren que el esquema espera que el motor de base de datos rellene o que el código establezca explícitamente estas fechas. Revisa la inicialización para evitar valores nulos si tu aplicación lo requiere.
- `@Column('simple-array')` almacena arrays como cadenas separadas por comas; para datos complejos o con comas en las etiquetas, considera usar una tabla relación o JSON según el soporte de la base de datos.
- Las relaciones (`@ManyToOne`, `@OneToMany`) aparecen con parámetros `undefined` en el esquema extraído; verifica los tipos genéricos y las opciones reales en el archivo fuente si necesitas más precisión (por ejemplo, la entidad objetivo o `cascade`, `eager`, etc.).

---
Generado automáticamente a partir del esquema provisto.
