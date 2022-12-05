package io.github.derechtepilz.javasourcegenerator

import io.github.derechtepilz.javasourcegenerator.fields.GeneratedField
import java.io.BufferedWriter
import java.io.FileWriter

class Generator {
	fun generate() {
		val writer = BufferedWriter(FileWriter("code.txt"))
		val methodBodyIndents: Map<String, Int> = HashMap()
		// val builder = Builder()
		// val generatedClass = GeneratedClass("TestClass", Visibility.PUBLIC, true)
		// val body = ClassBody(generatedClass)
		// val generatedField = GeneratedField(Visibility.PUBLIC, Array<String>::class.java, "names", false, false, false, true, true)
		// body.addFields(generatedField)
		// val method: Method = builder.buildMethod("calculateNumbers", Int::class.javaPrimitiveType, true, false, false).addParameter(Parameter(String::class.java, "name", false)).addParameter(Parameter(Int::class.javaPrimitiveType, "valueOne", false)).addParameter(Parameter(Int::class.javaPrimitiveType, "valueTwo", false))
		// body.addMethods(method)
		// writer.write(body.buildClass())
		writer.close()
		/*
		// Generate class
		GeneratedClass clazz = new GeneratedClass("TestClass", Visibility.PUBLIC);
		clazz.addTypeParameter(new TypeParameter('T'));

		int indents = Integer.parseInt(clazz.buildClass()[0]);
		String classSignature = clazz.buildClass()[1];
		writer.write(classSignature);
		writer.write("\n");

		// Generate fields
		GeneratedField generatedField = new GeneratedField(Visibility.PUBLIC, String[].class, "names", false, false, false, indents);
		writer.write(generatedField.buildField());
		writer.write("\n\n");

		// Generate method
		Method method = builder.buildMethod("calculateNumbers", Integer.class, true, false, false, indents)
			.addParameter(new Parameter<>(String.class, "name", false))
			.addParameter(new Parameter<>(int.class, "valueOne", false))
			.addParameter(new Parameter<>(int.class, "valueTwo", false));

		writer.write(method.build());
		writer.write("\n");

		methodBodyIndents.put(method.getMethodSignature(), method.getMethodBodyIndents());
		System.out.println(methodBodyIndents);

		writer.write("\n}");
		writer.close();
		 */
	}

	companion object {
		@JvmStatic
		fun generateReturnType(returnType: Class<*>): String {
			val packageName = returnType.canonicalName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
			return packageName[packageName.size - 1]
		}
	}
}