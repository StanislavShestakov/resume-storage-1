package pro.bolshakov.resumestorage.storage;

import org.junit.Before;
import org.junit.Test;
import pro.bolshakov.resumestorage.model.*;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractStorageTest {

    protected static final String STORAGE_DIR = "D:\\Develop\\Java\\resume-storage\\storage";

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    private static final ContactType CONTACT_TYPE_1 = ContactType.MAIL;
    private static final String CONTACT_1 = "mail@mail.ru";

    private static final ContactType CONTACT_TYPE_2 = ContactType.MOBILE;
    private static final String CONTACT_2 = "+78524442513";

    private static final ContactType CONTACT_TYPE_3 = ContactType.SKYPE;
    private static final String CONTACT_3 = "zombie";

    private static final ContactType CONTACT_TYPE_4 = ContactType.SKYPE;
    private static final String CONTACT_4 = "fisherman";

    private static final ContactType CONTACT_TYPE_5 = ContactType.SKYPE;
    private static final String CONTACT_5 = "lobster";

    private static final SectionType SECTION_TYPE_1 = SectionType.PERSONAL;
    private static final String SECTION_CONTENT_1 = "Personal section";
    private static final TextSection SECTION_1 = new TextSection(SECTION_CONTENT_1);

    private static final SectionType SECTION_TYPE_2 = SectionType.ACHIEVEMENT;
    private static final String ACHIEVEMENT_1 = "ACHIEVEMENT 1";
    private static final String ACHIEVEMENT_2 = "ACHIEVEMENT 2";
    private static final ListSection SECTION_2 = new ListSection(Arrays.asList(ACHIEVEMENT_1,ACHIEVEMENT_2));

    private static final SectionType SECTION_TYPE_3 = SectionType.EDUCATION;
    private static final LocalDate START_DATE_1 = LocalDate.of(2000, 1, 1);
    private static final LocalDate END_DATE_1 = LocalDate.of(2002, 1, 1);
    private static final String ORG_TITLE_1 = "Organization title 1";
    private static final String ORG_DESCRIPTION_1 = "Organization description 1";
    private static final LocalDate START_DATE_2 = LocalDate.of(2002, 1, 1);
    private static final LocalDate END_DATE_2 = LocalDate.of(2005, 6, 1);
    private static final String ORG_TITLE_2 = "Organization title 2";
    private static final String ORGANIZATION_NAME_1 = "Org 1";
    private static final String ORGANIZATION_URL_1 = "Org url 1";
    private static final Organization ORGANIZATION_1 = new Organization(
            ORGANIZATION_NAME_1,
            ORGANIZATION_URL_1,
            Arrays.asList(new Organization.Position(START_DATE_1,END_DATE_1,ORG_TITLE_1,ORG_DESCRIPTION_1),
                    new Organization.Position(START_DATE_2,END_DATE_2,ORG_TITLE_2,"")));

    private static final OrganizationSection SECTION_3 = new OrganizationSection(Collections.singletonList(ORGANIZATION_1));


    static {

        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_1.addContact(CONTACT_TYPE_1, CONTACT_1);
        RESUME_1.addContact(CONTACT_TYPE_5, CONTACT_5);

        RESUME_1.addSection(SECTION_TYPE_1, SECTION_1);
        RESUME_1.addSection(SECTION_TYPE_2, SECTION_2);
        RESUME_1.addSection(SECTION_TYPE_3, SECTION_3);

        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_2.addContact(CONTACT_TYPE_2, CONTACT_2);

        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_3.addContact(CONTACT_TYPE_3, CONTACT_3);

        RESUME_4 = new Resume(UUID_4, "Name4");
        RESUME_4.addContact(CONTACT_TYPE_4, CONTACT_4);
    }


    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testUpdate() throws Exception {
        Resume r = new Resume(UUID_3, "New Name");
        storage.update(r);
        assertEquals(r, storage.get(UUID_3));
    }

    @Test
    public void testSave() throws Exception {
        storage.save(RESUME_4);
        assertArrayWithSort(RESUME_1,RESUME_2,RESUME_3,RESUME_4);
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(RESUME_1, storage.get(UUID_1));
        assertEquals(RESUME_2, storage.get(UUID_2));
        assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(UUID_1);
        assertArrayWithSort(RESUME_2,RESUME_3);
    }

    @Test
    public void testGetAll() throws Exception {
        assertArrayWithSort(RESUME_1,RESUME_2,RESUME_3);
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(3, storage.size());
    }

    private void assertArrayWithSort(Resume... resumes){
        List<Resume> arr = Arrays.asList(resumes);
        assertTrue(arr.containsAll(storage.getAllSorted()));
    }

}