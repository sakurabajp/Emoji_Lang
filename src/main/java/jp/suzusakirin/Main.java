package jp.suzusakirin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

//TIP コードを<b>実行</b>するには、<shortcut actionId="Run"/> を押すか
// ガターの <icon src="AllIcons.Actions.Execute"/> アイコンをクリックします。
public class Main {
    // 絵文字とBrainfuck記号のマッピング
    private static final Map<String, String> EMOJI_MAP = Map.of(
            "\uD83E\uDEAC", "+", // ハムサ🪬
            "\uD83D\uDDFF", "-", //モアイ🗿
            "\uD83D\uDC49", ">", //それな！👉️
            "\uD83D\uDC48", "<", //それな2👈️
            "❗", ".", //一重感嘆符❗
            "\uD83E\uDD0F", ",", //つまみ🤏
            "\uD83E\uDD23", "[", //笑い転げる🤣
            "\uD83D\uDECC", "]" //ベッド🛌
    );

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("使用法: java EmojiLangTranspiler <ファイルパス.emj>");
            return;
        }

        String filePath = args[0];

        // 拡張子チェックの改善: 小文字に変換して末尾を確認
        if (!filePath.toLowerCase().endsWith(".emj")) {
            System.err.println("エラー: .emj ファイルのみ読み込み可能です。");
            System.err.println("指定されたパス: " + filePath);
            return;
        }

        try {
            String brainfuckCode = transpile(filePath);

            if (brainfuckCode.isEmpty()) {
                System.out.println("警告: Brainfuckコードが空です。絵文字が正しく認識されていない可能性があります。");
            } else {
                execute(brainfuckCode);
            }

        } catch (IOException e) {
            System.err.println("ファイルの読み込み中にエラーが発生しました: " + e.getMessage());
        }
    }

    /**
     * .emjファイルを読み込み、Brainfuckコードに変換する
     */
    public static String transpile(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        // ファイル全内容を文字列として読み込み
        String content = Files.readString(path);

        // 文字列を1文字（コードポイント）ずつ処理
        // 絵文字はサロゲートペア（2つのcharで1字）の場合があるため、codePoints()を使用
        return content.codePoints()
                .mapToObj(Character::toString)
                .map(symbol -> EMOJI_MAP.getOrDefault(symbol, "")) // マップにない文字（改行など）は無視
                .collect(Collectors.joining());
    }


        public static void execute(String code) {
            int[] tape = new int[30000]; // 標準的なテープサイズ
            int ptr = 0;
            int pc = 0; // プログラムカウンタ
            Scanner scanner = new Scanner(System.in);

            while (pc < code.length()) {
                char command = code.charAt(pc);

                switch (command) {
                    case '>':
                        ptr++;
                        break;
                    case '<':
                        ptr--;
                        break;
                    case '+':
                        tape[ptr]++;
                        break;
                    case '-':
                        tape[ptr]--;
                        break;
                    case '.':
                        System.out.print((char) tape[ptr]);
                        break;
                    case ',':
                        if (scanner.hasNext()) {
                            tape[ptr] = scanner.next().charAt(0);
                        }
                        break;
                    case '[':
                        if (tape[ptr] == 0) {
                            int loop = 1;
                            while (loop > 0) {
                                pc++;
                                char c = code.charAt(pc);
                                if (c == '[') loop++;
                                else if (c == ']') loop--;
                            }
                        }
                        break;
                    case ']':
                        if (tape[ptr] != 0) {
                            int loop = 1;
                            while (loop > 0) {
                                pc--;
                                char c = code.charAt(pc);
                                if (c == '[') loop--;
                                else if (c == ']') loop++;
                            }
                        }
                        break;
                }
                pc++;
            }
        }
    }
