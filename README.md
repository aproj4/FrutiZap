
# FrutiZap — Manual de usuario (instalación y uso desde cero)

Bienvenido a FrutiZap. Este documento explica, paso a paso y con lenguaje sencillo, cómo descargar, instalar, ejecutar y probar el juego en un equipo con Windows. Está pensado para personas sin experiencia técnica.

**Contenido**
- Descripción del proyecto
- Requisitos del sistema
- Instalación
- Ejecución del proyecto
- Ejecución de pruebas (JUnit)
- Uso del juego
- Problemas comunes y soluciones
- Estructura del proyecto

---

**1. Descripción del proyecto**

- ¿Qué es FrutiZap?
  FrutiZap es un juego educativo en Java para aprender memoria y conceptos básicos de fracciones usando frutas como elementos visuales.

- Objetivo educativo:
  - Desarrollar la memoria visual y la capacidad de emparejar objetos.
  - Introducir nociones de fracciones (ej.: mitad, cuarto) con representaciones visuales.

- Breve explicación de las dos fases:
  - Fase 1 — Memoria: el jugador ve un tablero de tarjetas ocultas. Al elegir dos, se muestran las frutas. Si coinciden, se quedan descubiertas.
  - Fase 2 — Fracciones: tras completar la memoria, aparecen preguntas visuales sobre fracciones (por ejemplo, "1/2" representado sobre una fruta) y varias opciones en botones.

---

**2. Requisitos del sistema**

- Java: se recomienda usar Java 17 o Java 21 (OpenJDK / Liberica / Temurin). Asegúrate de instalar la versión y recordar su ubicación.
- Sistema operativo: Windows (las instrucciones están orientadas a Windows). El proyecto también puede funcionar en Linux/Mac si se adaptan los comandos de terminal.
- Editor (opcional): Visual Studio Code (recomendado para abrir el proyecto y editar). No es necesario para jugar o ejecutar pruebas.

---

**3. Instalación**

- Descargar o copiar el proyecto:
  1. Descarga el ZIP del repositorio o copia la carpeta del proyecto a tu equipo.
  2. Coloca la carpeta en una ruta fácil de encontrar, por ejemplo `C:\Users\TuUsuario\Downloads\FrutiZap`.

- Abrir en Visual Studio Code (opcional):
  1. Abre Visual Studio Code.
  2. Selecciona "File → Open Folder" y elige la carpeta del proyecto.
  3. Si VS Code pregunta por extensiones recomendadas, puedes instalarlas (Extension Pack for Java).

- Verificar que Java está instalado:
  1. Abre una terminal (PowerShell) y escribe:

  ```powershell
  java -version
  ```

  2. Deberías ver una línea que indique la versión (por ejemplo, `openjdk version "21"` o `17`). Si no aparece, instala Java (por ejemplo, Liberica o Temurin) y vuelve a intentarlo.

- Verificar Gradle (wrapper):
  1. El proyecto puede incluir un Gradle local o un `gradlew` (wrapper). Para usar el wrapper desde PowerShell en Windows ejecuta:

  ```powershell
  .\gradlew --version
  ```

  2. Si no hay `gradlew`, puedes usar una instalación de Gradle del sistema (si ya la instalaste) o usar el script `run.bat` incluido que apunta a una copia local de Gradle.

---

**4. Ejecución del proyecto**

- Abrir la terminal en la carpeta raíz del proyecto:
  1. En el Explorador de Windows, entra en la carpeta del proyecto.
  2. Haz clic en la barra de direcciones y escribe `powershell`, luego presiona Enter. Se abrirá PowerShell en la carpeta correcta.

- Comandos básicos (PowerShell en Windows):

  - Compilar y ejecutar pruebas (limpio):

  ```powershell
  .\gradlew clean build
  ```

    - Explicación: `clean` borra artefactos anteriores; `build` compila el código y ejecuta las pruebas. Es el comando que asegura que todo compila correctamente.

  - Ejecutar la aplicación (lanzar el juego):

  ```powershell
  .\gradlew run
  ```

    - Explicación: `run` compila y arranca la aplicación mostrando la interfaz gráfica (JavaFX).

  - Alternativa si el proyecto incluye `run.bat` (script simplificado):

  ```powershell
  .\run.bat build
  .\run.bat run
  ```

    - `run.bat` es un script que invoca Gradle local incluido en la carpeta del proyecto.

---

**5. Ejecución de pruebas (JUnit)**

- Para ejecutar solo las pruebas unitarias usa:

```powershell
.\gradlew test
```

- Interpretación de resultados:
  - `BUILD SUCCESSFUL`: todas las pruebas y compilación completaron sin errores.
  - `BUILD FAILED` o errores en la salida: indica fallos de compilación o tests que fallaron. En la salida verás el nombre del test que falló y la causa.

Si una prueba falla, copia el texto del error y busca la clase mencionada en `src/test/java` para entender qué verifica esa prueba.

---

**6. Uso del juego (paso a paso)**

- Fase 1 — Memoria:
  1. Al iniciar el juego verás un tablero con varias tarjetas boca abajo.
  2. Haz clic en una tarjeta para voltearla y ver la fruta.
  3. Selecciona una segunda tarjeta.
  4. Si las dos tarjetas muestran la misma fruta, quedarán descubiertas y sumarás puntos.
  5. Si no coinciden, las tarjetas se volverán a ocultar y podrás intentar de nuevo.
  6. Continúa hasta descubrir todas las parejas; al terminar pasarás a la Fase 2.

- Fase 2 — Fracciones:
  1. Verás una representación visual de una fracción sobre una fruta y varias opciones (botones) con fracciones (por ejemplo `1/2`, `2/4`, `1/3`).
  2. Elige la opción que creas correcta tocando/clicando el botón con la fracción correcta.
  3. Si respondes bien: recibirás puntos y pasarás a la siguiente pregunta.
  4. Si respondes mal: normalmente el juego muestra si la respuesta fue incorrecta y puede ofrecer reintentos o mostrar la respuesta correcta (dependiendo de la configuración de la versión). Intenta de nuevo en la siguiente pregunta.

Consejo para niños: explica que `2/4` es lo mismo que `1/2` — son fracciones equivalentes.

---

**7. Problemas comunes y soluciones**

- Error al ejecutar `gradlew` en PowerShell: asegúrate de usar `.` y `\` en PowerShell, por ejemplo:

```powershell
.\gradlew clean build
```

- Error "JavaFX runtime missing" o ventanas que no abren:
  - Asegúrate de tener Java instalado (ver `java -version`).
  - Revisa que `build.gradle` tenga la sección `javafx { version = '21' modules = ['javafx.controls'] }`.
  - Si ves errores relacionados con JavaFX, instala una JDK que incluya soporte FX o agrega las librerías JavaFX correctamente.

- Errores de compilación:
  - Lee la salida de Gradle: te indicará el archivo y la línea con error.
  - Si el error es de prueba, el resultado indica la aserción que falló.

- Si el programa no abre o la interfaz no aparece:
  - Comprueba que no haya excepciones en la salida de la terminal.
  - Asegúrate de que la máquina tenga suficiente memoria y una JDK compatible.

Si necesitas ayuda, copia y pega la salida de la terminal y te iré guiando paso a paso.

---

**8. Estructura básica del proyecto**

La organización principal del código es la siguiente (carpeta `src/main/java`):

- `frutizap.model` — clases que representan los datos (por ejemplo `Fruta`, `FraccionPregunta`, `Tarjeta`).
- `frutizap.logic` — la lógica del juego (reglas, tablero, control de puntos, flujo de preguntas).
- `frutizap.ui` — interfaces gráficas (ventanas, vistas del juego, menús) implementadas con JavaFX.

Y las pruebas están en `src/test/java/frutizap`.

---

**9. Consejos finales**

- Guarda una copia del proyecto antes de hacer cambios.
- Si vas a ejecutar en otra máquina, instala Java 17 o 21 y, si es necesario, Gradle.

Gracias por usar FrutiZap. Si quieres, puedo añadir instrucciones específicas para Linux/Mac, un script para crear un `.jar` ejecutable o guía paso a paso para instalar Java en Windows.

# FrutiZap
Proyecto educativo desarrollado en Java y JavaFX para el aprendizaje de fracciones mediante actividades de memoria visual.
