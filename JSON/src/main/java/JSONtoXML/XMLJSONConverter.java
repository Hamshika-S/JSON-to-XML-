package JSONtoXML;
import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;

public class XMLJSONConverter implements XMLJSONConverterI {
    @Override
    public void convertJSONtoXML(File jsonFile, File xmlFile) throws Exception{
        ObjectMapper m = new ObjectMapper();
        JsonNode root = m.readTree(jsonFile);

        BufferedWriter w = new BufferedWriter(new FileWriter(xmlFile));

        writeNode(root,w,null);

        w.close();
    }

    private void writeNode(JsonNode node, BufferedWriter w, String name) throws IOException {
        if(node.isObject()){
            w.write("<object>");
            w.newLine();

            Iterator<Map.Entry<String,JsonNode>> fields = node.fields();
            while(fields.hasNext()){
                Map.Entry<String,JsonNode> entry = fields.next();
                //recursion
                writeNode(entry.getValue(),w,entry.getKey());
            }

            w.write("</object>");
            w.newLine();
        }

        else if(node.isArray()){
            w.write("<array>");
            w.newLine();

            for(JsonNode element:node){
                writeNode(element,w,null);
            }

            w.write("</array>");
            w.newLine();
        }
        else if(node.isTextual()){
            w.write("<string ");
            if(name!=null) w.write("name= \""+name+"\"");
            w.write("> "+node.asText()+" </string>");
            w.newLine();
        }

        else if(node.isNumber()){
            w.write("<number ");
            if(name!=null) w.write("name= \""+name+"\"");
            w.write("> "+node.asText()+" </number>");
            w.newLine();
        }

        else if (node.isBoolean()){
            w.write("<boolean ");
            if(name!=null) w.write("name= \""+name+"\"");
            w.write("> "+node.asText()+" <number>");
            w.newLine();
        }

        else if(node.isNull()){
            w.write("<null/>");
            w.newLine();
        }
    }
}
