package woowaapplication.pair.game.wordle;

import java.util.List;

public class WordleGameUI {

    private final WordleGameStorage wordleGameStorage;

    public WordleGameUI(WordleGameStorage wordleGameStorage) {
        this.wordleGameStorage = wordleGameStorage;
    }


    public void printResult(List<String[]> gameResult) {
        if (wordleGameStorage.isClear()) {
            System.out.println(wordleGameStorage.getRestChance() + "/" + WordleGame.TOTAL_CHANCE);
        }

        gameResult.stream()
                .map(array -> String.join(" ", array))
                .forEach(System.out::println);
    }

    public static WordleGameUI of(WordleGameStorage wordleGameStorage) {
        return new WordleGameUI(wordleGameStorage);
    }
}
