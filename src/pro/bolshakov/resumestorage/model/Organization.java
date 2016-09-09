package pro.bolshakov.resumestorage.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Organization {

    private final Link homePage;

    private final List<OrganizationData> organizationData = new ArrayList<>();

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
        this(new Link(name,url), startDate, endDate, title, description);
    }

    public Organization(Link homePage, LocalDate startDate, LocalDate endDate, String title, String description) {
        this.homePage = homePage;
        this.organizationData.addAll(Collections.singletonList(new OrganizationData(startDate, endDate, title, description)));
    }

    public Organization(String name, String url, List<OrganizationData> organizationData){
        homePage = new Link(name,url);
        this.organizationData.addAll(organizationData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        return organizationData.equals(that.organizationData);

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + organizationData.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", organizationData=" + organizationData +
                '}';
    }
}
