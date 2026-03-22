package com.MariaBermudez.generador.annotations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DocClase {
    String nombre() default "";
    String autor() default "Desconocido";
    String descripcion();
    String version() default "1.0";
}