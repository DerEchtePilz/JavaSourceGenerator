package io.github.derechtepilz.javasourcegenerator.fields

import io.github.derechtepilz.javasourcegenerator.Visibility

class FieldBuilder(var visibility: Visibility = Visibility.PUBLIC, var name: String = "dummy") {

	private var type: Class<*> = String::class.java
	private var typeParameter: Char? = null
	private var isFinal: Boolean = false
	private var isStatic: Boolean = false
	private var isInitialized: Boolean = false
	private var generateGetter: Boolean = false
	private var generateSetter: Boolean = false

	fun setType(type: Class<*>): FieldBuilder {
		this.type = type
		return this
	}

	fun setTypeParameter(typeParameter: Char): FieldBuilder {
		this.typeParameter = typeParameter
		return this
	}

	fun setFinal(): FieldBuilder {
		this.isFinal = true
		return this
	}

	fun setStatic(): FieldBuilder {
		this.isStatic = true
		return this
	}

	fun setInitialized(): FieldBuilder {
		this.isInitialized = true
		return this
	}

	fun generateGetter(): FieldBuilder {
		this.generateGetter = true
		return this
	}

	fun generateSetter(): FieldBuilder {
		this.generateSetter = true
		return this
	}

	fun buildField(): GeneratedField {
		return GeneratedField(visibility, type, name, isFinal, isStatic, isInitialized, typeParameter, generateGetter, generateSetter)
	}

	fun hasTypeParameter(): Boolean {
		return typeParameter != null
	}

}