package pro.bolshakov.resumestorage.model;

import java.util.ArrayList;
import java.util.List;

public class SectionList extends Section{

    List<String> list = new ArrayList<>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String getViewText() {
        StringBuilder stringBuilder = new StringBuilder("");
        for(String str: list){
            if(stringBuilder.length() != 0) {
                stringBuilder.append("\n\r");
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
