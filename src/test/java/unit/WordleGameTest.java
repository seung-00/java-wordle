package unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woowaapplication.pair.game.wordle.Coin;
import woowaapplication.pair.game.wordle.WordleGame;

class WordleGameTest {

    @Test
    @DisplayName("TXT 파일에서 단어 가져 오는 기능 테스트")
    void get_today_keyword() {
        // given
        WordleGame wordleGame = new WordleGame("spill", new Coin(WordleGame.TOTAL_CHANCE));

        // when
        List<String> keywordsFromFiles = wordleGame.getKeywordsFromFiles();

        // then
        assertThat(keywordsFromFiles.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("오늘 날짜를 기반으로 정답 단어를 가져 오는 기능 테스트")
    void line_calculate() {
        // given
        WordleGame wordleGame = new WordleGame("spill", new Coin(WordleGame.TOTAL_CHANCE));

        // 날짜 차이에 해당하는 길이보다 keyword 배열 길이가 큰 경우
        LocalDate today = LocalDate.of(2021, 6, 24);

        List<String> keywords = Arrays.asList(
                "cigar",
                "rebut",
                "sissy",
                "humph",
                "awake",
                "blush");

        String expectedTodayKeyword = "awake";

        // when
        String actualTodayKeyword = wordleGame.getKeywordWithToday(keywords, today);

        // then
        assertThat(actualTodayKeyword).isEqualTo(expectedTodayKeyword);
    }

}
