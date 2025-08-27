package com.yourssu.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static com.yourssu.application.utils.TestIOUtils.*;
import static com.yourssu.application.utils.TestRandomUtils.*;

class ApplicationTests {

    @BeforeEach
    void setUp() {
        resetRandom();
    }

    @Test
    void test_Example1() {
        String inputs = String.join("\n",
                "1",
                "",
                "",
                "2",
                "2",
                "3",
                "3",
                "3",
                "3",
                "3",
                "3",
                "2"
        );

        setFixedRandomSequence(1);

        String output = captureOutputWithInput(inputs, () -> Application.main(new String[]{}));

        assertTrue(output.contains("플레이어: 1 2"));
        assertTrue(output.contains("컴퓨터: 3"));
        assertTrue(output.contains("플레이어: 4 5"));
        assertTrue(output.contains("컴퓨터: 6"));
        assertTrue(output.contains("플레이어: 31"));
    }

    @Test
    void test_Example2_and_3() {
        String inputs1 = String.join("\n",
                "1",
                "11",
                "3",
                "2",
                "3",
                "save",
                "mid_game.json"
        );

        setFixedRandomSequence(1, 1);

        String output1 = captureOutputWithInput(inputs1, () -> Application.main(new String[]{}));

        assertTrue(output1.contains("플레이어: 1 2"));
        assertTrue(output1.contains("컴퓨터: 3"));
        assertTrue(output1.contains("플레이어: 4 5 6"));
        assertTrue(output1.contains("컴퓨터: 7"));
        assertTrue(output1.contains("게임이 성공적으로 저장되었습니다!"));

        String inputs2 = String.join("\n",
                "2",
                "invalid_file.json",
                "mid_game.json",
                "4",
                "1",
                "1"
        );

        resetRandom();
        setFixedRandomSequence();

        String output2 = captureOutputWithInput(inputs2, () -> Application.main(new String[]{}));

        assertTrue(output2.contains("게임을 성공적으로 불러왔습니다!"));
        assertTrue(output2.contains("끝 숫자: 11"));
        assertTrue(output2.contains("한 번에 부를 수 있는 최대 숫자: 3"));
        assertTrue(output2.contains("이전 숫자: 7"));
        assertTrue(output2.contains("잘못된 입력입니다"));
        assertTrue(output2.contains("플레이어: 8"));
        assertTrue(output2.contains("컴퓨터: 9 10"));
    }
}
