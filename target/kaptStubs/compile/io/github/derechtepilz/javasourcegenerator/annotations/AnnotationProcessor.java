package io.github.derechtepilz.javasourcegenerator.annotations;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016\u00a8\u0006\n"}, d2 = {"Lio/github/derechtepilz/javasourcegenerator/annotations/AnnotationProcessor;", "Ljavax/annotation/processing/AbstractProcessor;", "()V", "process", "", "annotations", "", "Ljavax/lang/model/element/TypeElement;", "roundEnv", "Ljavax/annotation/processing/RoundEnvironment;", "javasourcegenerator"})
@com.google.auto.service.AutoService(value = javax.annotation.processing.Processor.class)
@javax.annotation.processing.SupportedSourceVersion(value = javax.lang.model.SourceVersion.RELEASE_16)
@javax.annotation.processing.SupportedAnnotationTypes(value = {"io.github.derechtepilz.javasourcegenerator.annotations.Unimplemented"})
public final class AnnotationProcessor extends javax.annotation.processing.AbstractProcessor {
    
    public AnnotationProcessor() {
        super();
    }
    
    @java.lang.Override
    public boolean process(@org.jetbrains.annotations.NotNull
    java.util.Set<? extends javax.lang.model.element.TypeElement> annotations, @org.jetbrains.annotations.NotNull
    javax.annotation.processing.RoundEnvironment roundEnv) {
        return false;
    }
}