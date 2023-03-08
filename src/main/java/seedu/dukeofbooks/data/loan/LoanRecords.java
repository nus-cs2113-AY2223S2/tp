package seedu.dukeofbooks.data.loan;

import java.util.*;

public class LoanRecords {
    private final List<Loan> internalList;

    public LoanRecords() {
        internalList = new ArrayList<>();
    }

    public int size() {
        return internalList.size();
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    public boolean contains(Object o) {
        if (!(o instanceof Loan)) {
            return false;
        }
        return internalList.contains(o);
    }

    public Iterator<Loan> iterator() {
        return internalList.iterator();
    }

    public Object[] toArray() {
        return internalList.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return internalList.toArray(a);
    }

    public boolean add(Loan toAdd) {
        return internalList.add(toAdd);
    }

    public boolean remove(Loan toRemove) {
        return internalList.remove(toRemove);
    }

    public void clear() {
        internalList.clear();
    }

    public Loan get(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= internalList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return internalList.get(index);
    }


    public Loan remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= internalList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return internalList.remove(index);
    }

    public int indexOf(Loan toFind) {
        return internalList.indexOf(toFind);
    }

    public int lastIndexOf(Loan toFind) {
        return internalList.lastIndexOf(toFind);
    }

    public ListIterator<Loan> listIterator() {
        return internalList.listIterator();
    }

    public ListIterator<Loan> listIterator(int index) {
        return internalList.listIterator(index);
    }

    public List<Loan> subList(int fromIndex, int toIndex) {
        return internalList.subList(fromIndex, toIndex);
    }
}
