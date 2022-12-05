package io.github.derechtepilz.javasourcegenerator.classes

import io.github.derechtepilz.javasourcegenerator.Visibility
import io.github.derechtepilz.javasourcegenerator.fields.GeneratedField

class GeneratedConstructor(private val onlyIncludeFinals: Boolean, vararg fields: GeneratedField) {

	private val fields: List<GeneratedField>

	init {
		this.fields = listOf(*fields)
	}

	fun buildConstructor(visibility: Visibility, className: String, indents: Int): String {
		var indents = indents
		val buildConstructor = StringBuilder()
		when (visibility) {
			Visibility.PUBLIC -> buildConstructor.append("\t".repeat(0.coerceAtLeast(indents))).append("public ")
			Visibility.PRIVATE -> buildConstructor.append("\t".repeat(0.coerceAtLeast(indents))).append("private ")
			Visibility.PROTECTED -> buildConstructor.append("\t".repeat(0.coerceAtLeast(indents))).append("protected ")
			Visibility.PACKAGE_PRIVATE -> buildConstructor.append("")
		}
		buildConstructor.append(className).append("(")

		val finalFields: MutableList<GeneratedField> = mutableListOf()
		if (onlyIncludeFinals) {
			for (generatedField in fields) {
				if (generatedField.isFinal()) {
					finalFields.add(generatedField)
				}
			}
			for (generatedField in finalFields) {
				buildConstructor.append(generatedField.getType()).append(" ").append(generatedField.getName())
				if (finalFields.indexOf(generatedField) < finalFields.size - 1) {
					buildConstructor.append(", ")
				}
			}
		} else {
			for (generatedField in fields) {
				buildConstructor.append(generatedField.getType()).append(" ").append(generatedField.getName())
				if (fields.indexOf(generatedField) < fields.size - 1) {
					buildConstructor.append(", ")
				}
			}
		}
		buildConstructor.append(") {\n")
		indents++
		if (onlyIncludeFinals) {
			for (generatedField in finalFields) {
				buildConstructor.append("\t".repeat(0.coerceAtLeast(indents))).append("this.").append(generatedField.getName()).append(" = ").append(generatedField.getName()).append(";\n")
			}
		} else {
			for (generatedField in fields) {
				buildConstructor.append("\t".repeat(0.coerceAtLeast(indents))).append("this.").append(generatedField.getName()).append(" = ").append(generatedField.getName()).append(";\n")
			}
		}
		indents--
		buildConstructor.append("\t".repeat(0.coerceAtLeast(indents))).append("}")
		return buildConstructor.toString()
	}
}