package woowaapplication.pair.game.wordle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class WordleGame {

    public static final int TOTAL_CHANCE = 6;
    public static final int KEYWORD_LENGTH = 5;

    private final String answerKeyword;
    private final Coin coin;

    public WordleGame(String answerKeyword, Coin coin) {
        this.answerKeyword = answerKeyword;
        this.coin = coin;
    }

    public PlayResult play(String inputKeyword) {
        // 유효성 검사 진행
        // Validator 객체
        // 유효성 검사 메서드
        boolean validateResult = KeywordValidator.validate(inputKeyword, KEYWORD_LENGTH);

        if (!validateResult) {
            throw new IllegalArgumentException("잘못된 입력 값입니다");
        }

        //  정답 체크
        WordleBlock[] resultBlocks = checkAnswer(inputKeyword);

        boolean isAnswer = Arrays.stream(resultBlocks)
                .allMatch(block -> block == WordleBlock.CORRECT);

        // 정답이 아니면 잔여 횟수 감소
        if (!isAnswer) {
            coin.decreaseChance();
        }

        PlayResult result = new PlayResult(resultBlocks, coin);

        return result;
    }

    // 정답값

    // 정답 체크 메서드
    public WordleBlock[] checkAnswer(String inputKeyword) {
        // keyword 가 정답인지 체크 후 맞는 문자열 반환
        WordleBlock[] result = new WordleBlock[5];

        // TODO 1. 라인 정하기 -> ((현재 날짜 - 2021년 6월 19일) % 배열의 크기)

        // TODO 2. {위에서 구한 라인}번째의 단어를 TXT파일에서 가져오기

        return result;
    }

    public int getRestChance() {
        return coin.getRestChance();
    }

    public List<String> getKeywordsFromFiles() {
        try {
            URL resource = getClass().getClassLoader().getResource("words.txt");
            return Files.readAllLines(Paths.get(resource.toURI()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public String getKeywordWithToday(List<String> keywords, LocalDate today) {
        LocalDate targetDate = LocalDate.of(2021, 6, 19);

        Period period = Period.between(targetDate, today);
        int days = period.getDays();

        // ((현재 날짜 - 2021년 6월 19일) % 배열의 크기) 번째의 단어
        int index = (days % keywords.size());

        String todayKeyword = keywords.get(index - 1);

        return todayKeyword;
    }
}
