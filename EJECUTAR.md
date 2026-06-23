# Ejecutar FrutiZap - Guía Rápida

## Requisitos previos ✓
- JDK 21 (Liberica): `C:\Program Files\BellSoft\LibericaJDK-21-Full`
- Gradle 8.9: `C:\Users\YENNI\Downloads\FrutiZap-main\gradle-8.9-bin\gradle-8.9`

## Opción 1: PowerShell (Recomendado)

```powershell
cd C:\Users\YENNI\Downloads\FrutiZap-main\FrutiZap-main

# Limpiar y compilar
.\run.ps1 clean
.\run.ps1 build

# Ejecutar pruebas
.\run.ps1 test

# Ejecutar aplicación
.\run.ps1 run
```

## Opción 2: CMD (Símbolo del Sistema)

```batch
cd C:\Users\YENNI\Downloads\FrutiZap-main\FrutiZap-main

REM Limpiar y compilar
run.bat clean
run.bat build

REM Ejecutar pruebas
run.bat test

REM Ejecutar aplicación
run.bat run
```

## Opción 3: Directo (sin script)

```batch
C:\Users\YENNI\Downloads\FrutiZap-main\gradle-8.9-bin\gradle-8.9\bin\gradle.bat --no-daemon clean build test
```

## Tareas disponibles

| Tarea | Descripción |
|-------|------------|
| `clean` | Limpia archivos compilados |
| `build` | Compila el proyecto |
| `test` | Ejecuta todas las pruebas (JUnit 5) |
| `run` | Ejecuta la aplicación JavaFX |
| `compileJava` | Solo compila código Java |
| `compileTestJava` | Solo compila pruebas |

## Salida esperada

### Test exitoso (✓)
```
BUILD SUCCESSFUL
...
> Task :test
...
4 tests completed, 0 failed
```

### Build exitoso (✓)
```
BUILD SUCCESSFUL in Xs
```

### Errores comunes

**Error: Java version incompatible**
- Asegúrate de usar JDK 21
- Verifica: `java -version`

**Error: Gradle not found**
- Valida que Gradle 8.9 existe en la ruta indicada

**Error: Task not found**
- Usa solo tareas válidas (clean, build, test, run)
