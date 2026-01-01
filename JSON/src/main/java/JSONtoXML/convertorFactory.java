package JSONtoXML;

public class convertorFactory {
    public static XMLJSONConverterI createJSONConverter(){
        return new XMLJSONConverter();
    }
}
