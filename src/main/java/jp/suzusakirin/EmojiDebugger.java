package jp.suzusakirin;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class EmojiDebugger {
    // Mainクラスと同じマッピングを使用
    private static final Map<String, String> EMOJI_MAP = Map.of(
            "\uD83E\uDEAC", "+", // ハムサ🪬
            "\uD83D\uDDFF", "-", // モアイ🗿
            "\uD83D\uDC49", ">", // それな！👉️
            "\uD83D\uDC48", "<", // それな2👈️
            "❗", ".", // 二重感嘆符❗
            "\uD83E\uDD0F", ",", // つまみ🤏
            "\uD83E\uDD23", "[", // 笑い転げる🤣
            "\uD83D\uDECC", "]"  // ベッド🛌
    );

    public static void main(String[] args) {
        // 【デバッグ用】ここに直接絵文字コードを貼り付けてください
        String emojiSource = """
🪬🪬🪬🪬🪬🪬🪬🪬🤣👉🪬🪬🪬🪬🪬🪬🪬🪬👈🗿🛌👉🪬❗
                """;

        System.out.println("=== Emoji to Brainfuck Debugger ===");
        System.out.println("Input Emoji Source:");
        System.out.println(emojiSource);
        System.out.println("-----------------------------------");

        String brainfuckCode = convert(emojiSource);

        System.out.println("Generated Brainfuck Code:");
        System.out.println(brainfuckCode);
        System.out.println("-----------------------------------");
    }

    /**
     * 文字列中の絵文字をBrainfuck記号に変換する
     */
    public static String convert(String source) {
        return source.codePoints()
                .mapToObj(Character::toString)
                .map(symbol -> EMOJI_MAP.getOrDefault(symbol, "")) // マップにない文字（改行やスペース）は無視
                .collect(Collectors.joining());
    }
}