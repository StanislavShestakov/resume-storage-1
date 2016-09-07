package pro.bolshakov.resumestorage.model;

public class SectionText extends Section{

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getViewText() {
        return text;
    }
}
