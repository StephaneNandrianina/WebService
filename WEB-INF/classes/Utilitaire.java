package utilitaire;

public class Utilitaire {
     public String getAnnotation(String url) {
        String[] Tab = url.split("/");

        if (Tab.length> 0) {
            return Tab[Tab.length-1];

        }else{
            return "";
        }
        
    } 
 
}
