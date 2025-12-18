# 対応表

"+", // ハムサ🪬  
"-", //モアイ🗿  
">", //それな！👉️  
"<", //それな2👈️  
".", //一重感嘆符❗  
",", //つまみ🤏  
"[", //笑い転げる🤣  
"]" //ベッド🛌  

# 導入方法

1. [リリース](https://github.com/sakurabajp/Emoji_Lang/releases)からjarファイルをダウンロード  
2. [Java25](https://www.oracle.com/jp/java/technologies/downloads/#jdk25-windows)をダウンロード&インストール   
3. cmd管理者権限から  
  `assoc .emj=EmojiLangFile`,  
  `ftype EmojiLangFile=java -jar "C:\tools\EmojiLang.jar" "%1"`,  
を実行  
(Java25以外を利用、また適切なjavaファイルのpathを通さないとエラーが出ます！！)
4. [こちら](https://www.suzusakirin.jp/sample.emj)からサンプルコードを実行してみよう！