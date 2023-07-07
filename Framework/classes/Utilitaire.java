package utilitaire;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import annotation.AnnotationEmp;
import etu1851.framework.Mapping;

public class Utilitaire {
    public String getAnnotation(String url) {
        String[] Tab = url.split("/");

        if (Tab.length > 0) {
            return Tab[Tab.length - 1];

        } else {
            return "";
        }

    }

    // Methode qui parcours tous les classe dans le framework et liste les Hashmap
    public HashMap<String, Mapping> listeHashMapAllClass(String pathProjet) throws ClassNotFoundException {
        Vector<String> allClasse = listeClasse(pathProjet);
        HashMap<String, Mapping> newmap = new HashMap<String, Mapping>();
        String key = "";
        for (int i = 0; i < allClasse.size(); i++) {
            HashMap<String, Mapping> map = listemethode(allClasse.get(i));
            Set<String> keys = map.keySet();
            for (String j : keys) {
                newmap.put(j, map.get(j));
            }
        }
        return newmap;
    }

    // la liste des methodes
    public HashMap<String, Mapping> listemethode(String nomClasse) throws ClassNotFoundException {
        Class classe = Class.forName(nomClasse);
        Method[] listemethode = classe.getDeclaredMethods();
        String annotation = "AnnotationEmp";
        HashMap<String, Mapping> map = new HashMap<String, Mapping>();
        for (int i = 0; i < listemethode.length; i++) {
            if (listemethode[i].isAnnotationPresent(AnnotationEmp.class)) {
                AnnotationEmp an = listemethode[i].getAnnotation(AnnotationEmp.class);
                Mapping mapping = new Mapping(classe.getName(), listemethode[i].getName());
                map.put(an.value(), mapping);
            }
        }
        return map;
    }

    // liste des classes
    public Vector<String> listeClasse(String pathProjet) {
        String path = pathProjet + "\\WEB-INF\\classes";
        File folder = new File(path);
        File[] listedossier = folder.listFiles();
        Vector<String> enregistrement = new Vector<String>();
        for (File file : listedossier) {
            if (file.isDirectory()) { // si file est un dossier
                Vector<String> mini = new Vector<String>();
                getsousdossier(path, file.getName(), mini);
                enregistrement.addAll(mini);
            }
        }
        return enregistrement;
    }

    // Avoir les dossiers fils dans un dossier parent
    public void getsousdossier(String pathProjet, String pack, Vector<String> tableau) {
        String[] noslash = pack.split("\\.");
        File folder = new File(pathProjet + "\\" + noslash[noslash.length - 1]);
        File[] listedossier = folder.listFiles();
        String enrepack = pack;
        String newPath = "";
        String fileName = "";
        for (File file : listedossier) {
            if (file.isDirectory()) {
                enrepack = enrepack + "." + file.getName();
                newPath = pathProjet + "\\" + pack;
                getsousdossier(newPath, enrepack, tableau);
            } else {
                fileName = file.getName();
                enrepack = enrepack + "." + fileName.substring(0, fileName.lastIndexOf('.'));
                tableau.add(enrepack);
            }
            enrepack = pack;
        }
    }

}
