package JSONtoXML;
import java.io.File;

 class Main {
    public static void main(String[] args) throws Exception{
        if(args.length !=2){
            System.out.print("Give 2 files : json xml ");
            return;
        }

        File jsonFile = new File(args[0]);
        File xmlFile = new File (args[1]);

        XMLJSONConverterI c= convertorFactory.createJSONConverter();
        c.convertJSONtoXML(jsonFile, xmlFile);
    }
}
