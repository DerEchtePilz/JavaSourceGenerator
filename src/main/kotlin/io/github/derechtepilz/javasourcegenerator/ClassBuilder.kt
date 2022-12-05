package io.github.derechtepilz.javasourcegenerator

import io.github.derechtepilz.javasourcegenerator.classes.GeneratedConstructor
import io.github.derechtepilz.javasourcegenerator.fields.FieldBuilder
import io.github.derechtepilz.javasourcegenerator.fields.GeneratedField
import java.io.BufferedWriter
import java.io.FileWriter

class ClassBuilder(private val className: String, private val visibility: Visibility) {

	private var isParameterized: Boolean = false
	private var typeParameters: MutableList<Char> = mutableListOf()
	private var typeParameterString = ""

	private var generateConstructor: Boolean = false
	private var constructorVisibility: Visibility = Visibility.PUBLIC
	private var onlyIncludeFinals: Boolean = false

	private val generatedFields: MutableList<GeneratedField> = mutableListOf()

	private val fieldsWithGetter: MutableList<GeneratedField> = mutableListOf()
	private val fieldsWithSetter: MutableList<GeneratedField> = mutableListOf()

	fun generateConstructor(generateConstructor: Boolean, visibility: Visibility, onlyIncludeFinals: Boolean): ClassBuilder {
		this.generateConstructor = generateConstructor
		this.constructorVisibility = visibility
		this.onlyIncludeFinals = onlyIncludeFinals
		return this
	}

	fun addField(fieldBuilder: FieldBuilder): ClassBuilder {
		if (fieldBuilder.hasTypeParameter()) {
			this.isParameterized = true
		}
		val generatedField: GeneratedField = fieldBuilder.buildField()
		if (fieldBuilder.hasTypeParameter()) {
			if (!typeParameters.contains(generatedField.getTypeParameter()!!)) {
				this.typeParameters.add(generatedField.getTypeParameter()!!)
			}
		}
		this.generatedFields.add(generatedField)
		return this
	}

	fun addMethod(): ClassBuilder {
		return this
	}

	fun generate() {
		val writer = BufferedWriter(FileWriter("code.txt"))

		var indents = 0
		val buildClass: StringBuilder = StringBuilder()
		when (visibility) {
			Visibility.PUBLIC -> buildClass.append("public ")
			Visibility.PACKAGE_PRIVATE -> buildClass.append("")
			Visibility.PROTECTED -> throw IllegalArgumentException("Modifier 'protected' not allowed here!")
			Visibility.PRIVATE -> throw IllegalArgumentException("Modifier 'private' not allowed here!")
		}
		buildClass.append(className)
		// Write class type parameters
		if (isParameterized) {
			val typeParameterStart = buildClass.length
			buildClass.append("<")
			for (typeParameter: Char in typeParameters) {
				buildClass.append(typeParameter)
				if (typeParameters.indexOf(typeParameter) < typeParameters.size - 1) {
					buildClass.append(", ")
				}
			}
			buildClass.append(">")
			val typeParameterEnd = buildClass.length

			typeParameterString = buildClass.toString().subSequence(typeParameterStart, typeParameterEnd) as String
		}

		buildClass.append(" ").append("{\n\n")
		indents++

		// Write fields
		for (field: GeneratedField in generatedFields) {
			buildClass.append("\t".repeat(0.coerceAtLeast(indents))).append(field.buildField()).append("\n")
			if (field.isGenerateGetter) {
				fieldsWithGetter.add(field)
			}
			if (field.isGenerateSetter) {
				fieldsWithSetter.add(field)
			}
		}
		if (generatedFields.size >= 1) {
			buildClass.append("\n")
		}

		// Write constructor
		if (generateConstructor) {
			buildClass.append(GeneratedConstructor(onlyIncludeFinals, *generatedFields.toTypedArray()).buildConstructor(constructorVisibility, className, indents)).append("\n")
			buildClass.append("\n")
		}

		// Write methods

		// Write getters
		for (field: GeneratedField in fieldsWithGetter) {
			buildClass.append(field.generateGetter(indents)).append("\n\n")
		}

		// Write setters
		for (field: GeneratedField in fieldsWithSetter) {
			if (field.isFinal()) throw GetterSetterException("Cannot generate setter for field '${field.getName()}' as it has been declared as final!")
			buildClass.append(field.generateSetter(indents)).append("\n\n")
		}

		// Finish class
		indents--
		buildClass.append("}")
		writer.write(buildClass.toString())
		writer.close()
	}

}