package test.java.lang.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author sj
 * @Title MyMethodTestProcessor
 * @description TODO
 * @date 2021/3/8 18:52
 */
@SupportedAnnotationTypes("test.java.lang.annotation.MethodTest")
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class MyMethodTestProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("start annotation process .");
        for (TypeElement typeElement : annotations){
            System.out.println(typeElement+","+typeElement.getSimpleName());
        }
        System.out.println(roundEnv);
        return true;
    }
}
