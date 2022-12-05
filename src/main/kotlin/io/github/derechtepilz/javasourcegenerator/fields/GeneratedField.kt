package io.github.derechtepilz.javasourcegenerator.fields

import io.github.derechtepilz.javasourcegenerator.Generator
import io.github.derechtepilz.javasourcegenerator.Visibility
import java.util.*

class GeneratedField(private val visibility: Visibility, private val type: Class<*>, private val name: String, private val isFinal: Boolean, private val isStatic: Boolean, private val isInitialized: Boolean, private val typeParameter: Char?, val isGenerateGetter: Boolean, val isGenerateSetter: Boolean) {

	private var value: Class<*>? = null

	fun setValue(fieldValue: Class<*>?) {
		value = fieldValue
	}

	fun buildField(): String {
		require(!(isInitialized && value == null)) { "The field '$name' has been set as an initialized field, but a value has not been found! Please use the 'setValue' method to set a value!" }
		val builder = StringBuilder()
		when (visibility) {
			Visibility.PUBLIC -> builder.append("public ")
			Visibility.PRIVATE -> builder.append("private ")
			Visibility.PROTECTED -> builder.append("protected ")
			Visibility.PACKAGE_PRIVATE -> {}
		}
		if (isFinal) {
			builder.append("final ")
		}
		if (isStatic) {
			builder.append("static ")
		}
		val returnType: String = typeParameter?.toString() ?: Generator.generateReturnType(type)
		builder.append(returnType)

		builder.append(" ").append(name)
		if (!isInitialized) {
			builder.append(";")
		}
		return builder.toString()
	}

	fun getType(): String {
		return typeParameter?.toString() ?: Generator.generateReturnType(type)
	}

	fun getName(): String {
		return name
	}

	fun getTypeParameter(): Char? {
		return typeParameter
	}

	fun isFinal(): Boolean {
		return isFinal
	}

	fun generateGetter(indents: Int): String {
		var indents = indents
		val buildGetter = StringBuilder()
		buildGetter.append("\t".repeat(0.coerceAtLeast(indents))).append("public ").append(getType()) // public <ReturnType>
		buildGetter.append(" ").append("get").append(name.replaceFirst(name[0].toString(), name[0].toString().uppercase(Locale.getDefault()))).append("() {\n") // public <ReturnType> get<variableName>() {
		indents++
		buildGetter.append("\t".repeat(0.coerceAtLeast(indents))).append("return ").append(name).append(";\n")
		indents--
		buildGetter.append("\t".repeat(0.coerceAtLeast(indents))).append("}")
		return buildGetter.toString()
	}

	fun generateSetter(indents: Int): String {
		var indents = indents
		val buildGetter = StringBuilder()
		buildGetter.append("\t".repeat(0.coerceAtLeast(indents))).append("public void") // public void
		buildGetter.append(" ").append("set").append(name.replaceFirst(name[0].toString(), name[0].toString().uppercase())).append("(") // public void set<variableName>(
		buildGetter.append(getType()).append(" ").append(name).append(") {\n")
		indents++
		buildGetter.append("\t".repeat(0.coerceAtLeast(indents))).append("this.").append(name).append(" = ").append(name).append(";\n")
		indents--
		buildGetter.append("\t".repeat(0.coerceAtLeast(indents))).append("}")
		return buildGetter.toString()
	}
}