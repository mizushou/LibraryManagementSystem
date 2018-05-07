package ca.ciccc.comparator;

import ca.ciccc.model.Catalogue;

import java.util.Comparator;

public class CatalogueSortByYearComparator implements Comparator<Catalogue> {

    @Override
    public int compare(Catalogue o1, Catalogue o2) {
        return o2.getPublishedYear() - o1.getPublishedYear();
    }
}
