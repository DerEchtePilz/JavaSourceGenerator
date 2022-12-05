package io.github.derechtepilz.javasourcegenerator.dsl

import io.github.derechtepilz.javasourcegenerator.ClassBuilder
import io.github.derechtepilz.javasourcegenerator.Visibility
import io.github.derechtepilz.javasourcegenerator.fields.FieldBuilder

inline fun classBuilder(className: String, visibility: Visibility, block: ClassBuilder.() -> Unit = {}): Unit = ClassBuilder(className, visibility).apply(block).generate()

fun ClassBuilder.constructor(generateConstructor: Boolean, visibility: Visibility, onlyIncludeFinals: Boolean): ClassBuilder = generateConstructor(generateConstructor, visibility, onlyIncludeFinals)
fun ClassBuilder.field(fieldBuilder: FieldBuilder.() -> Unit): ClassBuilder = addField(FieldBuilder().apply(fieldBuilder))

/**
 * Sets the visibility and the name of the current [FieldBuilder]
 *
 * @param visibility The visibility of this [FieldBuilder]
 * @param name The name of this [FieldBuilder]
 */
fun FieldBuilder.buildWith(visibility: Visibility, name: String): FieldBuilder {
	this.visibility = visibility
	this.name = name
	return this
}

fun FieldBuilder.type(type: Class<*>): FieldBuilder = setType(type)
fun FieldBuilder.typeParameter(typeParameter: Char): FieldBuilder = setTypeParameter(typeParameter)
fun FieldBuilder.final(): FieldBuilder = setFinal()
fun FieldBuilder.static(): FieldBuilder = setStatic()
fun FieldBuilder.initialized(): FieldBuilder = setInitialized()
fun FieldBuilder.getter(): FieldBuilder = generateGetter()
fun FieldBuilder.setter(): FieldBuilder = generateSetter()