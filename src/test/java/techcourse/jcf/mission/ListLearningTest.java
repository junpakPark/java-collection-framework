package techcourse.jcf.mission;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class ListLearningTest {
    @Test
    public void arrayList() {
        final SimpleList values = new SimpleArrayList();
        values.add("first");
        values.add("second");

        assertAll(
                () -> assertThat(values.add("third")).isTrue(), // 세 번째 값을 추가한다.
                () -> assertThat(values.size()).isEqualTo(3), // list의 크기를 구한다.
                () -> assertThat(values.get(0)).isEqualTo("first"), // 첫 번째 값을 찾는다.
                () -> assertThat(values.contains("first")).isTrue(), // "first" 값이 포함되어 있는지를 확인한다.
                () -> assertThat(values.remove(0)).isEqualTo("first"), // 첫 번째 값을 삭제한다.
                () -> assertThat(values.size()).isEqualTo(2) // 값이 삭제 됐는지 확인한다.
        );

        values.add(0, "first");

        System.out.println(values);

    }

    @Test
    public void LinkedList() {
        final SimpleList values = new SimpleLinkedList();
        values.add("first");
        values.add("second");

        System.out.println(values);

        assertAll(
                () -> assertThat(values.add("third")).isTrue(), // 세 번째 값을 추가한다.
                () -> assertThat(values.size()).isEqualTo(3), // list의 크기를 구한다.
                () -> assertThat(values.get(0)).isEqualTo("first"), // 첫 번째 값을 찾는다.
                () -> assertThat(values.contains("first")).isTrue(), // "first" 값이 포함되어 있는지를 확인한다.
                () -> assertThat(values.remove(0)).isEqualTo("first"), // 첫 번째 값을 삭제한다.
                () -> assertThat(values.size()).isEqualTo(2) // 값이 삭제 됐는지 확인한다.
        );

        System.out.println(values);

        values.add(0, "first");

        System.out.println(values);

    }
}
