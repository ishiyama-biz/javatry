/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7(o)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7(o)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) { // false
            sea = 2001;
            sea = sea++ * 2;
        } else if (land && sea >= 904) { // false
            sea = 7;
            sea = ++sea * 2;
        } else if (sea >= 903 || land) { // true
            if (sea % 2 == 0) { // true
                sea = sea++ * 2; // sea : 1808
            }
            if (!land) { // true
                land = true; // land : true
            } else if (sea <= 903) {
                sea++;
            }
            if (sea < 1810) { // true
                sea = 8; // sea : 8
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) { // false
            sea--;
            if (sea % 2 == 1) {
                sea++;
            }
        }
        if (land) { // true
            sea = 10;
        }
        log(sea); // your answer? => 10(o)
        // 確か C とかでは `sea=sea++ * 2` みたいな書き方はできなかった気がする
        // C では未定義動作らしい by ChatGPT
        // 多分一時期未定義動作のコンパイルを弾いていたために勘違いした
        // Java では値を参照した後インクリメントされた値が追加され、その後で右辺が計算されて左辺に代入されるらしい by ChatGPT
        // Kotlin や Go は インクリメントされた変数を式として使えないらしい by ChatGPT
        // Python や Swift ではインクリメント自体がないらしい by ChatGPT
        // Python についてはそういえばそんなのを勉強した気がする
        // 未定義動作なのは C くらいなのか
        // done ishiyama [いいね] 勉強になります(^^ by jflute (2026/08/05)
        // 実際、Javaでも式として++を使うかというと、やらない傾向にありますね。(少なくともjfluteは)
        // ++やるときは、独立した行で書くというのが習慣になってます。

        // #1on1: 漠然読みからのフォーカス読み (2026/08/10)
        // 1. 漠然読みで構造だけ理解して、全体像を把握 (輪郭だけ読む)
        // 2. 当たりを付けて(sea=10)、フォーカス読み(landにフォーカス)
        //    (↑ギャンブルポイント)
        //
        // 時には、ギャンブルに負けることもある。
        // でも、全体像も把握してある程度フォーカス読みすることで、
        // 改めて上から地道に読むスピードが早くなってる(はず)。
        //
        // だし、1回目のギャンブルに負けたら負けたで、フォーカス読みをしたことで、
        // わりと自然と、次のギャンブルポイントが見つかって、またフォーカス読み。
        // ギャンブルを繰り返しているように見えるが、コード規模によっては、
        // 網羅的に読むよりは速い。
        //
        // 仮説思考とリンクする考え方。

        // done ishiyama [読み物課題] My Favorite Book: 仮説思考 by jflute (2026/08/10)
        // https://jflute.hatenadiary.jp/entry/20150111/kasetsu
        // 「自分の中でデマを広げさせない」も一緒に読みました
        // 前提に仮説を入れすぎたり、仮説を立ててから時間があくと確かに仮説キープは難しいなと思っていたのですごく納得感がありました。
        // 自分はこれまでコーディングにおいて、コードの深いところにある副作用とかを見逃すのが怖く、全部読んだ方がいいと思っていました。
        // ただ、どうしても時間がない時や、明らかにそれ以外の部分は気にしなくていいと理解できた場合だけこのようなコードリーディングをしていました。
        // どちらかというと書き方やツールを利用して最低限のコードリーディングで理解できるようにしようという発想でした。
        // ただこの仮説思考という考え方を知り、確かに全部を理解することは難しい場合は多く、そのような考えは他の場面でも活用できるので
        // そのように考えられるようにトレーニングしたいと思うようになりました。
        // done ishiyama 感想ありがとうございます！ by jflute (2026/08/24)
        // ぜひ意識の反復練習して、無意識にできるようになってもらえたらなと。
        // #1on1: "コードの深いところにある副作用とかを見逃すのが怖く" 自体はとても良いこと (2026/08/24)
        // 読めるんだったら読めるだけ読む。一方で、時間の制限があるから、仮説思考のようなアプローチ。
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside(o)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp(o)
        // #1on1: Iteratorパターンがあって、それがそのまま公式に実現されたみたいな感じでOK (2026/08/10)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar(o)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside(o)
        // -> ってなんの記法だったっけ？ Lambda だっけ？ (Java に Lambda ってないんじゃなかったけ？）
        // Lambda 式で、 Java 8 (2014年) からあるらしい by ChatGPT
        // それまでは匿名クラスで書いていたらしい by ChatGPT
        // 匿名クラスも見たことはあるくらい
        // 流石にオブジェクト指向として書くとしても匿名クラスは書くのが大変だな
        // Java 8 ってたまに聞く気がするな
        // Lambda 関数って呼ぶと間違いらしい by ChatGPT
        // 多分元は匿名クラスで書いていたものを関数型インターフェースとして書くことができるようにしたために Lambda 式と呼ぶことになったのだろう
        // おおよそ Lambda 式は匿名クラスの代わりになるが、 this の扱いなどが異なり、単純に変換されているものでもないらしい by ChatGPT

        // done ishiyama [ふぉろー] Java8 はJavaにとっては大きな転換期になったバージョンだからですね。 by jflute (2026/08/05)
        // javatryでも、step8はJava8で導入された機能に特化したステップになっています。
        // Lambda関数、そもそもJavaには厳密には関数という呼び名の文法は存在しないので、
        // Lambdaもあくまでコールバックのためのメソッド表現という感じです。
        // ただ、それを実現する仕組みとして、関数型インターフェースというところで関数という言葉が出てきます。
        // 匿名クラスとの違い、ほぼ同じものと考えても差し支えないですが、厳密にはちょっと違うというところですね。

        // done jflute 1on1にて、for文の歴史について (2026/08/05)
        // #1on1: 
        // o int iのfor文           // Javaの文法のループ
        // o 拡張for文 (普通のfor文) // Javaの文法のループ
        // o forEach()メソッド (内部では拡張for文) // 単なるメソッド
        //
        // $ むしろ、forEach()メソッドが不便だから拡張for文が出てきたのかもと思ったくらい by いしやまさん
        // $ コードが長くなりやすいから使いにくいなと思ったり？ by いしやまさん
        // 
        // 色々とforEach()メソッドできないこといっぱい
        // o 外側のローカル変数を変更できない
        // o continue, breakがない (returnでうまく代用するしかない)
        // 
        // あえて機能を削減したループを言える。
        // 「できなくて不便」なのか？「できなくて安心」なのか？
        // 関数型プログラミング的な思想にJavaでも影響があって、
        // 副作用をできるだけ起こさないプログラミングが好まれるようになってきた。
        // ローカル変数の変更は、そもそもあまりしないので、できない方が安心。
        // continue, breakもそんなにしないので、できなくても問題ない。
        // できないことが保証されていることによる、安全性と可読性。

        // #1on1: 適材適所なのか？シンプルなのか？のジレンマ。 (2026/08/10)
        // フレームワークの機能デザインでも常に考えること。

        // 余談で、Javaのバージョンの話とか。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList(); // ["broadway", "dockside", "hangar", "magiclamp"]
        List<String> aList = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                aList.add(stage);
            }
        }
        for (String stage : aList) {
            log(stage);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String sea = null;
        //    for (String stage : stageList) {
        //        if (stage.startsWith("br")) {
        //            continue;
        //        }
        //        sea = stage;
        //        if (stage.contains("ga")) {
        //            break;
        //        }
        //    }
        //    log(sea); // should be same as before-fix

        stageList.forEach(new Consumer<String>() {
            // TODO ishiyama エクササイズとはいえ、変数名の可読性もうちょい工夫してみましょう by jflute (2026/08/24)
            private boolean flag = true;

            @Override
            public void accept(String stage) {
                if (stage.startsWith("br")) {
                    return;
                }
                if (flag && stage.contains("ga")) {
                    log(stage);
                    flag = false;
                }
            }
        });
        // hangar
    }

    // なんか問題の意図とは違う気がする...
    // でも forEach() メソッドを使うのであれば、途中でループは止められないし変数は変えられないみたいだから難しい気がする
    // done ishiyama [ふぉろー] 意図は大丈夫ですよ。forEach()の制限(メリット!?)を体感してもらうもので by jflute (2026/08/05)
    // done jflute 1on1にて、forEach()の制限はメリットになる？話をする予定 (2026/08/05)

    // done ishiyama もし仮に、stageListの最後に、bongar という新しい要素が追加されたとしたら... by jflute (2026/08/05)
    // 実行結果どうなるでしょうか？foreach文をforEach()で同じ結果になるでしょうか？

    // 確かに、 foreach 文では `bongar` が消えますが、 forEach() では残りますね。
    // 自分で考えてみて、ラムダ式では難しそうだったので ChatGPT に聞いてみました。
    // 実際、 ChatGPT もラムダ式での実装は難しいとのことでした。
    // 一応、 Cosumer を用いるやり方や AtomicReference を使うやり方を提案されました。
    // AtomicReference はやりすぎに感じたので、 Consumer については自力で書いてみることにしました。
    // 匿名クラスについてあまり理解していなかったが、
    // インターフェースを実装したクラスやクラスを継承したクラスのインスタンスを作成できる文法らしい by ChatGPT
    // 最初は関数型インターフェース専用の文法かと思ってしまっていた。
    // TODO jflute 1on1にて、bongarのときフォロー (2026/08/24)

    // #1on1: AtomicReferenceのUnsafeから、Javaの会社、MySQLの会社のお話 (2026/08/24)
    // MySQL, Oracle DB などなど。

    // #1on1: 無名インナークラスのお話 (2026/08/24)
    // ちょこっとメソッドない名前付きインナークラスのレア話。

    // #1on1: AtomicReference, 用途違いのクラスのジレンマ (2026/08/24)
    // 用途違いで今はなんとか使うことができても、いつか互換性を失う可能性もなきにしもあらず。
    // 提供側からすると、用途違いのことは配慮しないものなので。
    // (暗黙の影響が出たりするととてもやっかい。Java標準はまだしも、OSSのフレームワークとかは要注意)
    // 極力、用途違いは起こさないようにしたいところ。
    // 逆にいうと、ちゃんとフレームワークのクラスをコンセプトから把握した上で使うことが大事。
    // (まだ用途違いをわかって使う分にはマシ。よくわからず動くから使うってのは怖い)

    // #1on1: bongarの対応はすでにできている (2026/08/24)
    // TODO ishiyama 今度は、gaを含んだstageが一つもなかったら？ by jflute (2026/08/24)
    // 意外にメソッド内名前付きクラスだったら解決しやすいかも。
    // もしくは、AtomicReference を使うか...
    // いや、無名インナークラスのままでも、toString()を若干hackするとかならなんとかなる？
    // どれか自分の好きなやつでやってみましょう。

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 1 から 20 までの整数のうち、 4 で割り切れるものだけを出力しなさい。
     * ただし、 `log` を複数回呼び出し、改行により異なる整数を出力して良いものとする。
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
        for (int i = 1; i <= 20; i++) {
            if (i % 4 == 0) {
                log(i);
            }
        }
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    // ["broadway", "dockside", "hangar", "magiclamp"]
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
