package emp;
import annotation.AnnotationEmp;
public class Emp {
    Emp emp;
   @AnnotationEmp(value = "Emp") 
   public Emp getEmp() {
        return emp;
   }
}
