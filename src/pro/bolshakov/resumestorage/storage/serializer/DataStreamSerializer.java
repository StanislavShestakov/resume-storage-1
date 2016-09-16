package pro.bolshakov.resumestorage.storage.serializer;

import pro.bolshakov.resumestorage.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer{

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(os)){
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            dos.writeInt(r.getContacts().size());
            for(Map.Entry<ContactType, String> entry: r.getContacts().entrySet()){
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            dos.writeInt(r.getSections().size());
            for (Map.Entry<SectionType,Section> entry: r.getSections().entrySet()){
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue().getClass().getName());
                if(entry.getValue() instanceof TextSection){
                    dos.writeUTF(((TextSection) entry.getValue()).getContent());
                }
                else if (entry.getValue() instanceof ListSection){
                    writeArray(((ListSection) entry.getValue()).getItems(), dos, DataOutputStream::writeUTF);
                }
                else if(entry.getValue() instanceof OrganizationSection){
                    OrganizationSection section = (OrganizationSection) entry.getValue();
                    writeArray(section.getOrganizations(), dos, new WritableObject<Organization>() {
                        @Override
                        public void writeDataStream(DataOutputStream dos, Organization org) throws IOException {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            writeArray(org.getPositions(), dos, new WritableObject<Organization.Position>() {
                                @Override
                                public void writeDataStream(DataOutputStream dos, Organization.Position obj) throws IOException {
                                    dos.writeUTF(obj.getStartDate().toString());
                                    dos.writeUTF(obj.getEndDate().toString());
                                    dos.writeUTF(obj.getTitle());
                                    dos.writeUTF(obj.getDescription());
                                }
                            });
                        }
                    });
                }
            }

        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try(DataInputStream dis = new DataInputStream(is)){
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int countContacts = dis.readInt();
            for (int i = 0; i < countContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int countSection = dis.readInt();
            for (int i = 0; i < countSection; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                String classSection = dis.readUTF();
                Section resSection = null;
                if(classSection.equals(TextSection.class.getName())){
                    resSection = new TextSection(dis.readUTF());
                }
                else if (classSection.equals(ListSection.class.getName())){
                    int count = dis.readInt();
                    ListSection section = new ListSection();
                    for (int j = 0; j < count; j++) {
                        section.getItems().add(dis.readUTF());
                    }
                    resSection = section;
                }
                else if (classSection.equals(OrganizationSection.class.getName())){
                    OrganizationSection section = new OrganizationSection();
                    int count = dis.readInt();
                    for (int j = 0; j < count; j++) {

                        String name = dis.readUTF();
                        String url = dis.readUTF();

                        Organization organization = new Organization(name, url);

                        int count2 = dis.readInt();
                        for (int k = 0; k < count2; k++) {
                            LocalDate startDate = LocalDate.parse(dis.readUTF());
                            LocalDate endDate = LocalDate.parse(dis.readUTF());
                            String title = dis.readUTF();
                            String desc = dis.readUTF();
                            organization.getPositions().add(new Organization.Position(startDate,endDate,title,desc));
                        }
                        section.getOrganizations().add(organization);
                    }
                    resSection = section;
                }

                resume.addSection(sectionType, resSection);
            }

            return resume;
        }
    }

    private <T> void writeArray(List<T> list, DataOutputStream dos, WritableObject<T> writableObject) throws IOException{
        dos.writeInt(list.size());
        for (T obj: list){
            writableObject.writeDataStream(dos, obj);
        }
    }

    interface WritableObject<T>{
        void writeDataStream(DataOutputStream dos, T obj) throws IOException;
    }


}
