package uk.co.developmentanddinosaurs.games.dinner.carnivore

import uk.co.developmentanddinosaurs.games.dinner.CraftyCodeCarnivore
import java.io.File
import java.io.FileFilter
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.JarFile

/**
 * Class that loads the [CraftyCodeCarnivore]s in the execution directory.
 *
 * This CarnivoreLoader is designed to load any [CraftyCodeCarnivore] classes that are present in the execution
 * directory where we're running the game. These classes need to be packaged up in a Jar file and present in the
 * `carnivores` directory.
 *
 * This should return the first implementation of a [CraftyCodeCarnivore] that it finds per jar.
 */
class CarnivoreLoader(private val directory: File) {
    private val fairCarnivores = listOf(
        FairCarnivore(),
        FairCarnivore(),
        FairCarnivore(),
        FairCarnivore(),
        FairCarnivore(),
        FairCarnivore()
    )

    /**
     * Loads the [CraftyCodeCarnivore] classes from the expected directory.
     *
     * @return the list of [CraftyCodeCarnivore]s found
     */
    fun loadCarnivores(): List<CraftyCodeCarnivore> {
        val carnivoreJars = File(directory, "carnivores").listFiles(FileFilter { it.extension == "jar" })
        if (carnivoreJars == null || carnivoreJars.isEmpty()) {
            return fairCarnivores
        }
        return carnivoreJars.map { jar ->
            val urls = arrayOf(URL("jar:file:${jar.path}!/"))
            val classLoader = URLClassLoader.newInstance(urls)
            JarFile(jar).entries().asSequence()
                .filter { !it.isDirectory && it.name.endsWith(".class") }
                .map { entry ->
                    val className = entry.name.substringBefore(".class").replace("/", ".")
                    classLoader.loadClass(className)
                }
                .filter { clazz ->
                    clazz.interfaces.contains(CraftyCodeCarnivore::class.java)
                }
                .map { clazz ->
                    val declaredConstructor = clazz.getDeclaredConstructor()
                    val newInstance = declaredConstructor.newInstance()
                    newInstance as CraftyCodeCarnivore
                }
                .first()
        }
    }
}
