package pro.bolshakov.resumestorage.storage.serializer;

import pro.bolshakov.resumestorage.model.Resume;
import pro.bolshakov.resumestorage.util.JsonParser;

import java.io.*;

public class JsonStreamSerializer implements StreamSerializer{
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try(Writer writer = new OutputStreamWriter(os)){
            JsonParser.write(r, writer);
        }

    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is)){
            return JsonParser.read(reader, Resume.class);
        }
    }
}
