package AUT.utilities;

//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//
//public @interface CustomAnnotations {
//    @Retention(RetentionPolicy.RUNTIME)
//    public @interface TestCaseId {
//        int value();
//    }
//}
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestCaseId {
    int value();
}
