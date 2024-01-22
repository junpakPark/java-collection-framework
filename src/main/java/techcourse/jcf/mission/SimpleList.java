package techcourse.jcf.mission;

public interface SimpleList {

    int size();

    boolean isEmpty();

    boolean contains(String value);

    boolean add(String value);

    boolean remove(String value);

    void clear();

    String get(int index);

    String set(int index, String value);

    void add(int index, String value);

    String remove(int index);

    int indexOf(String value);

}
