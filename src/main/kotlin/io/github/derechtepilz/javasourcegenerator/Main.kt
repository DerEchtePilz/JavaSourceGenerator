package io.github.derechtepilz.javasourcegenerator

import io.github.derechtepilz.javasourcegenerator.dsl.*
import io.github.derechtepilz.javasourcegenerator.fields.FieldBuilder

fun main() {
	ClassBuilder("TestClass", Visibility.PUBLIC)
		.addField(
			FieldBuilder(Visibility.PUBLIC, "names")
				.setType(Array<String>::class.java)
				.generateGetter()
				.generateSetter()
		)
		.addField(
			FieldBuilder(Visibility.PRIVATE, "password")
				.generateGetter()
				.setTypeParameter('T')
				.setFinal()
		)
		.addField(
			FieldBuilder(Visibility.PROTECTED, "color")
				.generateGetter()
				.generateSetter()
				.setTypeParameter('C')
		)
		.generateConstructor(true, Visibility.PUBLIC, false)
		.generate()

	classBuilder("TestClassDSL", Visibility.PUBLIC) {
		field {
			buildWith(Visibility.PUBLIC, "names")
			getter()
			setter()
			type(Array<String>::class.java)
		}
		field {
			buildWith(Visibility.PRIVATE, "password")
			typeParameter('T')
			final()
			getter()
		}
		field {
			buildWith(Visibility.PROTECTED, "color")
			getter()
			setter()
			typeParameter('C')
		}
		constructor(true, Visibility.PUBLIC, false)
	}
}