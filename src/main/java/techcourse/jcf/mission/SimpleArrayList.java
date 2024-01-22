package techcourse.jcf.mission;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class SimpleArrayList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE =
            Integer.MAX_VALUE - 8; // 할당할 수 있는 배열의 최대 크기, 일부 VM들은 배열에 헤더 단어를 예약하기 때문에 8을 빼줌
    private static final String[] EMPTY_ELEMENTDATA = {};


    String[] elementData;
    private int size;


    public SimpleArrayList() {
        this.elementData = new String[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }

        if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
            return;
        }

        this.elementData = new String[initialCapacity];
    }


    private String[] grow(int minCapacity) {
        int newCapacity = calculateNewCapacity(minCapacity);

        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private String[] grow() {
        return grow(size + 1);
    }


    private int calculateNewCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 비트 시프트를 통해 50% 증가

        if (newCapacity - minCapacity <= 0) {
            return adjustCapacity(minCapacity);
        }

        return ensureMaxCapacityLimit(minCapacity, newCapacity);
    }

    private int adjustCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        if (elementData == EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private int ensureMaxCapacityLimit(int minCapacity, int newCapacity) {
        if (newCapacity - MAX_ARRAY_SIZE <= 0) {
            return newCapacity;
        }
        return hugeCapacity(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }

        if (minCapacity > MAX_ARRAY_SIZE) {
            return Integer.MAX_VALUE;
        }

        return MAX_ARRAY_SIZE;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public boolean add(String value) {
        if (size == elementData.length) {
            elementData = grow();
        }

        elementData[size] = value;
        size = size + 1;
        return true;
    }

    @Override
    public void add(int index, String value) {
        Objects.checkIndex(index, size + 1);
        if (size == elementData.length) {
            elementData = grow();
        }

        if (size - index > 0) {
            System.arraycopy(
                    elementData, index,
                    elementData, index + 1,
                    size - index
            );
        }

        elementData[index] = value;
        size = size + 1;
    }

    @Override
    public boolean remove(String value) {
        int foundIndex = IntStream.range(0, size)
                .filter(i -> Objects.equals(value, elementData[i]))
                .findFirst()
                .orElse(-1);

        if (foundIndex != -1) {
            removeElementAt(foundIndex);
            return true;
        }
        return false;
    }

    @Override
    public String remove(int index) {
        Objects.checkIndex(index, size);

        String oldValue = elementData[index];
        removeElementAt(index);

        return oldValue;
    }

    private void removeElementAt(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(
                    elementData, index + 1,
                    elementData, index,
                    numMoved
            );
        }
        size = size - 1;
        elementData[size] = null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }

        size = 0;
    }

    @Override
    public String get(int index) {
        Objects.checkIndex(index, size);

        return elementData[index];
    }

    @Override
    public String set(int index, String value) {
        Objects.checkIndex(index, size);

        String oldValue = elementData[index];
        elementData[index] = value;

        return oldValue;
    }

    @Override
    public int indexOf(String value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (elementData == null) {
            return "null";
        }
        int iMax = size - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(elementData[i]);
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }
}
